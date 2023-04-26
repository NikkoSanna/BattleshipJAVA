package packet;

import javax.swing.*;
import java.net.*;
import java.io.*;

public class Server extends JFrame implements Runnable{
    final static int serverPort = 50000;
    String ip;
    boolean yourTurn = true;

    ServerSocket server;
    InputStreamReader inStream;
    OutputStreamWriter outStream;
    BufferedReader bufferIn;
    BufferedWriter bufferOut;

    Map mapOne;
    Map mapTwo;

    public Server(String playerName) throws IOException {
        mapOne = new Map(this, null);
        mapTwo = new Map(this, null);

        mapOne.playerName.setText(playerName);

        mapTwo.setLocation((ScreenSize.getWidth() / 2) + 25, (ScreenSize.getHeight() / 3) - 250);
        mapTwo.shipselect.dispose();
        mapTwo.bottomBar.remove(mapTwo.ready);
        mapTwo.bottomBar.add(mapTwo.gameText);

        mapTwo.gameText.setText("In attesa di una connessione...");

        this.setVisible(false);

        //Ottengo l'indirizzo IPv4 locale, uguale a quello che ottengo da cmd
        try (final DatagramSocket socket = new DatagramSocket()) {
            socket.connect(InetAddress.getByName("8.8.8.8"), serverPort);
            ip = socket.getLocalAddress().getHostAddress();
            System.out.println("il tuo indirizzo ip é " + ip + " e la porta 50000");

            //Finestra che informa dell'indirizzo IPv4 del host
            JOptionPane.showMessageDialog(this,"Comunica al secondo giocatore il codice " + ip + " per poter giocare","Attenzione",JOptionPane.INFORMATION_MESSAGE);
        }

        /* MACOS
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("google.com", 80));
        System.out.println(socket.getLocalAddress());
         */
    }

    @Override
    public void run() {
        //Tentativo di apertura server
        try{
            server = new ServerSocket(serverPort);

            //Continua a provare a connettersi
            while(true){
                try(Socket client = server.accept()){

                    inStream = new InputStreamReader(client.getInputStream());
                    outStream = new OutputStreamWriter(client.getOutputStream());
                    bufferIn = new BufferedReader(inStream);         //Usare solo questo oggetto per gli input
                    bufferOut = new BufferedWriter(outStream);       //Usare solo questo oggetto per gli output

                    //Messaggi a console riguardanti la connessione
                    System.out.println("Connesso col client");

                    String str = bufferIn.readLine();
                    System.out.println("Client avente indirizzo ip: " + str);

                    bufferOut.write(ip);
                    bufferOut.newLine();     //Riga piú importante qui
                    bufferOut.flush();     //Impone la scrittura dei dati presenti nel buffer sul dispositivo di output

                    //Scambio dei nickname
                    bufferOut.write(mapOne.playerName.getText());
                    bufferOut.newLine();
                    bufferOut.flush();

                    String enemyName = bufferIn.readLine();
                    mapTwo.playerName.setText(enemyName);

                    mapTwo.gameText.setText("Connessione riuscita");

                    //Una volta connesso continua a comunicare
                    while(true){
                        if(!yourTurn){
                            bufferIn.readLine();
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Server non startato");
        }
    }
}