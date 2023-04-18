package packet;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PlayerRole extends JFrame {
    Listener listener = new Listener(this);

    //Oggetti legati all'interfaccia
    Container c = this.getContentPane();

    JPanel frame = new JPanel();

    JPanel namePanel = new JPanel();
    JLabel textName = new JLabel();
    JTextField playerName = new JTextField();

    JLabel textRole = new JLabel();
    JPanel rolePanel = new JPanel();
    JRadioButton hostRole = new JRadioButton();
    JRadioButton clientRole = new JRadioButton();

    ButtonGroup buttons = new ButtonGroup();
    ButtonModel selectButtons;      //Tiene traccia del fatto che ci sia o meno un radio button cliccato

    JButton startButton = new JButton();
    public PlayerRole(){
        setTitle("PlayerRole");

        //Impostazione del layout e bordo del panel
        frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));
        frame.setBorder(new EmptyBorder(10,10,10,10));

        //Aggiunta della sezione di selezione nome
        textName.setText("Inserisci un nome:");
        namePanel.setLayout(new GridLayout(1,2,5,5));
        namePanel.setBorder(new EmptyBorder(0,10,20,10));
        namePanel.add(textName);
        namePanel.add(playerName);
        frame.add(namePanel);

        //Inserimento dei radio button dentro un gruppo
        buttons.add(hostRole);
        buttons.add(clientRole);

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

        //Aggiunta del bottone di inizio gioco
        startButton.setText("Comincia");
        startButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        frame.add(startButton);

        c.add(frame);   //Aggiunta del panel al content pane

        startButton.addActionListener(listener);    //Aggiunta di un action listener al bottone di inizio

        this.addWindowListener(listener);   //Aggiunta di un window listener al frame

        this.pack();
        this.setLocation(ScreenSize.getWidth() / 3, ScreenSize.getHeight() / 3);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }
}
