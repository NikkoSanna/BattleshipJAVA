package packet;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;

public class IP_Announcer extends JFrame {
    //Oggetti legati all'interfaccia
    Container c = this.getContentPane();

    JLabel textIP1 = new JLabel();
    JLabel textIP2 = new JLabel();

    Font font;

    public IP_Announcer(String ip) {
        setTitle("IP Announcer");

        c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));

        //Font da file esterno
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("Font.ttf")).deriveFont(Font.PLAIN, 18);
        } catch (Exception ignored) {}

        //Aggiunta del font agli elementi
        textIP1.setFont(font);
        textIP2.setFont(font);

        textIP1.setText("Comunica al giocatore avversario il");
        textIP2.setText("codice " + ip + " per poter giocare");
        textIP1.setBorder(new EmptyBorder(20, 20, 0, 20));
        textIP2.setBorder(new EmptyBorder(0, 20, 20, 20));

        c.add(textIP1);
        c.add(textIP2);

        //Impostazioni di visualizzazione
        this.pack();
        this.setLocation(ScreenSize.getWidth() / 3, ScreenSize.getHeight() / 2);
        this.setResizable(false);
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        this.setVisible(true);
    }
}
