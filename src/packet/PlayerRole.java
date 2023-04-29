package packet;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;

public class PlayerRole extends JFrame {
    Listener listener = new Listener(this);

    //Immagini salvate
    ImageIcon seaImage = new ImageIcon(new ImageIcon("images/backgrounds/roleBackground.jpeg").getImage().getScaledInstance(612, 612, Image.SCALE_SMOOTH));

    //Oggetti legati all'interfaccia
    Container c = this.getContentPane();

    JPanel frame = new JPanel();

    JPanel namePanel = new JPanel();
    JLabel textName = new JLabel();
    JTextField playerName = new JTextField();

    JLabel textRole = new JLabel();
    JPanel rolePanel = new JPanel();
    JPanel rolePanelBottom = new JPanel();
    JButton hostRole = new JButton();
    JButton clientRole = new JButton();

    JLabel background = new JLabel();
    Font font;
    public PlayerRole(){
        setTitle("PlayerRole");

        //Font da file esterno
        try{
            font  = Font.createFont(Font.TRUETYPE_FONT, new File("Font.ttf")).deriveFont(Font.PLAIN, 20);
        } catch (Exception ignored){}

        //Aggiunta font agli elementi
        textName.setFont(font);
        playerName.setFont(font);
        textRole.setFont(font);
        hostRole.setFont(font);
        clientRole.setFont(font);

        c.setLayout(new BoxLayout(c,BoxLayout.Y_AXIS));      //Impostazione del layout del content pane

        //Aggiunta dello sfondo a un label
        background.setLayout(new FlowLayout());
        background.setIcon(seaImage);

        //Impostazione del layout e bordo del panel
        frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));
        frame.setBorder(new EmptyBorder(175,10,10,10));
        frame.setOpaque(false);

        //Aggiunta della sezione di selezione nome
        textName.setText("Inserisci un nome:");
        namePanel.setLayout(new GridLayout(1,2,5,5));
        namePanel.setBorder(new EmptyBorder(10,10,50,10));
        namePanel.add(textName);
        namePanel.add(playerName);
        frame.add(namePanel);

        //Aggiunta della sezione di selezione ruolo
        rolePanel.setLayout(new BoxLayout(rolePanel,BoxLayout.Y_AXIS));
        textRole.setText("Con quale ruolo vuoi giocare?");
        textRole.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        hostRole.setText("Host");
        clientRole.setText("Client");
        rolePanelBottom.setLayout(new FlowLayout(FlowLayout.CENTER));
        rolePanelBottom.setBorder(new EmptyBorder(10,10,10,10));
        rolePanelBottom.add(hostRole);
        rolePanelBottom.add(clientRole);
        rolePanel.add(textRole);
        rolePanel.add(rolePanelBottom);
        frame.add(rolePanel);

        background.add(frame);

        c.add(background);   //Aggiunta del panel al content pane

        //Aggiunta di action listener per i bottoni
        hostRole.addActionListener(listener);
        clientRole.addActionListener(listener);

        this.addWindowListener(listener);   //Aggiunta di un window listener al frame

        this.pack();
        this.setLocation(ScreenSize.getWidth() / 2 - 306, ScreenSize.getHeight() / 2 - 306);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }
}
