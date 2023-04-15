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
    public Listener(ShipSelector page){
        shipselect = page;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Metodi richiamati dal menú
        try{
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
        }catch (Exception e1){
            System.out.println("Errore nell'action performed di menu");
        }

        //Metodi richiamati dal ship selector
        try{
            //Se la barca selezionata é la prima
            if(e.getSource() == shipselect.ship1){
                //Rendo il bottone della barca non piú usabile e nascondo lo ship selector
                shipselect.ship1.setEnabled(false);
                shipselect.setVisible(false);

                //Imposto il tipo di barca che andró a piazzare
                shipselect.map.setShipType(e.getActionCommand());
            }
            //Se la barca selezionata é la seconda
            else if(e.getSource() == shipselect.ship2){
                //Rendo il bottone della barca non piú usabile e nascondo lo ship selector
                shipselect.ship2.setEnabled(false);
                shipselect.setVisible(false);

                //Imposto il tipo di barca che andró a piazzare
                shipselect.map.setShipType(e.getActionCommand());
            }
            //Se la barca selezionata é la terza
            else if(e.getSource() == shipselect.ship3){
                //Rendo il bottone della barca non piú usabile e nascondo lo ship selector
                shipselect.ship3.setEnabled(false);
                shipselect.setVisible(false);

                //Imposto il tipo di barca che andró a piazzare
                shipselect.map.setShipType(e.getActionCommand());
            }
            //Se la barca selezionata é la quarta
            else {
                //Rendo il bottone della barca non piú usabile e nascondo lo ship selector
                shipselect.ship4.setEnabled(false);
                shipselect.setVisible(false);

                //Imposto il tipo di barca che andró a piazzare
                shipselect.map.setShipType(e.getActionCommand());
            }
        }catch(Exception e1){
            System.out.println("Errore nell'action performed di shipselector");
        }

        //Metodi richiamati dalla mappa

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
