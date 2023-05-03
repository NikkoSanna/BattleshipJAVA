package packet;

import javax.swing.*;
import java.net.*;
import java.io.*;
import java.sql.SQLOutput;

public class Server implements Runnable {
    final static int serverPort = 50000;

    String playerName;
    String ip;
    String str;     //stringa usata per la ricezione dal client
    String tileUsed;    //Stringa che contiene la casella che ho cliccato

    int ready = 2;      //booleano che si decrementa per capire quando entrambi sono pronti
    boolean yourTurn = true;      //booleano che gestisce i turni
    boolean loop = true;        //Usata solamente per evitare un blocco quando si clicca pronto
    boolean validHit = false;

    ServerSocket server;
    InputStreamReader inStream;
    OutputStreamWriter outStream;
    BufferedReader bufferIn;        //Usato per la ricezione dati
    BufferedWriter bufferOut;       //Usato per l'invio dati

    Map mapOne;
    Map mapTwo;
    IP_Announcer ipAnnouncer;

    public Server(String playerName) throws IOException {
        this.playerName = playerName;

        //Ottengo l'indirizzo IPv4 locale, uguale a quello che ottengo da cmd
        try (final DatagramSocket socket = new DatagramSocket()) {
            socket.connect(InetAddress.getByName("8.8.8.8"), serverPort);
            ip = socket.getLocalAddress().getHostAddress();
            System.out.println("il tuo indirizzo ip é " + ip + " e la porta 50000");
        }

        /* MACOS
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("google.com", 80));
        System.out.println(socket.getLocalAddress());
         */

        //Finestra che comunica l'indirizzo IPv4 finché non si connette qualcuno
        ipAnnouncer = new IP_Announcer(ip);
    }

    @Override
    public void run() {
        //Tentativo di apertura server
        try {
            server = new ServerSocket(serverPort);

            //Continua a provare a connettersi
            try (Socket client = server.accept()) {
                //Posso chiudere la finestra che comunica l'IPv4 e generare le mappe
                ipAnnouncer.dispose();

                //Genero le mappe di gioco
                mapOne = new Map(this, null, "mapOne");
                mapTwo = new Map(this, null, "mapTwo");
                mapOne.playerName.setText(playerName);

                //La mappa 2 avrá qualche differenza dalla mappa 1
                mapTwo.setLocation((ScreenSize.getWidth() / 2) + 25, (ScreenSize.getHeight() / 3) - 250);
                mapTwo.bottomBar.remove(mapTwo.ready);
                mapTwo.bottomBar.add(mapTwo.gameText);

                //Creazione oggetti che verranno usati nella comunicazione
                inStream = new InputStreamReader(client.getInputStream());
                outStream = new OutputStreamWriter(client.getOutputStream());
                bufferIn = new BufferedReader(inStream);         //Usare solo questo oggetto per gli input
                bufferOut = new BufferedWriter(outStream);       //Usare solo questo oggetto per gli output

                //Messaggi a console riguardanti la connessione
                System.out.println("Connesso col client");

                str = bufferIn.readLine();
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
                while (true) {
                    //Finché entrambi non sono pronti il gioco non inizia
                    while (ready > 0 || loop) {
                        //Se non uso un altro booleano rischio che si blocchi nella riga di lettura per sempre
                        if (loop) {
                            str = bufferIn.readLine();
                            if (str.equals("ready")) {
                                ready -= 1;
                                loop = false;
                            }
                        }
                        //System.out.println(" ");    //Si...questa riga se manca si rompe tutto - 3 ore buttate
                        try {
                            Thread.sleep(500);     //Almeno non spamma la console
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    mapTwo.actuallyPlaying = true;

                    //Gestisco il mio turno di gioco
                    if (yourTurn) {
                        mapTwo.gameText.setText("E il tuo turno!");

                        do {
                            System.out.println("aaa");

                            String[] coordinates = tileUsed.split(",");     //Splitto le coordinate
                            int x = Integer.parseInt(coordinates[0]);     //Converto la coordinata x in intero
                            int y = Integer.parseInt(coordinates[1]);     //Converto la coordinata y in intero

                            if (str.equals("goodHit")) {
                                mapTwo.tile[x][y].setIcon(mapTwo.tile[x][y].shipHit);
                            } else {
                                mapTwo.tile[x][y].setIcon(mapTwo.tile[x][y].badHit);

                            } else {
                                clickAgain = true;
                            }
                        } while (clickAgain);

                        yourTurn = false;
                        tileUsed = null;

                        //Gestisco il turno di gioco dell'avversario
                    } else {
                        mapTwo.gameText.setText("Turno avversario!");

                        while (!validHit) {
                            str = bufferIn.readLine();

                            //Controllo se ho perso
                            if (str.equals("lost") || str.equals("win")) {
                                mapTwo.gameText.setText("Hai perso");

                                new VictoryScreen(str);

                                //Chiudo il programma
                                try {
                                    Thread.sleep(4000);
                                    System.exit(0);
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }

                                //Controllo quali caselle sono state colpite
                            } else {

                                String[] coordinates = str.split(",");     //Splitto le coordinate
                                int x = Integer.parseInt(coordinates[0]);     //Converto la coordinata x in intero
                                int y = Integer.parseInt(coordinates[1]);     //Converto la coordinata y in intero

                                mapOne.tile[x][y].tileHit(x, y);
                            }
                        }

                        yourTurn = true;
                    }
                }
            }
        } catch (IOException e1) {
            System.out.println("Server non startato");
        }
    }
}