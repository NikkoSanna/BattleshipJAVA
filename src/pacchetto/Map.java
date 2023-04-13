package pacchetto;
import javax.swing.*;
import java.awt.*;

public class Map extends JFrame{
    private final int dimension = 6;
    Container c = this.getContentPane();
    Listener listener = new Listener(this);

    ImageIcon image = new ImageIcon(new ImageIcon("images/seaBackground.png").getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH));

    JLabel background = new JLabel();

    JPanel grid = new JPanel(new GridLayout(dimension,dimension));

    JButton[][] button = new JButton[dimension][dimension]; //caselle della griglia
    Tile[][] tile = new Tile[dimension][dimension];

    public Map(){
        super("GameMap");
        background.setSize(250,250);
        //background.setIcon(image);

        //Creating the grid
        for(int i=0; i<dimension; i++){
            for (int j=0; j<dimension; j++) {
                button[i][j] = new JButton(i + "," + j);
                button[i][j].setBorder(BorderFactory.createLineBorder(Color.BLUE,2));
                button[i][j].setActionCommand(String.valueOf(i) + "," + String.valueOf(j));
                button[i][j].addActionListener(listener);

                //grid.add(button[i][j]);
                tile[i][j] = new Tile(button[i][j]);     //Adding a Tile object to every label
            }
        }

        //background.add(grid);
        background.add(new JButton());
        c.add(background);

        this.addWindowListener(listener);

        this.setSize(250,250);
        this.setLocation(ScreenSize.getWidth() / 3, ScreenSize.getHeight() / 3);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }
}
