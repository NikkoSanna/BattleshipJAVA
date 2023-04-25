package packet;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;

public class Map extends JFrame {
    private final int dimension = 11;    //Per comoditá le dimensioni sono su una variabile di tipo int costante

    public boolean shipTwo_Sunk = false;    //Attributo che indica se la barca da 2 é affondata
    public boolean shipThree_Sunk = false;  //Attributo che indica se la barca da 3 é affondata
    public boolean shipFour_Sunk = false;   //Attributo che indica se la barca da 4 é affondata
    public boolean shipFive_Sunk = false;   //Attributo che indica se la barca da 5 é affondata

    boolean actuallyPlaying = false;    //Attributo che indica se si sta giocando o se si sta piazzando le navi

    private String shipType = "";     //Variabile che contiene il tipo di barca da posizionare

    //Array contenenti le coordinate delle barche
    String[] shipTwo_Tiles = new String[2];   //Array che contiene le coordinate della barca da 2
    String[] shipThree_Tiles = new String[3];   //Array che contiene le coordinate della barca da 3
    String[] shipFour_Tiles = new String[4]; //Array che contiene le coordinate della barca da 4
    String[] shipFive_Tiles = new String[5];  //Array che contiene le coordinate della barca da 5

    //Array che tiene traccia delle caselle occupate da barche che vengono colpite
    public int shipTwo_Iterator = 2;   //Intero che indica quante caselle della barca da 2 sono ancora operative
    public int shipThree_Iterator = 3; //Intero che indica quante caselle della barca da 3 sono ancora operative
    public int shipFour_Iterator = 4;  //Intero che indica quante caselle della barca da 4 sono ancora operative
    public int shipFive_Iterator = 5;  //Intero che indica quante caselle della barca da 5 sono ancora operative

    Listener listener = new Listener(this);

    //Immagini usate
    ImageIcon seaImage = new ImageIcon(new ImageIcon("images/backgrounds/seaBackground.png").getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH));
    ImageIcon captainImage = new ImageIcon(new ImageIcon("images/captainImage.png").getImage().getScaledInstance(60,60,Image.SCALE_SMOOTH));

    //Oggetti legati all'interfaccia
    Container c = this.getContentPane();
    JPanel upperBar = new JPanel();
    JLabel playerName = new JLabel();
    JLabel playerCharacter = new JLabel();
    JLabel shipSunkCounter = new JLabel();
    JLabel background = new JLabel();
    JPanel grid = new JPanel();
    Tile[][] tile = new Tile[dimension][dimension];     //JButton custom con aggiunta di attributi

    Font font;

    //La mappa deve essere a conoscenza della struttura del ship selector
    ShipSelector shipselect;


    public Map() {
        setTitle("GameMap");

        shipselect = new ShipSelector(this);   //La mappa genera il ship selector

        //Font da file esterno
        try{
            font  = Font.createFont(Font.TRUETYPE_FONT, new File("Font.ttf")).deriveFont(Font.PLAIN, 25);
        } catch (Exception e){}

        //Aggiunta font agli elementi
        playerName.setFont(font);
        shipSunkCounter.setFont(font.deriveFont(Font.PLAIN, 11));

        c.setLayout(new BoxLayout(c,BoxLayout.Y_AXIS));      //Impostazione del layout del content pane

        //Aggiunta dello sfondo a un label
        background.setLayout(new BorderLayout());
        background.setIcon(seaImage);

        //Aggiunta di una barra superiore al frame
        upperBar.setLayout(new GridLayout(1,3));
        shipSunkCounter.setText("Barche Affondate: 0");
        playerCharacter.setIcon(captainImage);
        playerName.setHorizontalAlignment(JLabel.CENTER);
        playerCharacter.setHorizontalAlignment(JLabel.CENTER);
        upperBar.add(playerName);
        upperBar.add(playerCharacter);
        upperBar.add(shipSunkCounter);

        background.add(upperBar, BorderLayout.NORTH);    //Aggiunta della barra superiore al content pane

        grid.setLayout(new GridLayout(dimension, dimension));    //Impostazione del layout della griglia di gioco

        //Creazione della griglia, da inserire in un panel
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {

                //Aggiunta di uno spazio vuoto in alto a sinistra
                if (i == 0 && j == 0) {
                    grid.add(new JLabel("", SwingConstants.CENTER));
                }
                //Numeratura prima riga con indici delle caselle
                else if (i == 0 && j != 0) {
                    grid.add(new JLabel(String.valueOf(j - 1), SwingConstants.CENTER));
                }
                //Numeratura prima colonna con indici delle caselle
                else if (j == 0 && i != 0) {
                    grid.add(new JLabel(String.valueOf(i - 1), SwingConstants.CENTER));
                }
                //Aggiunta delle caselle della mappa
                else {
                    tile[i][j] = new Tile(this, i, j);      //Popolamento matrice

                    //Comandi legati all'estetica delle caselle
                    tile[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                    tile[i][j].setContentAreaFilled(false);

                    grid.add(tile[i][j]);   //Aggiunta della casella alla griglia
                }
            }
        }
        //Comandi legati all'estetica della griglia
        grid.setOpaque(false);
        grid.setBorder(new EmptyBorder(20, 20, 20, 20));

        background.add(grid, BorderLayout.CENTER);   //Aggiunta della mappa di gioco al label principale

        c.add(background);      //Aggiunta dell'intera interfaccia al content pane


        this.addWindowListener(listener);   //Aggiunta di un window listener al frame

        //Impostazioni di visualizzazione
        this.pack();
        this.setLocation(ScreenSize.getWidth() / 2 - 525, ScreenSize.getHeight() / 3 - 250);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }

    //Setter del tipo di barca che sta venendo posizionato
    public void setShipType(String text) {
        shipType = text;
    }

    //Getter del tipo di barca che sta venendo posizionato
    public String getShipType() {
        return shipType;
    }

    //Getter della dimensione della griglia
    public int getDimension(){
        return dimension;
    }
}
