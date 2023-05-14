package packet;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class ShipSelector extends JFrame {
    Map map;    //Il ship selector deve conoscere la mappa per permettere poi effettivamente di piazzare le barche

    Listener listener = new Listener(this);

    //Immagini relative alle barche

    ImageIcon iconShip2 = new ImageIcon(new ImageIcon("images/ship2/ship2.png").getImage().getScaledInstance(76,46, Image.SCALE_SMOOTH));
    ImageIcon iconShip3 = new ImageIcon(new ImageIcon("images/ship3/ship3.png").getImage().getScaledInstance(114, 46, Image.SCALE_SMOOTH));
    ImageIcon iconShip4 = new ImageIcon(new ImageIcon("images/ship4/ship4.png").getImage().getScaledInstance(152, 46, Image.SCALE_SMOOTH));
    ImageIcon iconShip5 = new ImageIcon(new ImageIcon("images/ship5/ship5.png").getImage().getScaledInstance(190, 46, Image.SCALE_SMOOTH));

    ImageIcon iconRotationX = new ImageIcon(new ImageIcon("images/shipExample.png").getImage().getScaledInstance(114, 46, Image.SCALE_SMOOTH));
    ImageIcon iconRotationY = new ImageIcon(new ImageIcon("images/shipExampleTilted.png").getImage().getScaledInstance(46, 114, Image.SCALE_SMOOTH));

    //Oggetti legati all'interfaccia
    Container c = this.getContentPane();

    JPanel ships = new JPanel();
    JButton ship2 = new JButton();
    JButton ship3 = new JButton();
    JButton ship4 = new JButton();
    JButton ship5 = new JButton();

    JPanel rotate = new JPanel();
    JButton tiltShip = new JButton();       //Bottone usato per ruotare la barca
    JLabel shipRotation = new JLabel();

    Font font;

    public ShipSelector(Map map) {
        this.map = map;     //Il ship selector é a conoscenza dell'intera struttura della mappa

        setTitle("Ship Select");

        //Font da file esterno
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("Font.ttf")).deriveFont(Font.PLAIN, 25);
        } catch (Exception ignored) {
        }

        c.setLayout(new FlowLayout());
        c.setBackground(Color.GRAY);

        //Impostazioni del layout barche
        ships.setLayout(new GridLayout(1, 4, 5, 0));
        ships.setOpaque(false);

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

        shipRotation.setIcon(iconRotationY);

        //Aggiungo i bottoni al panel
        ships.add(ship2);
        ships.add(ship3);
        ships.add(ship4);
        ships.add(ship5);
        ships.add(tiltShip);

        c.add(ships);   //Aggiunta del panel al content pane

        rotate.setLayout(new BoxLayout(rotate, BoxLayout.Y_AXIS));
        rotate.setPreferredSize(new Dimension(120, 160));
        rotate.setOpaque(false);

        tiltShip.setText("Ruota");
        tiltShip.setFont(font);
        tiltShip.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        tiltShip.setOpaque(true);
        tiltShip.setAlignmentX(Component.CENTER_ALIGNMENT);
        tiltShip.setBackground(Color.WHITE);

        shipRotation.setAlignmentX(Component.CENTER_ALIGNMENT);

        rotate.add(tiltShip);
        rotate.add(shipRotation);

        c.add(rotate);

        //Imposto degli action listener con relativi comandi ad ogni bottone
        ship2.setActionCommand("ship2");
        ship2.addActionListener(listener);

        ship3.setActionCommand("ship3");
        ship3.addActionListener(listener);

        ship4.setActionCommand("ship4");
        ship4.addActionListener(listener);

        ship5.setActionCommand("ship5");
        ship5.addActionListener(listener);

        tiltShip.addActionListener(listener);

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
