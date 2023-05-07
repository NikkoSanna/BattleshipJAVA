package packet;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class VictoryScreen extends JFrame {
    //Immagini usate
    ImageIcon victoryImage = new ImageIcon(new ImageIcon("images/backgrounds/victoryImage.png").getImage());
    ImageIcon loseImage = new ImageIcon(new ImageIcon("images/backgrounds/loseImage.png").getImage());

    //Oggetti legati all'interfaccia
    Container c = this.getContentPane();

    JLabel background = new JLabel();
    JLabel text = new JLabel();

    Font font;

    public VictoryScreen(String str) {
        //Font da file esterno
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("Font.ttf")).deriveFont(Font.PLAIN, 18);
        } catch (Exception ignored) {}

        //Aggiunta dello sfondo a un label
        background.setLayout(new BorderLayout());

        //Aggiunta del font
        text.setFont(font);

        //Selezione scritta in base alla vittoria/sconfitta
        if (str.equals("lost")) {
            background.setIcon(loseImage);
        } else {
            background.setIcon(victoryImage);
        }

        background.add(text);

        c.add(background);

        //Impostazioni di visualizzazione
        this.pack();
        this.setLocation(ScreenSize.getWidth() / 2 - 250, ScreenSize.getHeight() / 2 - 250);
        this.setResizable(false);
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        this.setVisible(true);
    }
}
