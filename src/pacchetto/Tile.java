package pacchetto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;

public class Tile extends JButton implements MouseListener {
    //Attributi della casella
    boolean hasShip;    //Attributo che indica se é presente o meno una parte di barca
    boolean isPlaceable = false;     //Attributo usato quando bisogna piazzare una barca (fase di piazzamento)
    boolean isHit = false;      //Attributo che indica se una barca é stata colpita (fase di gioco)
    int i;      //Coordinate ordinata
    int j;      //Coordinate ascissa




    Map map;    //La casella deve conoscere la mappa per permettere modifiche grafiche

    public Tile(Map page, int i, int j) {
        this.map = page;        //Ogni casella é a conoscenza dell'intera struttura dell mappa

        //Coordinate della casella
        this.i = i;
        this.j = j;

        addMouseListener(this);     //Aggiunta di un mouse listener
    }

    //Metodo che viene richiamato quando si clicca su una casella
    public void tileHit() {
        //Se quella casella non era stata cliccata allora posso procedere
        if (!isHit) {
            isHit = true;   //Imposto la casella come colpita

            //Se é presente una barca allora devo mostrare che questa é stata colpita
            if (hasShip) {
               setText("O");   //Imposto il testo della casella come O
                //Se non é presenta devo mostrare che quella casella é stata colpita ma a vuoto
            } else {
                setText("X");   //Imposto il testo della casella come X
            }
        }
    }

    public void placeShip(String shipType){
        //Se la barca selezionata é quella da 2
        if(shipType == "ship1"){
            setContentAreaFilled(true);
            setBackground(Color.GREEN);
            map.tile[i-1][j].setContentAreaFilled(true);
            map.tile[i-1][j].setBackground(Color.GREEN);

            hasShip = true;
            map.tile[i-1][j].hasShip = true;
        }
        //Se la barca selezionata é quella da 3
        else if(shipType == "ship2"){
            setContentAreaFilled(true);
            setBackground(Color.GREEN);
            map.tile[i-1][j].setContentAreaFilled(true);
            map.tile[i-1][j].setBackground(Color.GREEN);
            map.tile[i+1][j].setContentAreaFilled(true);
            map.tile[i+1][j].setBackground(Color.GREEN);

            hasShip = true;
            map.tile[i-1][j].hasShip = true;
            map.tile[i+1][j].hasShip = true;
        }
        //Se la barca selezionata é la terza
        else if(shipType == "ship3"){
            setContentAreaFilled(true);
            setBackground(Color.GREEN);
            map.tile[i-1][j].setContentAreaFilled(true);
            map.tile[i-1][j].setBackground(Color.GREEN);
            map.tile[i+1][j].setContentAreaFilled(true);
            map.tile[i+1][j].setBackground(Color.GREEN);
            map.tile[i-2][j].setContentAreaFilled(true);
            map.tile[i-2][j].setBackground(Color.GREEN);

            hasShip = true;
            map.tile[i-1][j].hasShip = true;
            map.tile[i+1][j].hasShip = true;
            map.tile[i-2][j].hasShip = true;
        }
        //Se la barca selezionata é quella da 5
        else if(shipType == "ship4"){
            setContentAreaFilled(true);
            setBackground(Color.GREEN);
            map.tile[i-1][j].setContentAreaFilled(true);
            map.tile[i-1][j].setBackground(Color.GREEN);
            map.tile[i+1][j].setContentAreaFilled(true);
            map.tile[i+1][j].setBackground(Color.GREEN);
            map.tile[i-2][j].setContentAreaFilled(true);
            map.tile[i-2][j].setBackground(Color.GREEN);
            map.tile[i+2][j].setContentAreaFilled(true);
            map.tile[i+2][j].setBackground(Color.GREEN);

            hasShip = true;
            map.tile[i-1][j].hasShip = true;
            map.tile[i+1][j].hasShip = true;
            map.tile[i-2][j].hasShip = true;
            map.tile[i+2][j].hasShip = true;

            //Inserimeno all'interno di un array delle coordinate che occupa la barca
            map.shipFive_Tiles[0] = (map.tile[i-2][j].i - 1) + "," + (map.tile[i-2][j].j - 1);
            map.shipFive_Tiles[1] = (map.tile[i-1][j].i - 1) + "," + (map.tile[i-1][j].j - 1);
            map.shipFive_Tiles[2] = (i - 1) + "," + (j - 1);
            map.shipFive_Tiles[3] = (map.tile[i+1][j].i - 1) + "," + (map.tile[i+1][j].j - 1);
            map.shipFive_Tiles[4] = (map.tile[i+2][j].i - 1) + "," + (map.tile[i+2][j].j - 1);
        }

        map.setShipType("");
        map.shipselect.setVisible(true);
        map.shipselect.killShipSelector();      //Se ho tutti i tasti dello ship selector non usabili lo chiudo
        actuallyPlaying = true;     //Passo alla fase di gioco
    }

