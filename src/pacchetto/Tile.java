package pacchetto;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

public class Tile extends JButton implements MouseListener {
    //Attributi della casella
    boolean hasShip;    //Attributo che indica se é presente o meno una barca
    boolean isPlaceble = false;     //Attributo usato quando bisogna piazzare una barca (fase di piazzamento)
    boolean isHit = false;      //Attributo che indica se una barca é stata colpita (fase di gioco)
    int i;      //Coordinate ordinata
    int j;      //Coordinate ascissa


    Map map;    //La casella deve conoscere la mappa per permettere modifiche grafiche

    public Tile(Map page, int i, int j){
        this.map = page;        //Ogni casella é a conoscenza dell'intera struttura dell mappa

        //Coordinate della casella
        this.i = i;
        this.j = j;

        addMouseListener(this);     //Aggiunta di un mouse listener
    }

    //Metodo che viene richiamato quando si clicca su una casella (dalla classe Listener.java)
    public void tileHit(){
        //Se quella casella non era stata cliccata allora posso procedere
        if (isHit == false){
            isHit = true;       //Imposto la casella come colpita

            //Se é presente una barca allora devo mostrare che questa é stata colpita
            if(hasShip == true){
               /*TODO
                   label.setIcon(RED_X);    //Un cerchio rosso con la X rappresenta una parte di nava colpita
               */
            //Se non é presenta devo mostrare che quella casella é stata colpita ma a vuoto
            }else{
                /*TODO
                   label.setIcon(BLACK_X);      //Un cerchio nero rappresenta un colpo andato a vuoto
               */
            }
        }
    }

    public void placeShip(String shipType){
        //Se la barca selezionata é la prima
        if(shipType == "ship1"){
            setContentAreaFilled(true);
            setBackground(Color.GREEN);
            map.tile[i-1][j].setContentAreaFilled(true);
            map.tile[i-1][j].setBackground(Color.GREEN);

            hasShip = true;
            map.tile[i-1][j].hasShip = true;
        }
        //Se la barca selezionata é la seconda
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
        //Se la barca selezionata é la quarta
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
        }

