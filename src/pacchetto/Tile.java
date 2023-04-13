package pacchetto;
import java.awt.*;
import javax.swing.*;

public class Tile {
    boolean hasShip;
    boolean isHit = false;
    JButton label;

    public Tile(JButton label){
        this.label = label;
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

}
