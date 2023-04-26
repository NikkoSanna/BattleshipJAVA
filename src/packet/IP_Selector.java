package packet;

import javax.swing.*;
import java.awt.*;

public class IP_Selector extends JDialog {
    Listener listener = new Listener(this);
    JLabel message = new JLabel("Inserisci l'indirizzo IP del server");
    JTextField IP = new JTextField(20);
    JButton send = new JButton("Invia");
    JButton clear = new JButton("clear");

    boolean sent = false;

    JPanel buttons = new JPanel(new FlowLayout());

    public IP_Selector(JFrame parent){
        super(parent, "IP", true);

        Container c = getContentPane();
        setLayout(new GridLayout(3,1));

        buttons.add(send);
        send.setActionCommand("send");
        send.addActionListener(listener);
        buttons.add(clear);
        clear.addActionListener(listener);

        add(message);
        add(IP);
        add(buttons);

        if(sent) {
            getIP();
        }

        pack();
        setLocation(ScreenSize.getWidth() / 2, ScreenSize.getHeight() / 2);
        setResizable(false);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        setVisible(true);

    }
    public String getIP() {
        return IP.getText();
    }
}
