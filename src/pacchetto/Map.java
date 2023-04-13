package pacchetto;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Map extends JFrame{
    private final int dimension = 6;
    Container c = this.getContentPane();
    Listener listener = new Listener(this);

    ImageIcon image = new ImageIcon(new ImageIcon("images/seaBackground.png").getImage().getScaledInstance(290, 290, Image.SCALE_SMOOTH));

    JLabel background = new JLabel();

    JPanel grid = new JPanel(new GridLayout(dimension,dimension));

    Tile[][] tile = new Tile[dimension][dimension];

    public Map(){
        super("GameMap");

        background.setLayout(new BorderLayout());
        background.setIcon(image);

        //Creating the grid
        for(int i=0; i<dimension; i++){
            for (int j=0; j<dimension; j++) {
                tile[i][j] = new Tile();
                tile[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK,2));

                tile[i][j].setContentAreaFilled(false);

                tile[i][j].setActionCommand(String.valueOf(i) + "," + String.valueOf(j));
                tile[i][j].addActionListener(listener);
                tile[i][j].addMouseListener(listener);

                grid.setOpaque(false);
                grid.setBorder(new EmptyBorder(20,20,20,20));

                grid.add(tile[i][j]);
            }
        }

        background.add(grid,BorderLayout.CENTER);

        c.add(background);

        this.addWindowListener(listener);

        this.setSize(290,290);
        this.setLocation(ScreenSize.getWidth() / 3, ScreenSize.getHeight() / 3);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }
}
