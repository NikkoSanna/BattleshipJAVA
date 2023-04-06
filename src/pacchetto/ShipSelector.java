package pacchetto;
import javax.swing.*;
import java.awt.*;

public class ShipSelector extends JFrame {
    Container c = this.getContentPane();
    Listener listener = new Listener(this);

    JPanel ships = new JPanel();
    JPanel ship1 = new JPanel();
    JLabel ship2 = new JLabel();
    JLabel ship3= new JLabel();
    JLabel ship4 = new JLabel();

    public ShipSelector(){
        super("Ship Select");

        ships.setLayout(new GridLayout(2,2, 10,10));
        ships.add(ship1);
        ships.add(ship2);
        ships.add(ship3);
        ships.add(ship4);

        c.add(ships);

        this.addWindowListener(listener);
        this.addMouseListener(listener);

        this.setSize(250,250);
        this.setLocation(ScreenSize.getWidth() / 2, ScreenSize.getHeight() / 2);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }
}
