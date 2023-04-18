package packet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Listener extends JFrame implements ActionListener, FocusListener, WindowListener, MouseListener {
    //Il listener deve essere a conoscenza della classe che lo sta richiamando
    Menu menu;
    Map map;
    ShipSelector shipSelect;
    PlayerRole playerRole;

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
        shipSelect = page;
    }

    public Listener(PlayerRole page){
        playerRole = page;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Metodi richiamati dal menú
        try{
            //Apertura della mappa
            if(e.getSource() == menu.new_game){
                PlayerRole playerRole = new PlayerRole();
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
            if(e.getSource() == shipSelect.ship1){
                //Rendo il bottone della barca non piú usabile e nascondo lo ship selector
                shipSelect.ship1.setEnabled(false);
                shipSelect.setVisible(false);

                //Imposto il tipo di barca che andró a piazzare
                shipSelect.map.setShipType(e.getActionCommand());
            }
            //Se la barca selezionata é la seconda
            else if(e.getSource() == shipSelect.ship2){
                //Rendo il bottone della barca non piú usabile e nascondo lo ship selector
                shipSelect.ship2.setEnabled(false);
                shipSelect.setVisible(false);

                //Imposto il tipo di barca che andró a piazzare
                shipSelect.map.setShipType(e.getActionCommand());
            }
            //Se la barca selezionata é la terza
            else if(e.getSource() == shipSelect.ship3){
                //Rendo il bottone della barca non piú usabile e nascondo lo ship selector
                shipSelect.ship3.setEnabled(false);
                shipSelect.setVisible(false);

                //Imposto il tipo di barca che andró a piazzare
                shipSelect.map.setShipType(e.getActionCommand());
            }
            //Se la barca selezionata é la quarta
            else {
                //Rendo il bottone della barca non piú usabile e nascondo lo ship selector
                shipSelect.ship4.setEnabled(false);
                shipSelect.setVisible(false);

                //Imposto il tipo di barca che andró a piazzare
                shipSelect.map.setShipType(e.getActionCommand());
            }
        }catch(Exception e1){
            System.out.println("Errore nell'action performed di shipselector");
        }

        //Metodi richiamati dal selettore ruolo
        try{
            if(e.getSource() == playerRole.startButton){
                playerRole.selectButtons = playerRole.buttons.getSelection();
                if(playerRole.selectButtons != null && !(playerRole.playerName.getText().equals(""))){
                    Map mapOne = new Map();
                    Map mapTwo = new Map();
                    mapTwo.setLocation((ScreenSize.getWidth() / 2) + 25, (ScreenSize.getHeight() / 3) - 250);
                    mapTwo.shipselect.dispose();
                    mapOne.playerName.setText(playerRole.playerName.getText());
                    playerRole.dispose();
                    if(playerRole.hostRole.isSelected()){
                        Server server = new Server();
                    }else{
                        Client client = new Client();
                    }
                }
                else{
                    playerRole.playerName.setBackground(Color.RED);
                }
            }
        }catch (Exception e1){
            System.out.println("Errore nell'action performed di playerRole");
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
        int confirm = JOptionPane.showOptionDialog(this, "Sei sicuro di voler chiudere il gioco?", "Conferma", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
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
