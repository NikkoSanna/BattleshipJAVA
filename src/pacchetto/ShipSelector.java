package pacchetto;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ShipSelector extends JFrame {
    Map map;    //Il ship selector deve conoscere la mappa per permettere poi effettivamente di piazzare le barche

    Listener listener = new Listener(this);

    //Immagini relative alle barche
    ImageIcon iconShip1 = new ImageIcon(new ImageIcon("images/ship1.png").getImage().getScaledInstance(125, 125, Image.SCALE_SMOOTH));
    ImageIcon iconShip2 = new ImageIcon(new ImageIcon("images/ship2.png").getImage().getScaledInstance(125, 125, Image.SCALE_SMOOTH));
    ImageIcon iconShip3 = new ImageIcon(new ImageIcon("images/ship3.png").getImage().getScaledInstance(125, 125, Image.SCALE_SMOOTH));
    ImageIcon iconShip4 = new ImageIcon(new ImageIcon("images/ship4.png").getImage().getScaledInstance(125, 125, Image.SCALE_SMOOTH));

    //Oggetti legati all'interfaccia
    Container c = this.getContentPane();
    JPanel ships = new JPanel();
    JButton ship1 = new JButton();
    JButton ship2 = new JButton();
    JButton ship3 = new JButton();
    JButton ship4 = new JButton();

    public ShipSelector(Map map){
        this.map = map;     //Il ship selector é a conoscenza dell'intera struttura della mappa

        setTitle("Ship Select");

        //Impostazioni dei layout
        ships.setLayout(new GridLayout(2,2, 20,20));
        ships.setBorder(new EmptyBorder(20,20,20,20));

        //Aggiungo le icone ai bottoni
        ship1.setIcon(iconShip1);
        ship2.setIcon(iconShip2);
        ship3.setIcon(iconShip3);
        ship4.setIcon(iconShip4);

        //Aggiungo i bottoni al panel
        ships.add(ship1);
        ships.add(ship2);
        ships.add(ship3);
        ships.add(ship4);

        c.add(ships);   //Aggiunta del panel al content pane

        //Imposto degli action listener con relativi comandi ad ogni bottone
        ship1.setActionCommand("ship1");
        ship1.addActionListener(listener);

        ship2.setActionCommand("ship2");
        ship2.addActionListener(listener);

        ship3.setActionCommand("ship3");
        ship3.addActionListener(listener);

        ship4.setActionCommand("ship4");
        ship4.addActionListener(listener);

        //Impostazioni di visualizzazione
        this.pack();
        this.setLocation(ScreenSize.getWidth() / 2, ScreenSize.getHeight() / 2);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }
}
