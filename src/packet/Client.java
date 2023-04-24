package packet;

import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client extends JFrame {
    static String ipServer;
    final static int serverPort = 50000;
    Socket client;
    InputStreamReader inStream;
    OutputStreamWriter outStream;
    BufferedReader bufferIn;
    BufferedWriter bufferOut;

    public Client() throws IOException {
        this.setVisible(false);

        //Finestra per inserire l'indirizzo IPv4 del host
        ipServer = JOptionPane.showInputDialog(this,"Inserisci l'indirizzo del host",
                "Attenzione",JOptionPane.QUESTION_MESSAGE);

        //Ottengo l'indirizzo IPv4 locale, uguale a quello che ottengo da cmd
        //Ció non é necessario per il client, ma scriverlo in console potrebbe risultare utile
        String ip;
        try (final DatagramSocket socket = new DatagramSocket()) {
            socket.connect(InetAddress.getByName("8.8.8.8"), 50000);
            ip = socket.getLocalAddress().getHostAddress();
        }

        //Comunicazione con il server
        try {
            client = new Socket(ipServer, 50000);

            inStream = new InputStreamReader(client.getInputStream());
            outStream = new OutputStreamWriter(client.getOutputStream());
            bufferIn = new BufferedReader(inStream);
            bufferOut = new BufferedWriter(outStream);

            System.out.println("Connesso al server");

            bufferOut.write(ip);
            bufferOut.flush();     //Impone la scrittura dei dati presenti nel buffer sul dispositivo di output

            String str = bufferIn.readLine();
            System.out.println("server avente indirizzo ip: " + str);

            //Una volta connesso continua a comunicare
            while(true){

            }
        } catch (IOException e) {
            System.out.println("Client non startato");
        }
    }
}