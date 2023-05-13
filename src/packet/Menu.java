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
    JButton play = new JButton("Gioca");

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
        play.setFont(font);
        play.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        play.setBackground(Color.WHITE);
        play.setOpaque(true);

        background.add(play);   //Aggiunta del bottone al label

        c.add(background);       //Aggiunta del panel al content pane

        play.addActionListener(listener);   //Aggiunta di action listener per il bottone

        this.addWindowListener(listener);   //Aggiunta di un window listener al frame

        //Impostazioni di visualizzazione
        this.pack();
        this.setLocation(ScreenSize.getWidth() / 2 - 306, ScreenSize.getHeight() / 2 - 306);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }
}
