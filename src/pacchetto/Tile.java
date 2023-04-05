package pacchetto;
import java.awt.*;
import javax.swing.*;

public class Tile {
    boolean hasShip;
    boolean isHit;
    JLabel label;

    public Tile(JLabel label){
        this.label = label;
        label.setText("ciao");
    }


}
