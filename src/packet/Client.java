package packet;

import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client extends JFrame implements Runnable {
    static String ipServer;
    final static int serverPort = 50000;
    String ip;
    boolean yourTurn = false;

    Socket client;
    InputStreamReader inStream;
    OutputStreamWriter outStream;
    BufferedReader bufferIn;
    BufferedWriter bufferOut;

    Map mapOne;
    Map mapTwo;

    public Client(String playerName) throws IOException {
        mapOne = new Map(null, this);
        mapTwo = new Map(null, this);

        mapOne.playerName.setText(playerName);

        mapTwo.setLocation((ScreenSize.getWidth() / 2) + 25, (ScreenSize.getHeight() / 3) - 250);
        mapTwo.shipselect.dispose();
        mapTwo.bottomBar.remove(mapTwo.ready);
        mapTwo.bottomBar.add(mapTwo.gameText);

        mapTwo.gameText.setText("Connettiti ad un server...");

        this.setVisible(false);

        //Finestra per inserire l'indirizzo IPv4 del host
        ipServer = JOptionPane.showInputDialog(this,"Inserisci l'indirizzo del host", "Attenzione",JOptionPane.QUESTION_MESSAGE);

        //Ottengo l'indirizzo IPv4 locale, uguale a quello che ottengo da cmd
        //Ció non é necessario per il client, ma scriverlo in console potrebbe risultare utile
        try (final DatagramSocket socket = new DatagramSocket()) {
            socket.connect(InetAddress.getByName("8.8.8.8"), 50000);
            ip = socket.getLocalAddress().getHostAddress();
        }
    }

    @Override
    public void run() {
        //Comunicazione con il server
        try {
            client = new Socket(ipServer, 50000);

            inStream = new InputStreamReader(client.getInputStream());
            outStream = new OutputStreamWriter(client.getOutputStream());
            bufferIn = new BufferedReader(inStream);        //Usare solo questo oggetto per gli input
            bufferOut = new BufferedWriter(outStream);      //Usare solo questo oggetto per gli output

            //Messaggi a console riguardanti la connessione
            System.out.println("Connesso al server");

            bufferOut.write(ip);
            bufferOut.newLine();    //Riga piú importante qui
            bufferOut.flush();     //Impone la scrittura dei dati presenti nel buffer sul dispositivo di output

            String str = bufferIn.readLine();
            System.out.println("server avente indirizzo ip: " + str);

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
        } catch (IOException e) {
            System.out.println("Client non startato");
        }
    }
}