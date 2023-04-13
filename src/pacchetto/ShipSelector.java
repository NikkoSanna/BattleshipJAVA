package pacchetto;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ShipSelector extends JFrame {
    Container c = this.getContentPane();
    Listener listener = new Listener(this);

    //Ships icon images
    ImageIcon iconShip1 = new ImageIcon(new ImageIcon("images/ship1.png").getImage().getScaledInstance(125, 125, Image.SCALE_SMOOTH));
    ImageIcon iconShip2 = new ImageIcon(new ImageIcon("images/ship2.png").getImage().getScaledInstance(125, 125, Image.SCALE_SMOOTH));
    ImageIcon iconShip3 = new ImageIcon(new ImageIcon("images/ship3.png").getImage().getScaledInstance(125, 125, Image.SCALE_SMOOTH));
    ImageIcon iconShip4 = new ImageIcon(new ImageIcon("images/ship4.png").getImage().getScaledInstance(125, 125, Image.SCALE_SMOOTH));

    JPanel ships = new JPanel();
    JButton ship1 = new JButton();
    JButton ship2 = new JButton();
    JButton ship3 = new JButton();
    JButton ship4 = new JButton();

    public ShipSelector(){
        super("Ship Select");

        ships.setLayout(new GridLayout(2,2, 20,20));
        ships.setBorder(new EmptyBorder(20,20,20,20));

        //Adding icons to the labels
        ship1.setIcon(iconShip1);
        ship2.setIcon(iconShip2);
        ship3.setIcon(iconShip3);
        ship4.setIcon(iconShip4);

        //Adding the labels to the panel
        ships.add(ship1);
        ships.add(ship2);
        ships.add(ship3);
        ships.add(ship4);

        c.add(ships);

        ship1.setActionCommand("ship1");
        ship1.addActionListener(listener);
        ship1.setActionCommand("ship2");
        ship2.addActionListener(listener);
        ship1.setActionCommand("ship3");
        ship3.addActionListener(listener);
        ship1.setActionCommand("ship4");
        ship4.addActionListener(listener);

        this.pack();
        this.setLocation(ScreenSize.getWidth() / 2, ScreenSize.getHeight() / 2);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }
}