    //Nella fase di piazzamento mi mostra dove sto piazzando le barche
    public void shipHovering(){
        try{
            //Se la barca selezionata é la prima
            if(map.getShipType().equals("ship1")){
                //Con questa condizione evito anche che vengano cancellati i colori
                if (i >= 1 && !hasShip && !map.tile[i - 1][j].hasShip) {
                    setContentAreaFilled(true);
                    setBackground(Color.BLUE);
                    map.tile[i - 1][j].setContentAreaFilled(true);
                    map.tile[i - 1][j].setBackground(Color.BLUE);

                    isPlaceable = true;
                    map.tile[i - 1][j].isPlaceable = true;

                    //Else if al posto di un normale else, perché altrimenti mi rimangono caselle rosse a caso per la mappa
                    //Cosí invece se passo sopra una nave non appaiono, ma solo se vado su coordinate non valide
                } else if (!(i >= 1)) {
                    setContentAreaFilled(true);
                    setBackground(Color.RED);
                }

            }
            //Se la barca selezionata é quella da 3
            else if(map.getShipType().equals("ship2")){
                //Con questa condizione evito anche che vengano cancellati i colori
                if (i >= 1 && i <= map.getDimension() - 1 && !hasShip && !map.tile[i - 1][j].hasShip && !map.tile[i + 1][j].hasShip) {
                    setContentAreaFilled(true);
                    setBackground(Color.BLUE);
                    map.tile[i - 1][j].setContentAreaFilled(true);
                    map.tile[i - 1][j].setBackground(Color.BLUE);
                    map.tile[i + 1][j].setContentAreaFilled(true);
                    map.tile[i + 1][j].setBackground(Color.BLUE);

                    isPlaceable = true;
                    map.tile[i - 1][j].isPlaceable = true;
                    map.tile[i + 1][j].isPlaceable = true;

                    //Else if al posto di un normale else, perché altrimenti mi rimangono caselle rosse a caso per la mappa
                    //Cosí invece se passo sopra una nave non appaiono, ma solo se vado su coordinate non valide
                } else if (!(i >= 1 && i <= map.getDimension() - 1)) {
                    setContentAreaFilled(true);
                    setBackground(Color.RED);
                }
            }
            //Se la barca selezionata é la quella da 4
            else if(map.getShipType().equals("ship3")){
                //Con questa condizione evito anche che vengano cancellati i colori
                if (i >= 2 && i <= map.getDimension() - 1 && !hasShip && !map.tile[i - 1][j].hasShip && !map.tile[i + 1][j].hasShip && !map.tile[i - 2][j].hasShip) {
                    setContentAreaFilled(true);
                    setBackground(Color.BLUE);
                    map.tile[i - 1][j].setContentAreaFilled(true);
                    map.tile[i - 1][j].setBackground(Color.BLUE);
                    map.tile[i + 1][j].setContentAreaFilled(true);
                    map.tile[i + 1][j].setBackground(Color.BLUE);
                    map.tile[i - 2][j].setContentAreaFilled(true);
                    map.tile[i - 2][j].setBackground(Color.BLUE);

                    isPlaceable = true;
                    map.tile[i - 1][j].isPlaceable = true;
                    map.tile[i + 1][j].isPlaceable = true;
                    map.tile[i - 2][j].isPlaceable = true;

                    //Else if al posto di un normale else, perché altrimenti mi rimangono caselle rosse a caso per la mappa
                    //Cosí invece se passo sopra una nave non appaiono, ma solo se vado su coordinate non valide
                } else if (!(i >= 2 && i <= map.getDimension() - 1)) {
                    setContentAreaFilled(true);
                    setBackground(Color.RED);
                }
            }
            //Se la barca selezionata é quella da 5
            else if(map.getShipType().equals("ship4")){
                //Con questa condizione evito anche che vengano cancellati i colori
                if (i >= 2 && i <= map.getDimension() - 2 && !hasShip && !map.tile[i - 1][j].hasShip && !map.tile[i + 1][j].hasShip && !map.tile[i - 2][j].hasShip && !map.tile[i + 2][j].hasShip) {
                    setContentAreaFilled(true);
                    setBackground(Color.BLUE);
                    map.tile[i - 1][j].setContentAreaFilled(true);
                    map.tile[i - 1][j].setBackground(Color.BLUE);
                    map.tile[i + 1][j].setContentAreaFilled(true);
                    map.tile[i + 1][j].setBackground(Color.BLUE);
                    map.tile[i - 2][j].setContentAreaFilled(true);
                    map.tile[i - 2][j].setBackground(Color.BLUE);
                    map.tile[i + 2][j].setContentAreaFilled(true);
                    map.tile[i + 2][j].setBackground(Color.BLUE);

                    isPlaceable = true;
                    map.tile[i - 1][j].isPlaceable = true;
                    map.tile[i + 1][j].isPlaceable = true;
                    map.tile[i - 2][j].isPlaceable = true;
                    map.tile[i + 2][j].isPlaceable = true;

                    //Else if al posto di un normale else, perché altrimenti mi rimangono caselle rosse a caso per la mappa
                    //Cosí invece se passo sopra una nave non appaiono, ma solo se vado su coordinate non valide
                } else if (!(i >= 2 && i <= map.getDimension() - 2)) {
                    setContentAreaFilled(true);
                    setBackground(Color.RED);
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
                if (map.getShipType().equals("ship1") && isPlaceable && map.tile[i - 1][j].isPlaceable) {
                    placeShip("ship1");
                } else if (map.getShipType().equals("ship2") && isPlaceable && map.tile[i - 1][j].isPlaceable && map.tile[i + 1][j].isPlaceable) {
                    placeShip("ship2");
                } else if (map.getShipType().equals("ship3") && isPlaceable && map.tile[i - 1][j].isPlaceable && map.tile[i + 1][j].isPlaceable && map.tile[i - 2][j].isPlaceable) {
                    placeShip("ship3");
                } else if (map.getShipType().equals("ship4") && isPlaceable && map.tile[i - 1][j].isPlaceable && map.tile[i + 1][j].isPlaceable && map.tile[i - 2][j].isPlaceable && map.tile[i + 2][j].isPlaceable) {
                    placeShip("ship4");
                }
            } catch (Exception ignored) {
            }
        }
        //Se sono nella fase di gioco richiamo il metodo tileHit
        else {
            tileHit();
        }
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
        //Se ho un tipo di barca da posizionare selezionato devo mostrare su quali caselle la sto per piazzare
        shipHovering();
    }

    //Se non ho posizionato la barca rimetto la grafica delle caselle come prima
    @Override
    public void mouseExited(MouseEvent e) {
        try {
            //Se la barca selezionata é la prima
            if (map.getShipType().equals("ship1")) {
                if (!hasShip) {
                    setContentAreaFilled(false);
                    setBackground(null);
                }
                if (!map.tile[i - 1][j].hasShip) {
                    map.tile[i - 1][j].setContentAreaFilled(false);
                    map.tile[i - 1][j].setBackground(null);
                }
            }
            //Se la barca selezionata é la seconda
            else if (map.getShipType().equals("ship2")) {
                if (!hasShip) {
                    setContentAreaFilled(false);
                    setBackground(null);
                }
                if (!map.tile[i - 1][j].hasShip && !map.tile[i + 1][j].hasShip) {
                    map.tile[i - 1][j].setContentAreaFilled(false);
                    map.tile[i + 1][j].setContentAreaFilled(false);
                    map.tile[i - 1][j].setBackground(null);
                    map.tile[i + 1][j].setBackground(null);
                }
            }
            //Se la barca selezionata é la terza
            else if (map.getShipType().equals("ship3")) {
                if (!hasShip) {
                    setContentAreaFilled(false);
                    setBackground(null);
                }
                if (!map.tile[i - 1][j].hasShip && !map.tile[i + 1][j].hasShip && !map.tile[i - 2][j].hasShip) {
                    map.tile[i - 1][j].setContentAreaFilled(false);
                    map.tile[i + 1][j].setContentAreaFilled(false);
                    map.tile[i - 2][j].setContentAreaFilled(false);
                    map.tile[i - 1][j].setBackground(null);
                    map.tile[i + 1][j].setBackground(null);
                    map.tile[i - 2][j].setBackground(null);
                }
            }
            //Se la barca selezionata é la quarta
            else if (map.getShipType().equals("ship4")) {
                if (!hasShip) {
                    setContentAreaFilled(false);
                    setBackground(null);
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
                }
            }
        } catch (Exception ignored) {
        }
    }
}
