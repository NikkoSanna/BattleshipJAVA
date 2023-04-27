package packet;

import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client implements Runnable {
    final static int serverPort = 50000;
    String ipServer;
    String ip;
    String str;     //stringa usata per la ricezione dal server
    boolean started = false;    //booleano usato quando ancora entrambi non hanno cliccato pronto
    boolean yourTurn = false;       //booleano che gestisce i turni

    Socket client;
    InputStreamReader inStream;
    OutputStreamWriter outStream;
    BufferedReader bufferIn;        //Usato per la ricezione dati
    BufferedWriter bufferOut;       //Usato per l'invio dati

    Map mapOne;
    Map mapTwo;

    public Client(String playerName, String ipServer) throws IOException {
        this.ipServer = ipServer;

        //Genero le mappe di gioco
        mapOne = new Map(null, this);
        mapTwo = new Map(null, this);
        mapTwo.shipselect.dispose();

        mapOne.playerName.setText(playerName);

        //La mappa 2 avrá qualche differenza dalla mappa 1
        mapTwo.setLocation((ScreenSize.getWidth() / 2) + 25, (ScreenSize.getHeight() / 3) - 250);
        mapTwo.bottomBar.remove(mapTwo.ready);
        mapTwo.bottomBar.add(mapTwo.gameText);

        mapTwo.gameText.setText("Connettiti ad un server...");

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
            client = new Socket(ipServer, serverPort);

            inStream = new InputStreamReader(client.getInputStream());
            outStream = new OutputStreamWriter(client.getOutputStream());
            bufferIn = new BufferedReader(inStream);        //Usare solo questo oggetto per gli input
            bufferOut = new BufferedWriter(outStream);      //Usare solo questo oggetto per gli output

            //Messaggi a console riguardanti la connessione
            System.out.println("Connesso al server");

            bufferOut.write(ip);
            bufferOut.newLine();    //Riga piú importante qui
            bufferOut.flush();     //Impone la scrittura dei dati presenti nel buffer sul dispositivo di output

            str = bufferIn.readLine();
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
                //Finché entrambi non sono pronti il gioco non inizia
                while(!started){
                    str = bufferIn.readLine();
                    if(str.equals("ready")){
                        started = true;
                    }
                }

                //Gestisco i turni di gioco
                if(yourTurn){
                    mapTwo.gameText.setText("E il tuo turno!");
                }else {
                    str = bufferIn.readLine();
                    String[] coordinates = str.split(",");     //Splitto le coordinate
                    int x = Integer.parseInt(coordinates[0]);     //Converto la coordinata x in intero
                    int y = Integer.parseInt(coordinates[1]);     //Converto la coordinata y in intero

                    mapOne.tile[x][y].tileHit(x,y);
                }
            }
        } catch (IOException e1) {
            System.out.println("Client non startato");
        }
    }
}