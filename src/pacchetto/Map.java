package pacchetto;
import javax.swing.*;
import java.awt.*;

public class Map extends JFrame{
    private int dimension = 6;
    Container c = this.getContentPane();
    Listener listener = new Listener(this);

    JPanel grid = new JPanel(new GridLayout(6,6));

    JLabel[][] label = new JLabel[dimension][dimension]; //caselle della griglia
    Tile[][] tile = new Tile[dimension][dimension];

    public Map(){
        super("GameMap");

        for(int i=0; i<dimension; i++){
            for (int j=0; j<dimension; j++) {
                label[i][j] = new JLabel(i + "," + j);
                label[i][j].setBorder(BorderFactory.createLineBorder(Color.BLUE,2));
                grid.add(label[i][j]);
                tile[i][j] = new Tile(label[i][j]);
            }
        }

        c.add(grid, BorderLayout.CENTER);

        this.addWindowListener(listener);

        this.setSize(250,250);
        this.setLocation(ScreenSize.getWidth() / 3, ScreenSize.getHeight() / 3);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
