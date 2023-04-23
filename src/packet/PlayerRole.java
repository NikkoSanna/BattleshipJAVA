package packet;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PlayerRole extends JFrame {
    Listener listener = new Listener(this);

    //Immagini salvate
    ImageIcon seaImage = new ImageIcon(new ImageIcon("images/roleBackground.jpeg").getImage().getScaledInstance(612, 612, Image.SCALE_SMOOTH));

    //Oggetti legati all'interfaccia
    Container c = this.getContentPane();

    JPanel frame = new JPanel();

    JPanel namePanel = new JPanel();
    JLabel textName = new JLabel();
    JTextField playerName = new JTextField();

    JLabel textRole = new JLabel();
    JPanel rolePanel = new JPanel();
    JButton hostRole = new JButton();
    JButton clientRole = new JButton();

    JLabel background = new JLabel();

    public PlayerRole(){
        setTitle("PlayerRole");

        c.setLayout(new BoxLayout(c,BoxLayout.Y_AXIS));      //Impostazione del layout del content pane

        //Aggiunta dello sfondo a un label
        background.setLayout(new FlowLayout());
        background.setIcon(seaImage);

        //Impostazione del layout e bordo del panel
        frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));
        frame.setBorder(new EmptyBorder(150,10,10,10));

        //Aggiunta della sezione di selezione nome
        textName.setText("Inserisci un nome:");
        namePanel.setLayout(new GridLayout(1,2,5,5));
        namePanel.setBorder(new EmptyBorder(0,10,20,10));
        namePanel.add(textName);
        namePanel.add(playerName);
        frame.add(namePanel);

        //Aggiunta della sezione di selezione ruolo
        textRole.setText("Con quale ruolo vuoi giocare?");
        textRole.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        hostRole.setText("Host");
        clientRole.setText("Client");
        rolePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        rolePanel.setBorder(new EmptyBorder(10,10,10,10));
        rolePanel.add(hostRole);
        rolePanel.add(clientRole);
        frame.add(textRole);
        frame.add(rolePanel);

        background.add(frame);

        c.add(background);   //Aggiunta del panel al content pane

        hostRole.addActionListener(listener);
        clientRole.addActionListener(listener);

        this.addWindowListener(listener);   //Aggiunta di un window listener al frame

        this.pack();
        this.setLocation(ScreenSize.getWidth() / 3, ScreenSize.getHeight() / 4);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }
}