        map.setShipType("");
        map.shipselect.setVisible(true);
        map.shipselect.killShipSelector();      //Se ho tutti i tasti dello ship selector non usabili lo chiudo
    }

    //Nella fase di piazzamento mi mostra dove sto piazzando le barche
    public void shipHovering(){
        try{
            //Se la barca selezionata é la prima
            if(map.getShipType().equals("ship1")){
                //Con questa condizione evito anche che vengano cancellati i colori
                if(i >= 1 && hasShip == false && map.tile[i-1][j].hasShip == false){
                    setContentAreaFilled(true);
                    setBackground(Color.BLUE);
                    map.tile[i-1][j].setContentAreaFilled(true);
                    map.tile[i-1][j].setBackground(Color.BLUE);

                    isPlaceble = true;
                    map.tile[i-1][j].isPlaceble = true;

                //Else if al posto di un normale else, perché altrimenti mi rimangono caselle rosse a caso per la mappa
                //Cosí invece se passo sopra una nave non appaiono, ma solo se vado su coordinate non valide
                }else if((i >= 1) == false){
                    setContentAreaFilled(true);
                    setBackground(Color.RED);
                }

            }
            //Se la barca selezionata é la seconda
            else if(map.getShipType().equals("ship2")){
                //Con questa condizione evito anche che vengano cancellati i colori
                if(i >= 1 && i <= map.getDimension()-1 && hasShip == false && map.tile[i-1][j].hasShip == false && map.tile[i+1][j].hasShip == false){
                    setContentAreaFilled(true);
                    setBackground(Color.BLUE);
                    map.tile[i-1][j].setContentAreaFilled(true);
                    map.tile[i-1][j].setBackground(Color.BLUE);
                    map.tile[i+1][j].setContentAreaFilled(true);
                    map.tile[i+1][j].setBackground(Color.BLUE);

                    isPlaceble = true;
                    map.tile[i-1][j].isPlaceble = true;
                    map.tile[i+1][j].isPlaceble = true;

                //Else if al posto di un normale else, perché altrimenti mi rimangono caselle rosse a caso per la mappa
                //Cosí invece se passo sopra una nave non appaiono, ma solo se vado su coordinate non valide
                }else if((i >= 1 && i <= map.getDimension()-1) == false){
                    setContentAreaFilled(true);
                    setBackground(Color.RED);
                }
            }
            //Se la barca selezionata é la terza
            else if(map.getShipType().equals("ship3")){
                //Con questa condizione evito anche che vengano cancellati i colori
                if(i >= 2 && i <= map.getDimension()-1 && hasShip == false && map.tile[i-1][j].hasShip == false && map.tile[i+1][j].hasShip == false && map.tile[i-2][j].hasShip == false){
                    setContentAreaFilled(true);
                    setBackground(Color.BLUE);
                    map.tile[i-1][j].setContentAreaFilled(true);
                    map.tile[i-1][j].setBackground(Color.BLUE);
                    map.tile[i+1][j].setContentAreaFilled(true);
                    map.tile[i+1][j].setBackground(Color.BLUE);
                    map.tile[i-2][j].setContentAreaFilled(true);
                    map.tile[i-2][j].setBackground(Color.BLUE);

                    isPlaceble = true;
                    map.tile[i-1][j].isPlaceble = true;
                    map.tile[i+1][j].isPlaceble = true;
                    map.tile[i-2][j].isPlaceble = true;

                //Else if al posto di un normale else, perché altrimenti mi rimangono caselle rosse a caso per la mappa
                //Cosí invece se passo sopra una nave non appaiono, ma solo se vado su coordinate non valide
                }else if((i >= 2 && i <= map.getDimension()-1) == false){
                    setContentAreaFilled(true);
                    setBackground(Color.RED);
                }
            }
            //Se la barca selezionata é la quarta
            else if(map.getShipType().equals("ship4")){
                //Con questa condizione evito anche che vengano cancellati i colori
                if(i>=2 && i <= map.getDimension()-2 && hasShip == false && map.tile[i-1][j].hasShip == false && map.tile[i+1][j].hasShip == false && map.tile[i-2][j].hasShip == false && map.tile[i+2][j].hasShip == false){
                    setContentAreaFilled(true);
                    setBackground(Color.BLUE);
                    map.tile[i-1][j].setContentAreaFilled(true);
                    map.tile[i-1][j].setBackground(Color.BLUE);
                    map.tile[i+1][j].setContentAreaFilled(true);
                    map.tile[i+1][j].setBackground(Color.BLUE);
                    map.tile[i-2][j].setContentAreaFilled(true);
                    map.tile[i-2][j].setBackground(Color.BLUE);
                    map.tile[i+2][j].setContentAreaFilled(true);
                    map.tile[i+2][j].setBackground(Color.BLUE);

                    isPlaceble = true;
                    map.tile[i-1][j].isPlaceble = true;
                    map.tile[i+1][j].isPlaceble = true;
                    map.tile[i-2][j].isPlaceble = true;
                    map.tile[i+2][j].isPlaceble = true;

                //Else if al posto di un normale else, perché altrimenti mi rimangono caselle rosse a caso per la mappa
                //Cosí invece se passo sopra una nave non appaiono, ma solo se vado su coordinate non valide
                }else if((i>=2 && i <= map.getDimension()-2) == false){
                    setContentAreaFilled(true);
                    setBackground(Color.RED);
                }
            }
        }catch (Exception e1){}
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //Se sono nella fase di piazzamento richiamo il metodo placeShip col tipo di barca corretto
        try {
            if (map.getShipType().equals("ship1") && isPlaceble && map.tile[i-1][j].isPlaceble) {
                placeShip("ship1");
            }
            else if (map.getShipType().equals("ship2") && isPlaceble && map.tile[i-1][j].isPlaceble && map.tile[i+1][j].isPlaceble){
                placeShip("ship2");
            }
            else if (map.getShipType().equals("ship3") && isPlaceble && map.tile[i-1][j].isPlaceble && map.tile[i+1][j].isPlaceble && map.tile[i-2][j].isPlaceble){
                placeShip("ship3");
            }
            else if (map.getShipType().equals("ship4") && isPlaceble && map.tile[i-1][j].isPlaceble && map.tile[i+1][j].isPlaceble && map.tile[i-2][j].isPlaceble && map.tile[i+2][j].isPlaceble){
                placeShip("ship4");
            }
        }catch (Exception e1){}
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
        try{
            //Se la barca selezionata é la prima
            if(map.getShipType().equals("ship1")){
                setContentAreaFilled(false);
                map.tile[i-1][j].setContentAreaFilled(false);
            }
            //Se la barca selezionata é la seconda
            else if(map.getShipType().equals("ship2")){
                setContentAreaFilled(false);
                map.tile[i+1][j].setContentAreaFilled(false);
                map.tile[i-1][j].setContentAreaFilled(false);
            }
            //Se la barca selezionata é la terza
            else if(map.getShipType().equals("ship3")){
                setContentAreaFilled(false);
                map.tile[i+1][j].setContentAreaFilled(false);
                map.tile[i-1][j].setContentAreaFilled(false);
                map.tile[i-2][j].setContentAreaFilled(false);
            }
            //Se la barca selezionata é la quarta
            else if(map.getShipType().equals("ship4")){
                setContentAreaFilled(false);
                map.tile[i+1][j].setContentAreaFilled(false);
                map.tile[i-1][j].setContentAreaFilled(false);
                map.tile[i-2][j].setContentAreaFilled(false);
                map.tile[i+2][j].setContentAreaFilled(false);
            }
        }catch (Exception e1){}
    }
}
