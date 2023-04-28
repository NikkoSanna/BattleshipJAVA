package packet;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;

public class IP_Selector extends JFrame{
    String playerName;

    Listener listener = new Listener(this);

    //Oggetti legati all'interfaccia
    Container c = this.getContentPane();

    JLabel textIP = new JLabel();
    JTextField IP = new JTextField(20);
    JButton send = new JButton();
    JButton clear = new JButton();
    JPanel buttons = new JPanel(new FlowLayout());
    JPanel frame = new JPanel();

    Font font;

    public IP_Selector(String playerName){
        setTitle("IP Selector");

        this.playerName = playerName;

        //Font da file esterno
        try{
            font  = Font.createFont(Font.TRUETYPE_FONT, new File("Font.ttf")).deriveFont(Font.PLAIN, 14);
        } catch (Exception ignored){}

        //Aggiunta del font agli elementi
        textIP.setFont(font);
        send.setFont(font);
        clear.setFont(font);
        IP.setFont(font.deriveFont(Font.PLAIN, 17));

        //Aggiunta layout del frame e un bordo invisibile
        frame.setLayout(new GridLayout(3,1, 10,10));
        frame.setBorder(new EmptyBorder(20,20,20,20));

        //Aggiunta delle richieste dell'indirizzo IPv4
        textIP.setText("Inserisci l indirizzo IP del server");
        frame.add(textIP);
        IP.setHorizontalAlignment(SwingConstants.CENTER);
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
        this.setSize(400,200);
        this.setLocation(ScreenSize.getWidth() / 2 - 200, ScreenSize.getHeight() / 2 - 100);
        this.setResizable(false);
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        this.setVisible(true);
    }
}
