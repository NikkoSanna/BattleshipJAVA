package packet;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;

public class Menu extends JFrame {
    Listener listener = new Listener(this);

    //Immagini salvate
    ImageIcon seaImage = new ImageIcon(new ImageIcon("images/backgrounds/menuBackground.jpg").getImage().getScaledInstance(612, 612, Image.SCALE_SMOOTH));

    //Oggetti legati all'interfaccia
    Container c = this.getContentPane();
    JPanel panel = new JPanel();
    JButton singleplayer = new JButton("Singleplayer"); //Non implementato
    JButton multiplayer = new JButton("Multiplayer");
    JButton settings = new JButton("Impostazioni");      //Non implementato

    JLabel background = new JLabel();
    Font font;

    public Menu() {
        setTitle("Menú");

        //Font da file esterno
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("Font.ttf")).deriveFont(Font.PLAIN, 35);
        } catch (Exception ignored) {}


        c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));      //Impostazione del layout del content pane

        //Aggiunta dello sfondo a un label
        background.setLayout(new FlowLayout());
        background.setIcon(seaImage);

        //Aspetto grafico dei bottoni
        singleplayer.setFont(font);
        singleplayer.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        singleplayer.setBackground(Color.WHITE);
        singleplayer.setOpaque(true);
        multiplayer.setFont(font);
        multiplayer.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        multiplayer.setBackground(Color.WHITE);
        multiplayer.setOpaque(true);
        settings.setFont(font);
        settings.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        settings.setBackground(Color.WHITE);
        settings.setOpaque(true);

        //Aggiunta dei bottoni al panel
        panel.setBorder(new EmptyBorder(250, 100, 100, 100));
        panel.setLayout(new GridLayout(3, 1, 20, 30));
        panel.setOpaque(false);
        panel.add(singleplayer);
        panel.add(multiplayer);
        panel.add(settings);

        background.add(panel);   //Aggiunta del panel al label

        c.add(background);       //Aggiunta del panel al content pane


        //Aggiunta di action listener per i bottoni
        singleplayer.addActionListener(listener);
        multiplayer.addActionListener(listener);
        settings.addActionListener(listener);

        this.addWindowListener(listener);   //Aggiunta di un window listener al frame

        //Impostazioni di visualizzazione
        this.pack();
        this.setLocation(ScreenSize.getWidth() / 2 - 306, ScreenSize.getHeight() / 2 - 306);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }
}
