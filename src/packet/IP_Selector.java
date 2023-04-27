package packet;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class IP_Selector extends JFrame{
    String playerName;

    Listener listener = new Listener(this);

    //Oggetti legati all'interfaccia
    Container c = this.getContentPane();

    JLabel message = new JLabel();
    JTextField IP = new JTextField(20);
    JButton send = new JButton();
    JButton clear = new JButton();
    JPanel buttons = new JPanel(new FlowLayout());
    JPanel frame = new JPanel();

    public IP_Selector(String playerName){
        setTitle("IP Selector");

        this.playerName = playerName;

        //Aggiunta layout del frame e un bordo invisibile
        frame.setLayout(new GridLayout(3,1, 10,10));
        frame.setBorder(new EmptyBorder(20,20,20,20));

        //Aggiunta delle richiesta dell'indirizzo IPv4
        message.setText("Inserisci l'indirizzo IP del server");
        frame.add(message);
        frame.add(IP);

        //Aggiunta dei bottoni invio e cancella
        send.setText("Invia");
        buttons.add(send);
        send.addActionListener(listener);
        clear.setText("Cancella");
        buttons.add(clear);
        clear.addActionListener(listener);

        frame.add(buttons);

        c.add(frame);

        //Impostazioni di visualizzazione
        this.pack();
        this.setLocation(ScreenSize.getWidth() / 2, ScreenSize.getHeight() / 2);
        this.setResizable(false);
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        this.setVisible(true);
    }
}
