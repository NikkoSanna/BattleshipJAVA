package pacchetto;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

public class Tile extends JButton implements MouseListener {
    boolean hasShip;
    boolean isHit = false;

    public Tile(){
        addMouseListener(this);
    }

    public void tileHit(int i, int j){
        if (isHit == false){
            isHit=true;
            if(hasShip == true){
               /*TODO
                   label.setIcon(RED);
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

    @Override
    public void mousePressed(MouseEvent e) {
        setContentAreaFilled(true);
        setBackground(Color.GRAY);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        setContentAreaFilled(false);
        setBackground(null);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
