package pacchetto;

import javax.swing.*;
import java.awt.*;

public class Menu extends JFrame {
    Listener listener = new Listener(this);

    //Oggetti legati all'interfaccia
    Container c = this.getContentPane();
    JPanel panel = new JPanel(new GridLayout(3, 1));
    JButton new_game = new JButton("Nuova Partita");
    JButton old_game = new JButton("Continua partita (Non implementato)");
    JButton settings = new JButton("Impostazioni (Non implementato)");

    public Menu() {
        //Aggiunta dei bottoni al panel
        panel.add(new_game);
        panel.add(old_game);
        panel.add(settings);

        c.add(panel);   //Aggiunta del panel al content pane

        //Aggiunta di action listener per i bottoni
        new_game.addActionListener(listener);
        old_game.addActionListener(listener);
        settings.addActionListener(listener);

        this.addWindowListener(listener);   //Aggiunta di un window listener al frame

        //Impostazioni di visualizzazione
        this.setSize(250,250);
        this.setLocation(ScreenSize.getWidth() / 3, ScreenSize.getHeight() / 3);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }
}
