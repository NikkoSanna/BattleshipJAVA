package pacchetto;
import java.awt.*;
import javax.swing.*;

public class Tile {
    boolean hasShip;
    boolean isHit = false;
    JLabel label;

    public Tile(JLabel label){
        this.label = label;
        label.setText("ciao");
    }

    public void tileHit(){
        if (isHit == false){
            isHit=true;
            if(hasShip == true){
               /*TODO
                   label.setIcon(RED);
                */
            }
        }
    }

}
