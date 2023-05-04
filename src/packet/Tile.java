package packet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class Tile extends JButton implements MouseListener {
    //Attributi della casella
    boolean hasShip;    //Attributo che indica se é presente o meno una parte di barca
    boolean isHit = false;      //Attributo che indica se una parte di barca é stata colpita (fase di gioco)
    int i;      //Coordinate ordinata
    int j;      //Coordinate ascissa

    //Immagini salvate
    ImageIcon ship2_1 = new ImageIcon(new ImageIcon("images/ship2/ship2_1.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship2_2 = new ImageIcon(new ImageIcon("images/ship2/ship2_2.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));

    ImageIcon ship3_1 = new ImageIcon(new ImageIcon("images/ship3/ship3_1.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship3_2 = new ImageIcon(new ImageIcon("images/ship3/ship3_2.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship3_3 = new ImageIcon(new ImageIcon("images/ship3/ship3_3.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));

    ImageIcon ship4_1 = new ImageIcon(new ImageIcon("images/ship4/ship4_1.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship4_2 = new ImageIcon(new ImageIcon("images/ship4/ship4_2.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship4_3 = new ImageIcon(new ImageIcon("images/ship4/ship4_3.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship4_4 = new ImageIcon(new ImageIcon("images/ship4/ship4_4.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));

    ImageIcon ship5_1 = new ImageIcon(new ImageIcon("images/ship5/ship5_1.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship5_2 = new ImageIcon(new ImageIcon("images/ship5/ship5_2.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship5_3 = new ImageIcon(new ImageIcon("images/ship5/ship5_3.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship5_4 = new ImageIcon(new ImageIcon("images/ship5/ship5_4.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship5_5 = new ImageIcon(new ImageIcon("images/ship5/ship5_5.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));

    ImageIcon ship2_1Hit = new ImageIcon(new ImageIcon("images/ship2/ship2_1Hit.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship2_2Hit = new ImageIcon(new ImageIcon("images/ship2/ship2_2Hit.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));

    ImageIcon ship3_1Hit = new ImageIcon(new ImageIcon("images/ship3/ship3_1Hit.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship3_2Hit = new ImageIcon(new ImageIcon("images/ship3/ship3_2Hit.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship3_3Hit = new ImageIcon(new ImageIcon("images/ship3/ship3_3Hit.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));

    ImageIcon ship4_1Hit = new ImageIcon(new ImageIcon("images/ship4/ship4_1Hit.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship4_2Hit = new ImageIcon(new ImageIcon("images/ship4/ship4_2Hit.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship4_3Hit = new ImageIcon(new ImageIcon("images/ship4/ship4_3Hit.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship4_4Hit = new ImageIcon(new ImageIcon("images/ship4/ship4_4Hit.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));

    ImageIcon ship5_1Hit = new ImageIcon(new ImageIcon("images/ship5/ship5_1Hit.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship5_2Hit = new ImageIcon(new ImageIcon("images/ship5/ship5_2Hit.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship5_3Hit = new ImageIcon(new ImageIcon("images/ship5/ship5_3Hit.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship5_4Hit = new ImageIcon(new ImageIcon("images/ship5/ship5_4Hit.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship5_5Hit = new ImageIcon(new ImageIcon("images/ship5/ship5_5Hit.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));

    ImageIcon shipHit = new ImageIcon(new ImageIcon("images/shipHit.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon badHit = new ImageIcon(new ImageIcon("images/badHit.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));

    Map map;    //La casella deve conoscere la mappa per permettere modifiche grafiche

    public Tile(Map page, int i, int j) {
        this.map = page;        //Ogni casella é a conoscenza dell'intera struttura dell mappa

        //Coordinate della casella
        this.i = i;
        this.j = j;

        addMouseListener(this);     //Aggiunta di un mouse listener
    }


    //Se sto cliccando su una casella questa deve darmi un feedback visivo
    @Override
    public void mousePressed(MouseEvent e) {
        setContentAreaFilled(true);
        setBackground(Color.GRAY);
    }

    //Come rilascio la casella questa deve tornare visivamente come prima
    @Override
    public void mouseReleased(MouseEvent e) {
        setContentAreaFilled(false);
        setBackground(null);
    }


    @Override
    public void mouseEntered(MouseEvent e) {
        //Se ho un tipo di barca da posizionare selezionato devo mostrare su quali caselle la sto per piazzare (fase di piazzamento)
        if (!map.actuallyPlaying) {
            shipHovering();
        }
    }

    //Se non ho posizionato la barca rimetto la grafica delle caselle come prima
    @Override
    public void mouseExited(MouseEvent e) {
        try {
            //Se la barca selezionata é quella da 2
            if (map.getShipType().equals("ship2")) {
                if (!hasShip) {
                    setContentAreaFilled(false);
                    setBackground(null);
                    setIcon(null);
                }
                if (!map.tile[i - 1][j].hasShip) {
                    map.tile[i - 1][j].setContentAreaFilled(false);
                    map.tile[i - 1][j].setBackground(null);
                    map.tile[i - 1][j].setIcon(null);
                }
            }
            //Se la barca selezionata é quella da 3
            else if (map.getShipType().equals("ship3")) {
                if (!hasShip) {
                    setContentAreaFilled(false);
                    setBackground(null);
                    setIcon(null);
                }
                if (!map.tile[i - 1][j].hasShip && !map.tile[i + 1][j].hasShip) {
                    map.tile[i - 1][j].setContentAreaFilled(false);
                    map.tile[i + 1][j].setContentAreaFilled(false);
                    map.tile[i - 1][j].setBackground(null);
                    map.tile[i + 1][j].setBackground(null);
                    map.tile[i - 1][j].setIcon(null);
                    map.tile[i + 1][j].setIcon(null);
                }
            }
            //Se la barca selezionata é quella da 4
            else if (map.getShipType().equals("ship4")) {
                if (!hasShip) {
                    setContentAreaFilled(false);
                    setBackground(null);
                    setIcon(null);
                }
                if (!map.tile[i - 1][j].hasShip && !map.tile[i + 1][j].hasShip && !map.tile[i - 2][j].hasShip) {
                    map.tile[i - 1][j].setContentAreaFilled(false);
                    map.tile[i + 1][j].setContentAreaFilled(false);
                    map.tile[i - 2][j].setContentAreaFilled(false);
                    map.tile[i - 1][j].setBackground(null);
                    map.tile[i + 1][j].setBackground(null);
                    map.tile[i - 2][j].setBackground(null);
                    map.tile[i - 1][j].setIcon(null);
                    map.tile[i + 1][j].setIcon(null);
                    map.tile[i - 2][j].setIcon(null);
                }
            }
            //Se la barca selezionata é quella da 5
            else if (map.getShipType().equals("ship5")) {
                if (!hasShip) {
                    setContentAreaFilled(false);
                    setBackground(null);
                    setIcon(null);
                }
                if (!map.tile[i - 1][j].hasShip && !map.tile[i + 1][j].hasShip && !map.tile[i - 2][j].hasShip && !map.tile[i + 2][j].hasShip) {
                    map.tile[i - 1][j].setContentAreaFilled(false);
                    map.tile[i + 1][j].setContentAreaFilled(false);
                    map.tile[i - 2][j].setContentAreaFilled(false);
                    map.tile[i + 2][j].setContentAreaFilled(false);
                    map.tile[i - 1][j].setBackground(null);
                    map.tile[i + 1][j].setBackground(null);
                    map.tile[i - 2][j].setBackground(null);
                    map.tile[i + 2][j].setBackground(null);
                    map.tile[i - 1][j].setIcon(null);
                    map.tile[i + 1][j].setIcon(null);
                    map.tile[i - 2][j].setIcon(null);
                    map.tile[i + 2][j].setIcon(null);
                }
            }
        } catch (Exception ignored) {
        }
    }


    //Nella fase di piazzamento mi mostra dove sto piazzando le barche
    public void shipHovering() {
        try {
            //Se la barca selezionata é quella da 2
            if (map.getShipType().equals("ship2")) {
                //Con questa condizione evito anche che vengano cancellati i colori
                if (i >= 1 && !hasShip && !map.tile[i - 1][j].hasShip) {
                    setIcon(ship2_2);
                    map.tile[i - 1][j].setIcon(ship2_1);
                }
            }
            //Se la barca selezionata é quella da 3
            else if (map.getShipType().equals("ship3")) {
                //Con questa condizione evito anche che vengano cancellati i colori
                if (i >= 1 && i <= map.getDimension() - 1 && !hasShip && !map.tile[i - 1][j].hasShip && !map.tile[i + 1][j].hasShip) {
                    setIcon(ship3_2);
                    map.tile[i - 1][j].setIcon(ship3_1);
                    map.tile[i + 1][j].setIcon(ship3_3);
                }
            }
            //Se la barca selezionata é la quella da 4
            else if (map.getShipType().equals("ship4")) {
                //Con questa condizione evito anche che vengano cancellati i colori
                if (i >= 2 && i <= map.getDimension() - 1 && !hasShip && !map.tile[i - 1][j].hasShip && !map.tile[i + 1][j].hasShip && !map.tile[i - 2][j].hasShip) {
                    setIcon(ship4_3);
                    map.tile[i - 1][j].setIcon(ship4_2);
                    map.tile[i + 1][j].setIcon(ship4_4);
                    map.tile[i - 2][j].setIcon(ship4_1);
                }
            }
            //Se la barca selezionata é quella da 5
            else if (map.getShipType().equals("ship5")) {
                //Con questa condizione evito anche che vengano cancellati i colori
                if (i >= 2 && i <= map.getDimension() - 2 && !hasShip && !map.tile[i - 1][j].hasShip && !map.tile[i + 1][j].hasShip && !map.tile[i - 2][j].hasShip && !map.tile[i + 2][j].hasShip) {
                    setIcon(ship5_3);
                    map.tile[i - 1][j].setIcon(ship5_2);
                    map.tile[i + 1][j].setIcon(ship5_4);
                    map.tile[i - 2][j].setIcon(ship5_1);
                    map.tile[i + 2][j].setIcon(ship5_5);
                }
            }
        } catch (Exception ignored) {
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        //Se sono nella fase di piazzamento richiamo il metodo placeShip col tipo di barca corretto
        if (!map.actuallyPlaying) {
            try {
                if (map.getShipType().equals("ship2") && !hasShip && !map.tile[i - 1][j].hasShip) {
                    placeShip("ship2");
                } else if (map.getShipType().equals("ship3") && !hasShip && !map.tile[i - 1][j].hasShip && !map.tile[i + 1][j].hasShip) {
                    placeShip("ship3");
                } else if (map.getShipType().equals("ship4") && !hasShip && !map.tile[i - 1][j].hasShip && !map.tile[i + 1][j].hasShip && !map.tile[i - 2][j].hasShip) {
                    placeShip("ship4");
                } else if (map.getShipType().equals("ship5") && !hasShip && !map.tile[i - 1][j].hasShip && !map.tile[i + 1][j].hasShip && !map.tile[i - 2][j].hasShip && !map.tile[i + 2][j].hasShip) {
                    placeShip("ship5");
                }
            } catch (Exception ignored) {
            }
        }

        //Se sono nella fase di gioco invio le informazioni sulla casella cliccata all'altro giocatore
        else if (map.mapNumber.equals("mapTwo")) {
            if (map.client == null) {
                if (map.server.yourTurn) {
                    //Invio al client la posizione della casella colpita
                    try {
                        map.server.tileUsed = (i + "," + j);

                        map.server.bufferOut.write(map.server.tileUsed);
                        map.server.bufferOut.newLine();
                        map.server.bufferOut.flush();
                    } catch (IOException e1) {
                        throw new RuntimeException(e1);
                    }
                }
            } else {
                if (map.client.yourTurn) {
                    //Invio al server la posizione della casella colpita
                    try {
                        map.client.tileUsed = (i + "," + j);

                        map.client.bufferOut.write(map.client.tileUsed);
                        map.client.bufferOut.newLine();
                        map.client.bufferOut.flush();
                    } catch (IOException e1) {
                        throw new RuntimeException(e1);
                    }
                }
            }
        }
    }

    //Metodo che viene richiamato per il piazzamento della barca nella fase di piazzamento
    public void placeShip(String shipType) {
        //Se la barca selezionata é quella da 2
        if (shipType.equals("ship2")) {
            hasShip = true;
            map.tile[i - 1][j].hasShip = true;

            //Inserimento all'interno di un array delle coordinate che occupa la barca
            map.shipTwo_Tiles[0] = (map.tile[i - 1][j].i - 1) + "," + (map.tile[i - 1][j].j - 1);
            map.shipTwo_Tiles[1] = (i - 1) + "," + (j - 1);
        }
        //Se la barca selezionata é quella da 3
        else if (shipType.equals("ship3")) {
            hasShip = true;
            map.tile[i - 1][j].hasShip = true;
            map.tile[i + 1][j].hasShip = true;

            //Inserimento all'interno di un array delle coordinate che occupa la barca
            map.shipThree_Tiles[0] = (map.tile[i - 1][j].i - 1) + "," + (map.tile[i - 1][j].j - 1);
            map.shipThree_Tiles[1] = (i - 1) + "," + (j - 1);
            map.shipThree_Tiles[2] = (map.tile[i + 1][j].i - 1) + "," + (map.tile[i + 1][j].j - 1);
        }
        //Se la barca selezionata é la terza
        else if (shipType.equals("ship4")) {
            hasShip = true;
            map.tile[i - 1][j].hasShip = true;
            map.tile[i + 1][j].hasShip = true;
            map.tile[i - 2][j].hasShip = true;

            //Inserimento all'interno di un array delle coordinate che occupa la barca
            map.shipFour_Tiles[0] = (map.tile[i - 2][j].i - 1) + "," + (map.tile[i - 2][j].j - 1);
            map.shipFour_Tiles[1] = (map.tile[i - 1][j].i - 1) + "," + (map.tile[i - 1][j].j - 1);
            map.shipFour_Tiles[2] = (i - 1) + "," + (j - 1);
            map.shipFour_Tiles[3] = (map.tile[i + 1][j].i - 1) + "," + (map.tile[i + 1][j].j - 1);
        }
        //Se la barca selezionata é quella da 5
        else if (shipType.equals("ship5")) {
            hasShip = true;
            map.tile[i - 1][j].hasShip = true;
            map.tile[i + 1][j].hasShip = true;
            map.tile[i - 2][j].hasShip = true;
            map.tile[i + 2][j].hasShip = true;

            //Inserimento all'interno di un array delle coordinate che occupa la barca
            map.shipFive_Tiles[0] = (map.tile[i - 2][j].i - 1) + "," + (map.tile[i - 2][j].j - 1);
            map.shipFive_Tiles[1] = (map.tile[i - 1][j].i - 1) + "," + (map.tile[i - 1][j].j - 1);
            map.shipFive_Tiles[2] = (i - 1) + "," + (j - 1);
            map.shipFive_Tiles[3] = (map.tile[i + 1][j].i - 1) + "," + (map.tile[i + 1][j].j - 1);
            map.shipFive_Tiles[4] = (map.tile[i + 2][j].i - 1) + "," + (map.tile[i + 2][j].j - 1);
        }

        map.setShipType("");
        map.shipselect.setVisible(true);
        map.shipselect.killShipSelector();      //Se ho tutti i tasti dello ship selector non usabili lo chiude
    }


    //Metodo che viene richiamato quando si clicca su una casella
    public void tileHit() {
        //Se quella casella non era stata cliccata allora posso procedere
        if (!isHit) {
            isHit = true;   //Imposto la casella come colpita

            //Se é presente una barca allora devo mostrare che questa é stata colpita e informare l'avversario
            if (hasShip) {
                setIcon(shipHit);

                if (map.client == null) {
                    try {
                        map.server.bufferOut.write("goodHit");
                        map.server.bufferOut.newLine();
                        map.server.bufferOut.flush();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    try {
                        map.client.bufferOut.write("goodHit");
                        map.client.bufferOut.newLine();
                        map.client.bufferOut.flush();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

                //Controllo se é stata colpita una casella della barca da 2
                for (int a = 0; a < map.shipTwo_Tiles.length; a++) {
                    if (map.shipTwo_Tiles[a].equals((i - 1) + "," + (j - 1))) {
                        map.shipTwo_Iterator--;     //Decremento il contatore delle caselle operative

                        //Controllo se quella barca é stata affondata
                        if (map.shipTwo_Iterator == 0) {     //Se il contatore é arrivato a 0 allora la barca é stata affondata
                            map.shipTwo_Sunk = true;    //Imposto la barca come affondata

                            setIcon(ship2_2Hit);
                            map.tile[i - 1][j].setIcon(ship2_1Hit);

                            break;    //Evito ripetizioni inutili di controllo se la barca é affondata
                        }
                    }
                }
                //Controllo se é stata colpita una casella della barca da 3
                for (int a = 0; a < map.shipThree_Tiles.length; a++) {
                    if (map.shipThree_Tiles[a].equals((i - 1) + "," + (j - 1))) {
                        map.shipThree_Iterator--;     //Decremento il contatore delle caselle operative

                        //Controllo se quella barca é stata affondata
                        if (map.shipThree_Iterator == 0) {     //Se il contatore é arrivato a 0 allora la barca é stata affondata
                            map.shipThree_Sunk = true;    //Imposto la barca come affondata

                            for(int b = 0; b < map.shipThree_Tiles.length; b++){
                                String[] coordinates = map.shipThree_Tiles[b].split(",");     //Splitto le coordinate
                                int x = Integer.parseInt(coordinates[0]);     //Converto la coordinata x in intero
                                int y = Integer.parseInt(coordinates[1]);     //Converto la coordinata y in intero

                                if(b == 0){
                                    map.tile[x][y].setIcon(ship3_1Hit);
                                }else if(b == 1){
                                    map.tile[x][y].setIcon(ship3_2Hit);
                                }else if(b == 2){
                                    map.tile[x][y].setIcon(ship3_3Hit);
                                }
                            }

                            break;    //Evito ripetizioni inutili di controllo se la barca é affondata
                        }
                    }
                }
                //Controllo se é stata colpita una casella della barca da 4
                for (int a = 0; a < map.shipFour_Tiles.length; a++) {
                    if (map.shipFour_Tiles[a].equals((i - 1) + "," + (j - 1))) {
                        map.shipFour_Iterator--;     //Decremento il contatore delle caselle operative

                        //Controllo se quella barca é stata affondata
                        if (map.shipFour_Iterator == 0) {     //Se il contatore é arrivato a 0 allora la barca é stata affondata
                            map.shipFour_Sunk = true;    //Imposto la barca come affondata

                            setIcon(ship4_3Hit);
                            map.tile[i - 1][j].setIcon(ship4_2Hit);
                            map.tile[i + 1][j].setIcon(ship4_4Hit);
                            map.tile[i - 2][j].setIcon(ship4_1Hit);

                            break;    //Evito ripetizioni inutili di controllo se la barca é affondata
                        }
                    }
                }

                //Controllo se é stata colpita una casella della barca da 5
                for (int a = 0; a < map.shipFive_Tiles.length; a++) {
                    if (map.shipFive_Tiles[a].equals((i - 1) + "," + (j - 1))) {
                        map.shipFive_Iterator--;     //Decremento il contatore delle caselle operative

                        //Controllo se quella barca é stata affondata
                        if (map.shipFive_Iterator == 0) {     //Se il contatore é arrivato a 0 allora la barca é stata affondata
                            map.shipFive_Sunk = true;    //Imposto la barca come affondata

                            setIcon(ship5_3Hit);
                            map.tile[i - 1][j].setIcon(ship5_2Hit);
                            map.tile[i + 1][j].setIcon(ship5_4Hit);
                            map.tile[i - 2][j].setIcon(ship5_1Hit);
                            map.tile[i + 2][j].setIcon(ship5_5Hit);

                            break;    //Evito ripetizioni inutili di controllo se la barca é affondata
                        }
                    }
                }

                //Controllo se tutte le barche sono affondate
                if (map.shipTwo_Sunk && map.shipThree_Sunk && map.shipFour_Sunk && map.shipFive_Sunk) {
                    if (map.client == null) {
                        try {
                            map.server.bufferOut.write("win");
                            map.server.bufferOut.newLine();
                            map.server.bufferOut.flush();

                            new VictoryScreen("lost");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        try {
                            map.client.bufferOut.write("win");
                            map.client.bufferOut.newLine();
                            map.client.bufferOut.flush();

                            new VictoryScreen("lost");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            } else {        //Se non é presenta devo mostrare che quella casella é stata colpita ma a vuoto e informare l'avversario
                setIcon(badHit);
                if (map.client == null) {
                    try {
                        map.server.bufferOut.write("badHit");
                        map.server.bufferOut.newLine();
                        map.server.bufferOut.flush();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    try {
                        map.client.bufferOut.write("badHit");
                        map.client.bufferOut.newLine();
                        map.client.bufferOut.flush();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        } else {
            if (map.client == null) {
                try{
                    map.server.bufferOut.write("reClick");
                    map.server.bufferOut.newLine();
                    map.server.bufferOut.flush();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                try{
                    map.client.bufferOut.write("reClick");
                    map.client.bufferOut.newLine();
                    map.client.bufferOut.flush();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
