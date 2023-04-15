package pacchetto;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Map extends JFrame{
    private final int dimension = 10;    //Per comoditá le dimensioni sono su una variabile di tipo int costante

    private String shipType="";     //Variabile che contiene il tipo di barca da posizionare

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

    public Map(){
        setTitle("GameMap");

        //Aggiunta dello sfondo a un label
        background.setLayout(new BorderLayout());
        background.setIcon(image);

        //Impostazione del layout della griglia di gioco
        grid.setLayout(new GridLayout(dimension,dimension));

        //Creazione della griglia, da inserire in un panel
        for(int i=0; i<dimension; i++){
            for (int j=0; j<dimension; j++) {
                //Ogni casella é a conoscenza del contenuto dell'intera mappa e delle sue coordinate
                tile[i][j] = new Tile(this, i ,j);

                //Comandi legati all'estetica delle caselle
                tile[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
                tile[i][j].setContentAreaFilled(false);

                //Aggiunta di action e mouse listener a ogni casella
                //Ogni bottone avrá come action command le sue coordinate, cosí da permettere di ottenerle piú avanti
                tile[i][j].setActionCommand(String.valueOf(i) + "," + String.valueOf(j));
                tile[i][j].addActionListener(listener);
                tile[i][j].addMouseListener(listener);

                //Comandi legati all'estetica della griglia
                grid.setOpaque(false);
                grid.setBorder(new EmptyBorder(20,20,20,20));

                grid.add(tile[i][j]);   //Aggiunta di ogni casella al panel
            }
        }

        background.add(grid,BorderLayout.CENTER);   //Aggiunta della mappa di gioco al label principale

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
