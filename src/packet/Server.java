package packet;

import javax.swing.*;
import java.net.*;
import java.io.*;

public class Server extends JFrame{
    final static int serverPort = 50000;
    ServerSocket server;

    public Server() throws IOException {
        this.setVisible(false);

        //Ottengo l'indirizzo IPv4 locale, uguale a quello che ottengo da cmd
        String ip;
        try (final DatagramSocket socket = new DatagramSocket()) {
            socket.connect(InetAddress.getByName("8.8.8.8"), serverPort);
            ip = socket.getLocalAddress().getHostAddress();
            System.out.println("il tuo indirizzo ip é " + ip + " e la porta 50000");

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

            while(true){
                try(Socket client = server.accept()){
                    //Comandi di informazione sull'avvenuta connessione
                    System.out.println("Connesso al client");

                    InputStreamReader in = new InputStreamReader(client.getInputStream());
                    BufferedReader bf = new BufferedReader(in);

                    String str = bf.readLine();
                    System.out.println("client avente indirizzo ip: " + str);

                    PrintWriter pr = new PrintWriter(client.getOutputStream());
                    pr.println(ip);
                    pr.flush();     //impone la scrittura immediata di tutti i dati presenti nel buffer sul dispositivo di output.

                }
            }
        } catch (IOException e) {
            System.out.println("Server non startato");
        }
    }
}