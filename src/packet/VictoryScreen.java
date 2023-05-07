package packet;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class VictoryScreen extends JFrame {
    //Immagini usate
    ImageIcon victoryImage = new ImageIcon(new ImageIcon("images/backgrounds/victoryImage.png").getImage().getScaledInstance(400, 500, Image.SCALE_SMOOTH));
    ImageIcon loseImage = new ImageIcon(new ImageIcon("images/backgrounds/loseImage.png").getImage().getScaledInstance(400, 500, Image.SCALE_SMOOTH));

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
            text.setText("Mi spiace ma hai perso");
            background.setIcon(victoryImage);
        } else {
            text.setText("Congratulazioni per aver vinto");
            background.setIcon(loseImage);
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
