package pacchetto;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

public class Tile extends JButton implements MouseListener {
    //Attributi della casella
    boolean hasShip;
    boolean isHit = false;

    Map map;    //La casella deve conoscere la mappa per permettere modifiche grafiche

    public Tile(Map page){
        this.map = page;        //Ogni casella é a conoscenza dell'intera struttura dell mappa

        addMouseListener(this);     //Aggiunta di un mouse listener
    }

    //Metodo che viene richiamato quando si clicca su una casella (dalla classe Listener.java)
    public void tileHit(int i, int j){
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

    public void placeShip(){
        //TODO
    }

    @Override
    public void mouseClicked(MouseEvent e) {

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

    //Se ho un tipo di barca da posizionare selezionato devo mostrare su quali caselle la sto per piazzare
    @Override
    public void mouseEntered(MouseEvent e) {
        //System.out.println(map.getShipType());
        if(map.getShipType().equals("ship1")){
            System.out.println("aa");
            setContentAreaFilled(true);
            setBackground(Color.BLUE);
        }
    }

    //Se non ho posizionato la barca rimetto la grafica della casella come prima
    @Override
    public void mouseExited(MouseEvent e) {
        setContentAreaFilled(false);
    }
}
