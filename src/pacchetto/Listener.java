package pacchetto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.EventObject;

public class Listener extends JFrame implements ActionListener, FocusListener, WindowListener, MouseListener {
    //Il listener deve essere a conoscenza della classe che lo sta richiamando
    Menu menu;
    Map map;
    ShipSelector shipselect;

    //Costruttore se il listener é richiamato dal menú
    public Listener(Menu page){
        menu = page;
    }

    //Costruttore se il listener é richiamato dalla mappa
    public Listener(Map page){
        map = page;
    }

    //Costruttore se il listener é richiamato dal ship selector (contiene pure la mappa)
    public Listener(ShipSelector shipselect){
        this.shipselect = shipselect;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Metodi richiamati dal menú
        if(e.getSource() == menu){

            //Apertura della mappa
            if(e.getSource() == menu.new_game){
                Map map = new Map();
                menu.dispose();
            }
            //Continuo della partita precedente
            else if(e.getSource() == menu.old_game){
                //Work in progress
            }
            //Apertura della pagina impostazioni
            else {
                //Settings opt = new Settings();
            }
        }

        //Metodi richiamati dal ship selector
        try{
            //Se la barca selezionata é la prima
            if(e.getSource() == shipselect.ship1){
                System.out.println("a");
                shipselect.ship1.setEnabled(false);
                shipselect.setVisible(false);
                System.out.println("a");
                map.setShipType("ship1");
                System.out.println(map.getShipType());
                System.out.println("a");
            }
            //Se la barca selezionata é la seconda
            else if(e.getSource() == shipselect.ship2){
                //TODO
            }
            //Se la barca selezionata é la terza
            else if(e.getSource() == shipselect.ship3){
                //TODO
            }
            //Se la barca selezionata é la quarta
            else if(e.getSource() == shipselect.ship4){
                //TODO
            }
        }catch(Exception e1){
            System.out.println("Errore nell'action performed di shipselector");
        }

        //Metodi richiamati dalla mappa
        try{
            String command = e.getActionCommand();
            //System.out.println(e.getActionCommand());
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

    //Apparizione di una finestra di conferma per la chiusura dei frame
    @Override
    public void windowClosing(WindowEvent e) {
        int confirm = JOptionPane.showOptionDialog(this, "Sei sicuro di voler chiudere la pagina?", "Conferma", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (confirm == JOptionPane.YES_OPTION) {
            if(e.getSource() == menu)
                System.exit(0);
            else if(e.getSource() == map){
                System.exit(0);
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
