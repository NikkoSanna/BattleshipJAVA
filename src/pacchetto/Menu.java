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
        panel.add(new_game);
        panel.add(old_game);
        panel.add(settings);
    }
}
