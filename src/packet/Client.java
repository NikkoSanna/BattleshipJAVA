package packet;

import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client extends JFrame {
    static String ipServer;
    final static int serverPort = 50000;
    Socket client;
    boolean started = false;

    public Client() throws IOException {
        this.setVisible(false);
        ipServer = JOptionPane.showInputDialog(this,"Inserisci l'indirizzo del host",
                "Attenzione",JOptionPane.QUESTION_MESSAGE);

        //Ottengo l'indirizzo IPv4 locale, uguale a quello che ottengo da cmd
        String ip;
        try (final DatagramSocket socket = new DatagramSocket()) {
            socket.connect(InetAddress.getByName("8.8.8.8"), 50000);
            ip = socket.getLocalAddress().getHostAddress();
        }

        //Comunicazione con il server
        while(true){
            //Tentativo di connessione al server
            try {
                client = new Socket(ipServer, 50000);
                started = true;
            } catch (IOException e) {
                System.out.println("Client non startato");
                started = false;
            }

            //Scambio di informazioni tra i dispositivi
            if(started){
                //Comandi di informazione sull'avvenuta connessione
                System.out.println("Connesso al server");

                PrintWriter pr = new PrintWriter(client.getOutputStream());
                pr.println(ip);
                pr.flush();     //Impone la scrittura dei dati presenti nel buffer sul dispositivo di output

                InputStreamReader in = new InputStreamReader(client.getInputStream());
                BufferedReader bf = new BufferedReader(in);

                String str = bf.readLine();
                System.out.println("server avente indirizzo ip: " + str);


            }
        }
    }
}