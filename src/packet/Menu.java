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
    JLabel emptySpace = new JLabel();
    JLabel background = new JLabel();
    JLabel credits = new JLabel();
    Font font;

    public Menu() {
        setTitle("Menú");

        //Font da file esterno
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("Font.ttf")).deriveFont(Font.PLAIN, 35);
        } catch (Exception ignored) {}


        c.setLayout(new BorderLayout());      //Impostazione del layout del content pane

        //Aggiunta dello sfondo a un label
        background.setLayout(new FlowLayout());
        background.setIcon(seaImage);

        //Aspetto grafico della scritta dei credits
        credits.setText("by Corra and Nikko");
        credits.setHorizontalAlignment(JLabel.RIGHT);
        credits.setFont(font.deriveFont(Font.PLAIN, 15));

        emptySpace.setBorder(BorderFactory.createEmptyBorder(500,0,0,0));

        //Aspetto grafico del bottone
        play.setFont(font);
        play.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        play.setBackground(Color.WHITE);
        play.setOpaque(true);
        play.setPreferredSize(new Dimension(200, 70));

        background.add(emptySpace);
        background.add(play);   //Aggiunta del bottone al label
        background.add(emptySpace);

        c.add(background, BorderLayout.CENTER);       //Aggiunta del panel al content pane
        c.add(credits, BorderLayout.PAGE_END);      //Aggiunta della scritta dei credits

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
