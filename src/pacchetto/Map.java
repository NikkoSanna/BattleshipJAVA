package pacchetto;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Map extends JFrame {
    private final int dimension = 11;    //Per comoditá le dimensioni sono su una variabile di tipo int costante

    public boolean shipTwo_Sunk = false;    //Attributo che indica se la barca da 2 é affondata
    public boolean shipThree_Sunk = false;  //Attributo che indica se la barca da 3 é affondata
    public boolean shipFour_Sunk = false;   //Attributo che indica se la barca da 4 é affondata
    public boolean shipFive_Sunk = false;   //Attributo che indica se la barca da 5 é affondata

    boolean actuallyPlaying;    //Attributo che indica se si sta giocando o se si sta piazzando le navi

    private String shipType = "";     //Variabile che contiene il tipo di barca da posizionare

    //Array contenenti le coordinate delle barche
    String[] shipTwo_Tiles = new String[2];   //Array che contiene le coordinate della barca da 2
    String[] shipThree_Tiles = new String[3];   //Array che contiene le coordinate della barca da 3
    String[] shipFour_Tiles = new String[4]; //Array che contiene le coordinate della barca da 4
    String[] shipFive_Tiles = new String[5];  //Array che contiene le coordinate della barca da 5

    //Array che tiene traccia delle caselle occupate da barche che vengono colpite
    int shipTwo_Iterator = 2;   //Intero che indica quante caselle della barca da 2 sono ancora operative
    int shipThree_Iterator = 3; //Intero che indica quante caselle della barca da 3 sono ancora operative
    int shipFour_Iterator = 4;  //Intero che indica quante caselle della barca da 4 sono ancora operative
    int shipFive_Iterator = 5;  //Intero che indica quante caselle della barca da 5 sono ancora operative

    Listener listener = new Listener(this);

    //Immagine di sfondo
    ImageIcon image = new ImageIcon(new ImageIcon("images/seaBackground.png").getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH));

    //Oggetti legati all'interfaccia
    Container c = this.getContentPane();
    JLabel background = new JLabel();
    JPanel grid = new JPanel();
    Tile[][] tile = new Tile[dimension][dimension];     //JButton custom con aggiunta di attributi


    //La mappa deve essere a conoscenza della struttura del ship selector
    ShipSelector shipselect;

    public Map() {
        setTitle("GameMap");

        //Aggiunta dello sfondo a un label
        background.setLayout(new BorderLayout());
        background.setIcon(image);

        //Impostazione del layout della griglia di gioco
        grid.setLayout(new GridLayout(dimension, dimension));

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

                    //Aggiunta di mouse listener a ogni casella
                    tile[i][j].addMouseListener(listener);

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
        this.setLocation(ScreenSize.getWidth() / 9, ScreenSize.getHeight() / 3 - 100);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        shipselect = new ShipSelector(this);   //La mappa genera il ship selector
    }

    //Setter del tipo di barca che sta venendo posizionato
     public void setShipType(String text){
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
