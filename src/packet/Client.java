package packet;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.net.*;
import java.io.*;

public class Client extends JFrame implements Runnable {
    final static int serverPort = 50000;

    String playerName;
    String ipServer;
    String ip;
    String str;     //stringa usata per la ricezione dal server
    String tileUsed;    //Stringa che contiene la casella che ho cliccato

    int shipSunk = 0;       //Contatore delle navi affondate

    boolean started = false;    //booleano usato quando ancora entrambi non hanno cliccato pronto
    boolean yourTurn = false;       //booleano che gestisce i turni
    boolean loop = true;        //Usata solamente per evitare di iniziare la partita prima del server
    boolean validHit = false;       //Usata per rileggere le coordinate dell'avversario se ha cliccato una casella già cliccata in precedenza
    boolean clickAgain = false;     //Usata per rifare i cicli se si clicca su una casella già cliccata in precedenza

    Socket client;
    InputStreamReader inStream;
    OutputStreamWriter outStream;
    BufferedReader bufferIn;        //Usato per la ricezione dati
    BufferedWriter bufferOut;       //Usato per l'invio dati

    Map mapOne;
    Map mapTwo;

    public Client(String playerName, String ipServer) throws IOException {
        this.setVisible(false);

        this.playerName = playerName;
        this.ipServer = ipServer;

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

            //Genero le mappe di gioco
            mapOne = new Map(null, this, "mapOne");
            mapTwo = new Map(null, this, "mapTwo");
            mapOne.playerName.setText(playerName);

            //La mappa 2 avrá qualche differenza dalla mappa 1
            mapTwo.setLocation((ScreenSize.getWidth() / 2) + 25, (ScreenSize.getHeight() / 3) - 250);
            mapTwo.bottomBar.add(mapTwo.gameText);

            //Creazione oggetti che verranno usati nella comunicazione
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
            System.out.println("Server avente indirizzo ip: " + str);

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
                while (!started || loop) {
                    //Se non uso un altro booleano rischio che si blocchi nella riga di lettura per sempre
                    if (!started) {
                        str = bufferIn.readLine();
                        if (str.equals("ready")) {
                            started = true;
                        }
                    }
                    //System.out.println(" ");    //Si...questa riga se manca si rompe tutto - 3 ore buttate
                    try {
                        Thread.sleep(500);     //Almeno non spamma la console
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                mapTwo.actuallyPlaying = true;      //Probabilmente superfluo

                //Gestisco il mio turno di gioco
                if (yourTurn) {
                    mapTwo.gameText.setText("E il tuo turno!");

                    //L'avversario mi comunica se ho cliccato su una casella con barca o meno
                    //Ció solo dopo che io ho cliccato su di una casella
                    do {
                        str = bufferIn.readLine();

                        //Se ho colpito una barca allora devo mettere l'icona corretta e controllare per un affondo
                        if (str.equals("goodHit")) {
                            clickAgain = false;     //Il ciclo di lettura si puó interrompere

                            //Ottengo le coordinate sotto forma di unica stringa
                            String[] coordinates = tileUsed.split(",");     //Splitto le coordinate
                            int x = Integer.parseInt(coordinates[0]);     //Converto la coordinata x in intero
                            int y = Integer.parseInt(coordinates[1]);     //Converto la coordinata y in intero

                            mapTwo.tile[x][y].setIcon(mapTwo.tile[x][y].shipHit);

                            //Controllo se ho affondato una barca
                            str = bufferIn.readLine();

                            //Se l'ho fatto devo cambiare le icone delle caselle
                            if(str.equals("shipTwoSunk")){
                                shipSunk++;
                                //Uso un ciclo e leggo dal client tutte le coordinate delle caselle
                                for(int a = 0; a < mapTwo.shipTwo_Tiles.length; a++){
                                    str = bufferIn.readLine();

                                    String[] coordinates2 = str.split(",");     //Splitto le coordinate
                                    int x2 = Integer.parseInt(coordinates2[0]) + 1;     //Converto la coordinata x in intero
                                    int y2 = Integer.parseInt(coordinates2[1]) + 1;     //Converto la coordinata y in intero

                                    //Cambio le icone
                                    if(a == 0){
                                        mapTwo.tile[x2][y2].setIcon(mapTwo.tile[x2][y2].ship2_1Hit);
                                    }else if(a == 1) {
                                        mapTwo.tile[x2][y2].setIcon(mapTwo.tile[x2][y2].ship2_2Hit);
                                    }
                                }

                                //Effetto audio affondo
                                try {
                                    File file = new File("SunkSoundEffect.wav"); // Inserire il percorso del file audio clic
                                    Clip clip = AudioSystem.getClip();
                                    clip.open(AudioSystem.getAudioInputStream(file));
                                    clip.start();
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }else if(str.equals("shipThreeSunk")){
                                shipSunk++;
                                //Uso un ciclo e leggo dal client tutte le coordinate delle caselle
                                for(int a = 0; a < mapTwo.shipThree_Tiles.length; a++){
                                    str = bufferIn.readLine();

                                    String[] coordinates2 = str.split(",");     //Splitto le coordinate
                                    int x2 = Integer.parseInt(coordinates2[0]) + 1;     //Converto la coordinata x in intero
                                    int y2 = Integer.parseInt(coordinates2[1]) + 1;     //Converto la coordinata y in intero

                                    //Cambio le icone
                                    if(a == 0){
                                        mapTwo.tile[x2][y2].setIcon(mapTwo.tile[x2][y2].ship3_1Hit);
                                    }else if(a == 1) {
                                        mapTwo.tile[x2][y2].setIcon(mapTwo.tile[x2][y2].ship3_2Hit);
                                    }else if(a == 2){
                                        mapTwo.tile[x2][y2].setIcon(mapTwo.tile[x2][y2].ship3_3Hit);
                                    }
                                }

                                //Effetto audio affondo
                                try {
                                    File file = new File("SunkSoundEffect.wav"); // Inserire il percorso del file audio clic
                                    Clip clip = AudioSystem.getClip();
                                    clip.open(AudioSystem.getAudioInputStream(file));
                                    clip.start();
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }else if(str.equals("shipFourSunk")){
                                shipSunk++;
                                //Uso un ciclo e leggo dal client tutte le coordinate delle caselle
                                for(int a = 0; a < mapTwo.shipFour_Tiles.length; a++){
                                    str = bufferIn.readLine();

                                    String[] coordinates2 = str.split(",");     //Splitto le coordinate
                                    int x2 = Integer.parseInt(coordinates2[0]) + 1;     //Converto la coordinata x in intero
                                    int y2 = Integer.parseInt(coordinates2[1]) + 1;     //Converto la coordinata y in intero

                                    //Cambio le icone
                                    if(a == 0){
                                        mapTwo.tile[x2][y2].setIcon(mapTwo.tile[x2][y2].ship4_1Hit);
                                    }else if(a == 1) {
                                        mapTwo.tile[x2][y2].setIcon(mapTwo.tile[x2][y2].ship4_2Hit);
                                    }else if(a == 2){
                                        mapTwo.tile[x2][y2].setIcon(mapTwo.tile[x2][y2].ship4_3Hit);
                                    }else if(a == 3){
                                        mapTwo.tile[x2][y2].setIcon(mapTwo.tile[x2][y2].ship4_4Hit);
                                    }
                                }

                                //Effetto audio affondo
                                try {
                                    File file = new File("SunkSoundEffect.wav"); // Inserire il percorso del file audio clic
                                    Clip clip = AudioSystem.getClip();
                                    clip.open(AudioSystem.getAudioInputStream(file));
                                    clip.start();
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }else if(str.equals("shipFiveSunk")){
                                shipSunk++;
                                //Uso un ciclo e leggo dal client tutte le coordinate delle caselle
                                for(int a = 0; a < mapTwo.shipFive_Tiles.length; a++){
                                    str = bufferIn.readLine();

                                    String[] coordinates2 = str.split(",");     //Splitto le coordinate
                                    int x2 = Integer.parseInt(coordinates2[0]) + 1;     //Converto la coordinata x in intero
                                    int y2 = Integer.parseInt(coordinates2[1]) + 1;     //Converto la coordinata y in intero

                                    //Cambio le icone
                                    if(a == 0){
                                        mapTwo.tile[x2][y2].setIcon(mapTwo.tile[x2][y2].ship5_1Hit);
                                    }else if(a == 1) {
                                        mapTwo.tile[x2][y2].setIcon(mapTwo.tile[x2][y2].ship5_2Hit);
                                    }else if(a == 2){
                                        mapTwo.tile[x2][y2].setIcon(mapTwo.tile[x2][y2].ship5_3Hit);
                                    }else if(a == 3){
                                        mapTwo.tile[x2][y2].setIcon(mapTwo.tile[x2][y2].ship5_4Hit);
                                    }else if(a == 4){
                                        mapTwo.tile[x2][y2].setIcon(mapTwo.tile[x2][y2].ship5_5Hit);
                                    }
                                }

                                //Effetto audio affondo
                                try {
                                    File file = new File("SunkSoundEffect.wav"); // Inserire il percorso del file audio clic
                                    Clip clip = AudioSystem.getClip();
                                    clip.open(AudioSystem.getAudioInputStream(file));
                                    clip.start();
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                            else if (str.equals("shipTwoSunkTilt")) {
                                shipSunk++;
                                //Uso un ciclo e leggo dal client tutte le coordinate delle caselle
                                for (int a = 0; a < mapTwo.shipTwo_Tiles.length; a++) {
                                    str = bufferIn.readLine();

                                    String[] coordinates2 = str.split(",");     //Splitto le coordinate
                                    int x2 = Integer.parseInt(coordinates2[0]) + 1;     //Converto la coordinata x in intero
                                    int y2 = Integer.parseInt(coordinates2[1]) + 1;     //Converto la coordinata y in intero

                                    //Cambio le icone
                                    if (a == 0) {
                                        mapTwo.tile[x2][y2].setIcon(mapTwo.tile[x2][y2].ship2_1HitTilted);
                                    } else if (a == 1) {
                                        mapTwo.tile[x2][y2].setIcon(mapTwo.tile[x2][y2].ship2_2HitTilted);
                                    }
                                }

                                //Effetto audio affondo
                                try {
                                    File file = new File("SunkSoundEffect.wav"); // Inserire il percorso del file audio clic
                                    Clip clip = AudioSystem.getClip();
                                    clip.open(AudioSystem.getAudioInputStream(file));
                                    clip.start();
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            } else if (str.equals("shipThreeSunkTilt")) {
                                shipSunk++;
                                //Uso un ciclo e leggo dal client tutte le coordinate delle caselle
                                for (int a = 0; a < mapTwo.shipThree_Tiles.length; a++) {
                                    str = bufferIn.readLine();

                                    String[] coordinates2 = str.split(",");     //Splitto le coordinate
                                    int x2 = Integer.parseInt(coordinates2[0]) + 1;     //Converto la coordinata x in intero
                                    int y2 = Integer.parseInt(coordinates2[1]) + 1;     //Converto la coordinata y in intero

                                    //Cambio le icone
                                    if (a == 0) {
                                        mapTwo.tile[x2][y2].setIcon(mapTwo.tile[x2][y2].ship3_1HitTilted);
                                    } else if (a == 1) {
                                        mapTwo.tile[x2][y2].setIcon(mapTwo.tile[x2][y2].ship3_2HitTilted);
                                    } else if (a == 2) {
                                        mapTwo.tile[x2][y2].setIcon(mapTwo.tile[x2][y2].ship3_3HitTilted);
                                    }
                                }

                                //Effetto audio affondo
                                try {
                                    File file = new File("SunkSoundEffect.wav"); // Inserire il percorso del file audio clic
                                    Clip clip = AudioSystem.getClip();
                                    clip.open(AudioSystem.getAudioInputStream(file));
                                    clip.start();
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            } else if (str.equals("shipFourSunkTilt")) {
                                shipSunk++;
                                //Uso un ciclo e leggo dal client tutte le coordinate delle caselle
                                for (int a = 0; a < mapTwo.shipFour_Tiles.length; a++) {
                                    str = bufferIn.readLine();

                                    String[] coordinates2 = str.split(",");     //Splitto le coordinate
                                    int x2 = Integer.parseInt(coordinates2[0]) + 1;     //Converto la coordinata x in intero
                                    int y2 = Integer.parseInt(coordinates2[1]) + 1;     //Converto la coordinata y in intero

                                    //Cambio le icone
                                    if (a == 0) {
                                        mapTwo.tile[x2][y2].setIcon(mapTwo.tile[x2][y2].ship4_1HitTilted);
                                    } else if (a == 1) {
                                        mapTwo.tile[x2][y2].setIcon(mapTwo.tile[x2][y2].ship4_2HitTilted);
                                    } else if (a == 2) {
                                        mapTwo.tile[x2][y2].setIcon(mapTwo.tile[x2][y2].ship4_3HitTilted);
                                    } else if (a == 3) {
                                        mapTwo.tile[x2][y2].setIcon(mapTwo.tile[x2][y2].ship4_4HitTilted);
                                    }
                                }

                                //Effetto audio affondo
                                try {
                                    File file = new File("SunkSoundEffect.wav"); // Inserire il percorso del file audio clic
                                    Clip clip = AudioSystem.getClip();
                                    clip.open(AudioSystem.getAudioInputStream(file));
                                    clip.start();
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            } else if (str.equals("shipFiveSunkTilt")) {
                                shipSunk++;
                                //Uso un ciclo e leggo dal client tutte le coordinate delle caselle
                                for (int a = 0; a < mapTwo.shipFive_Tiles.length; a++) {
                                    str = bufferIn.readLine();

                                    String[] coordinates2 = str.split(",");     //Splitto le coordinate
                                    int x2 = Integer.parseInt(coordinates2[0]) + 1;     //Converto la coordinata x in intero
                                    int y2 = Integer.parseInt(coordinates2[1]) + 1;     //Converto la coordinata y in intero

                                    //Cambio le icone
                                    if (a == 0) {
                                        mapTwo.tile[x2][y2].setIcon(mapTwo.tile[x2][y2].ship5_1HitTilted);
                                    } else if (a == 1) {
                                        mapTwo.tile[x2][y2].setIcon(mapTwo.tile[x2][y2].ship5_2HitTilted);
                                    } else if (a == 2) {
                                        mapTwo.tile[x2][y2].setIcon(mapTwo.tile[x2][y2].ship5_3HitTilted);
                                    } else if (a == 3) {
                                        mapTwo.tile[x2][y2].setIcon(mapTwo.tile[x2][y2].ship5_4HitTilted);
                                    } else if (a == 4) {
                                        mapTwo.tile[x2][y2].setIcon(mapTwo.tile[x2][y2].ship5_5HitTilted);
                                    }
                                }

                                //Effetto audio affondo
                                try {
                                    File file = new File("SunkSoundEffect.wav"); // Inserire il percorso del file audio clic
                                    Clip clip = AudioSystem.getClip();
                                    clip.open(AudioSystem.getAudioInputStream(file));
                                    clip.start();
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                            else{
                                //Effetto audio colpita (senza affondo)
                                try {
                                    File file = new File("HitSoundEffect.wav"); // Inserire il percorso del file audio clic
                                    Clip clip = AudioSystem.getClip();
                                    clip.open(AudioSystem.getAudioInputStream(file));
                                    clip.start();
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }

                                System.out.println("Nessuna barca affondata");
                            }
                        //Se non ho colpito una barca devo mettere l'icona corretta
                        } else if (str.equals("badHit")) {
                            //Comunico all'avversario che puó smettere di aspettare nuove coordinate
                            bufferOut.write("stopCycle");
                            bufferOut.newLine();
                            bufferOut.flush();

                            yourTurn = false;
                            clickAgain = false;

                            String[] coordinates = tileUsed.split(",");     //Splitto le coordinate
                            int x = Integer.parseInt(coordinates[0]);     //Converto la coordinata x in intero
                            int y = Integer.parseInt(coordinates[1]);     //Converto la coordinata y in intero

                            mapTwo.tile[x][y].setIcon(mapTwo.tile[x][y].badHit);

                            bufferOut.write("stopCycle");
                            bufferOut.newLine();
                            bufferOut.flush();
                        //Controllo se ho vinto
                        } else if (str.equals("win")) {
                            mapTwo.gameText.setText("Hai vinto");

                            new VictoryScreen(str);

                            //Chiudo il programma
                            try {
                                Thread.sleep(4000);
                                System.exit(0);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        } else {
                            clickAgain = true;
                        }
                    } while (clickAgain);

                    if (shipSunk != 0) {
                        mapTwo.shipSunkText.setText("Navi affondate: " + shipSunk);
                    }

                    tileUsed = null;

                //Gestisco il turno di gioco dell'avversario
                } else {
                    mapTwo.gameText.setText("Turno avversario!");

                    //Ripeto il ciclo in caso l'avversario abbia cliccato su una casella giá colpita in precedenza
                    do {
                        str = bufferIn.readLine();

                        //Se l'avversario ha cliccato su di una casella valida interrompo il ciclo
                        if (str.equals("stopCycle")) {
                            validHit = true;

                        //Controllo quali caselle sono state colpite
                        } else {
                            String[] coordinates = str.split(",");     //Splitto le coordinate
                            int x = Integer.parseInt(coordinates[0]);     //Converto la coordinata x in intero
                            int y = Integer.parseInt(coordinates[1]);     //Converto la coordinata y in intero

                            mapOne.tile[x][y].tileHit();
                        }
                    } while (!validHit);

                    validHit = false;
                    yourTurn = true;
                }
            }
        } catch (IOException e1) {
            //Avviso in caso di qualche problema con la connessione
            JOptionPane.showMessageDialog(this, "<html>Potresti aver riscontrato uno di questi problemi: <br><br>" +
                    "1) Il client potrebbe non essere stato avviato correttamente. <br>" +
                    "2) L'host potrebbe non essere stato trovato. <br>" +
                    "3) L'host potrebbe essersi disconnesso.</html>", "Attenzione qualcosa é andato storto", JOptionPane.ERROR_MESSAGE);

            //Chiudo l'intero programma
            System.exit(0);
        }
    }
}