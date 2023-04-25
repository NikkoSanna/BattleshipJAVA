package packet;

import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client extends JFrame implements Runnable {
    static String ipServer;
    final static int serverPort = 50000;
    Socket client;
    InputStreamReader inStream;
    OutputStreamWriter outStream;
    BufferedReader bufferIn;
    BufferedWriter bufferOut;
    String ip;

    Map mapOne;
    Map mapTwo;

    public Client(Map mapOne, Map mapTwo) throws IOException {
        this.mapOne = mapOne;
        this.mapTwo = mapTwo;

        this.setVisible(false);

        //Finestra per inserire l'indirizzo IPv4 del host
        ipServer = JOptionPane.showInputDialog(this,"Inserisci l'indirizzo del host",
                "Attenzione",JOptionPane.QUESTION_MESSAGE);

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
            bufferIn = new BufferedReader(inStream);
            bufferOut = new BufferedWriter(outStream);

            System.out.println("Connesso al server");

            bufferOut.write(ip);
            bufferOut.newLine();    //Riga piú importante qui
            bufferOut.flush();     //Impone la scrittura dei dati presenti nel buffer sul dispositivo di output

            String str = bufferIn.readLine();
            System.out.println("server avente indirizzo ip: " + str);

            bufferOut.write(mapOne.playerName.getText());
            bufferOut.newLine();
            bufferOut.flush();

            String enemyName = bufferIn.readLine();
            mapTwo.playerName.setText(enemyName);

            //Una volta connesso continua a comunicare
            while(true){

            }
        } catch (IOException e) {
            System.out.println("Client non startato");
        }
    }
}