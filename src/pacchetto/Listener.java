package pacchetto;

import javax.swing.*;
import java.awt.event.*;

public class Listener extends JFrame implements ActionListener, FocusListener, WindowListener, MouseListener {
    Menu menu;
    Map map;
    ShipSelector shipSelector;

    //All the constructors
    public Listener(Menu page){
        menu = page;
    }
    public Listener(Map page){
        map = page;
    }
    public Listener(ShipSelector page){
        shipSelector = page;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Metods for the menu
        if(e.getSource()==menu.new_game){
            Map map = new Map();
        }
        if(e.getSource()==menu.old_game){
            //TODO
        }
        if(e.getSource()==menu.settings){
            //TODO
        }

        //Metods for the shipSelector
        if(e.getSource()==shipSelector.ship1){
            //TODO
        }
        if(e.getSource()==shipSelector.ship2){
            //TODO
        }
        if(e.getSource()==shipSelector.ship3){
            //TODO
        }
        if(e.getSource()==shipSelector.ship4){
            //TODO
        }

        //Metods for the map

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
            if(e.getSource()==menu)
                menu.dispose();
            else if(e.getSource()==map){
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
