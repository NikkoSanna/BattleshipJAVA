package packet;

import javax.swing.*;
import java.net.*;
import java.io.*;

public class Server extends JFrame{
    final static int serverPort = 50000;
    ServerSocket server;
    InputStreamReader inStream;
    OutputStreamWriter outStream;
    BufferedReader bufferIn;
    BufferedWriter bufferOut;

    public Server() throws IOException {
        this.setVisible(false);

        //Ottengo l'indirizzo IPv4 locale, uguale a quello che ottengo da cmd
        String ip;
        try (final DatagramSocket socket = new DatagramSocket()) {
            socket.connect(InetAddress.getByName("8.8.8.8"), serverPort);
            ip = socket.getLocalAddress().getHostAddress();
            System.out.println("il tuo indirizzo ip é " + ip + " e la porta 50000");

            //Finestra che informa dell'indirizzo IPv4 del host
            JOptionPane.showMessageDialog(this,"Comunica al secondo giocatore il codice " + ip + " per poter giocare",
                    "Attenzione",JOptionPane.INFORMATION_MESSAGE);
        }

        /* MACOS
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("google.com", 80));
        System.out.println(socket.getLocalAddress());
         */

        //Tentativo di apertura server
        try{
            server = new ServerSocket(serverPort);

            //Continua a provare a connettersi
            while(true){
                try(Socket client = server.accept()){

                    inStream = new InputStreamReader(client.getInputStream());
                    outStream = new OutputStreamWriter(client.getOutputStream());
                    bufferIn = new BufferedReader(inStream);
                    bufferOut = new BufferedWriter(outStream);

                    System.out.println("Connesso col client");

                    String str = bufferIn.readLine();
                    System.out.println("Client avente indirizzo ip: " + str);

                    bufferOut.write(ip);
                    bufferOut.flush();     //Impone la scrittura dei dati presenti nel buffer sul dispositivo di output

                    //Una volta connesso continua a comunicare
                    while(true){

                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Server non startato");
        }
    }
}