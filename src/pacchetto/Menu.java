package pacchetto;

import javax.swing.*;
import java.awt.*;

public class Menu extends JFrame {
    Container c = this.getContentPane();
    Listener listener = new Listener(this);
    JPanel panel = new JPanel(new GridLayout(3, 1));
    JButton new_game = new JButton("Nuova Partita");
    JButton old_game = new JButton("Continua partita");
    JButton settings = new JButton("Impostazioni");

    public Menu() {
        new_game.addActionListener(listener);
        old_game.addActionListener(listener);
        settings.addActionListener(listener);

        this.addWindowListener(listener);

        panel.add(new_game);
        panel.add(old_game);
        panel.add(settings);

        c.add(panel);

        this.setSize(250,250);
        this.setLocation(ScreenSize.getWidth() / 3, ScreenSize.getHeight() / 3);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

    }
}
