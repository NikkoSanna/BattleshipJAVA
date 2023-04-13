package pacchetto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Listener extends JFrame implements ActionListener, FocusListener, WindowListener, MouseListener {
    Menu menu;
    Map map;
    ShipSelector shipselect;

    //All the constructors
    public Listener(Menu page){
        menu = page;
    }
    public Listener(Map page){
        map = page;
    }
    public Listener(ShipSelector page){
        shipselect = page;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Metods for the menu
        try{
            if(e.getSource() == menu.new_game){
                Map map = new Map();
                menu.dispose();
            }
            if(e.getSource() == menu.old_game){
                Settings opt = new Settings();
            }
            if(e.getSource() == menu.settings){
                //TODO
            }
        }catch(Exception e1){
            System.out.println("Errore nell'action performed di menu");
        }

        //Metods for the shipSelector
        try{
            if(e.getSource() == shipselect.ship1){
                shipselect.ship1.setEnabled(false);
                shipselect.setVisible(false);
                map.shipType = e.getActionCommand();
            }
            if(e.getSource() == shipselect.ship2){
                //TODO
            }
            if(e.getSource() == shipselect.ship3){
                //TODO
            }
            if(e.getSource() == shipselect.ship4){
                //TODO
            }
        }catch(Exception e1){
            System.out.println("Errore nell'action performed di shipselector");
        }

        //Metods for the map
        try{
            String command = e.getActionCommand();
            System.out.println(e.getActionCommand());
            String[] indexes = command.split(",");

            int i = Integer.parseInt(indexes[0]);
            int j = Integer.parseInt(indexes[1]);

            System.out.println(i);
            System.out.println(j);

            map.tile[i][j].tileHit(i,j);
        }catch(Exception e1){
            System.out.println("Errore nell'action performed di map");
        }
    }

    @Override
    public void focusGained(FocusEvent e) {

    }

    @Override
    public void focusLost(FocusEvent e) {

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        int confirm = JOptionPane.showOptionDialog(this, "Sei sicuro di voler chiudere la pagina?", "Conferma", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (confirm == JOptionPane.YES_OPTION) {
            if(e.getSource() == menu)
                menu.dispose();
            else if(e.getSource() == map){
                map.dispose();
            }
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
