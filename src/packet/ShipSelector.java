package packet;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ShipSelector extends JFrame {
    Map map;    //Il ship selector deve conoscere la mappa per permettere poi effettivamente di piazzare le barche

    Listener listener = new Listener(this);

    //Immagini relative alle barche

    ImageIcon iconShip2 = new ImageIcon(new ImageIcon("images/ship2/ship2.png").getImage());
    ImageIcon iconShip3 = new ImageIcon(new ImageIcon("images/ship3/ship3.png").getImage());
    ImageIcon iconShip4 = new ImageIcon(new ImageIcon("images/ship4/ship4.png").getImage());
    ImageIcon iconShip5 = new ImageIcon(new ImageIcon("images/ship5/ship5.png").getImage());

    //Oggetti legati all'interfaccia
    Container c = this.getContentPane();
    JPanel ships = new JPanel();
    JButton ship2 = new JButton();
    JButton ship3 = new JButton();
    JButton ship4 = new JButton();
    JButton ship5 = new JButton();

    public ShipSelector(Map map) {
        this.map = map;     //Il ship selector é a conoscenza dell'intera struttura della mappa

        setTitle("Ship Select");

        //Impostazioni dei layout
        ships.setLayout(new GridLayout(1, 4, 20, 20));
        ships.setBorder(new EmptyBorder(20, 20, 20, 20));
        ships.setBackground(Color.GRAY);

        //Comandi per l'estetica dei bottoni
        ship2.setContentAreaFilled(false);
        ship2.setBorderPainted(false);
        ship3.setContentAreaFilled(false);
        ship3.setBorderPainted(false);
        ship4.setContentAreaFilled(false);
        ship4.setBorderPainted(false);
        ship5.setContentAreaFilled(false);
        ship5.setBorderPainted(false);

        //Aggiungo le icone ai bottoni
        ship2.setIcon(iconShip2);
        ship3.setIcon(iconShip3);
        ship4.setIcon(iconShip4);
        ship5.setIcon(iconShip5);

        //Aggiungo i bottoni al panel
        ships.add(ship2);
        ships.add(ship3);
        ships.add(ship4);
        ships.add(ship5);

        c.add(ships);   //Aggiunta del panel al content pane


        //Imposto degli action listener con relativi comandi ad ogni bottone
        ship2.setActionCommand("ship2");
        ship2.addActionListener(listener);

        ship3.setActionCommand("ship3");
        ship3.addActionListener(listener);

        ship4.setActionCommand("ship4");
        ship4.addActionListener(listener);

        ship5.setActionCommand("ship5");
        ship5.addActionListener(listener);

        //Impostazioni di visualizzazione
        this.setSize(1063, 200);
        this.setLocation(ScreenSize.getWidth() / 2 - 525, ScreenSize.getHeight() / 3 + 345);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }

    //Se nessuno dei bottoni é piú funzionante viene chiuso definitivamente ship selector
    public void killShipSelector() {
        map.actuallyPlaying = true;     //Passo alla fase di gioco

        this.dispose();
    }
}
