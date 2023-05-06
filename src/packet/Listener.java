package packet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Listener extends JFrame implements ActionListener, WindowListener {
    //Il listener deve essere a conoscenza della classe che lo sta richiamando
    Menu menu;
    Map map;
    ShipSelector shipSelect;
    PlayerRole playerRole;
    IP_Selector ipSelector;

    //Costruttore se il listener é richiamato dal menú
    public Listener(Menu page) {
        menu = page;
    }

    //Costruttore se il listener é richiamato dalla mappa
    public Listener(Map page) {
        map = page;
    }

    //Costruttore se il listener é richiamato dal ship selector (contiene pure la mappa)
    public Listener(ShipSelector page) {
        shipSelect = page;
    }

    public Listener(PlayerRole page) {
        playerRole = page;
    }

    public Listener(IP_Selector page) {
        ipSelector = page;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Metodi richiamati dal menú
        try {
            //Apertura della mappa
            if (e.getSource() == menu.singleplayer) {
                //Work in progress
            }
            //Continuo della partita precedente
            else if (e.getSource() == menu.multiplayer) {
                PlayerRole playerRole = new PlayerRole();
                menu.dispose();
            }
            //Apertura della pagina impostazioni
            else {
                //Settings opt = new Settings();
            }
        } catch (Exception ignored) {
        }

        //Metodi richiamati dal ship selector
        try {
            //Se la barca selezionata é la prima
            if (e.getSource() == shipSelect.ship2) {
                //Rendo il bottone della barca non piú usabile e nascondo lo ship selector
                shipSelect.ship2.setEnabled(false);
                shipSelect.setVisible(false);

                //Imposto il tipo di barca che andró a piazzare
                shipSelect.map.setShipType(e.getActionCommand());
            }
            //Se la barca selezionata é la seconda
            else if (e.getSource() == shipSelect.ship3) {
                //Rendo il bottone della barca non piú usabile e nascondo lo ship selector
                shipSelect.ship3.setEnabled(false);
                shipSelect.setVisible(false);

                //Imposto il tipo di barca che andró a piazzare
                shipSelect.map.setShipType(e.getActionCommand());
            }
            //Se la barca selezionata é la terza
            else if (e.getSource() == shipSelect.ship4) {
                //Rendo il bottone della barca non piú usabile e nascondo lo ship selector
                shipSelect.ship4.setEnabled(false);
                shipSelect.setVisible(false);

                //Imposto il tipo di barca che andró a piazzare
                shipSelect.map.setShipType(e.getActionCommand());
            }
            //Se la barca selezionata é la quarta
            else {
                //Rendo il bottone della barca non piú usabile e nascondo lo ship selector
                shipSelect.ship5.setEnabled(false);
                shipSelect.setVisible(false);

                //Imposto il tipo di barca che andró a piazzare
                shipSelect.map.setShipType(e.getActionCommand());
            }
        } catch (Exception ignored) {
        }

        //Metodi richiamati dal selettore ruolo
        try {
            if (e.getSource() == playerRole.hostRole || e.getSource() == playerRole.clientRole) {
                //Controllo se é stato inserito un nome
                if (!playerRole.playerName.getText().equals("")) {
                    String playerName = playerRole.playerName.getText();
                    playerRole.dispose();

                    //Avvio la partita da server
                    if (e.getSource() == playerRole.hostRole) {
                        //Uso il multithreading, altrimenti ottengo un freeze dell'interfaccia
                        Thread server = new Thread(new Server(playerName));
                        server.start();

                        //Avvio la partita da client
                    } else {
                        //Appare la richiesta di selezione IP
                        ipSelector = new IP_Selector(playerName);
                    }
                } else {
                    playerRole.playerName.setBackground(Color.RED);
                }
            }
        } catch (Exception ignored) {
        }

        //Metodi richiamati dalla mappa
        try {
            if (e.getSource() == map.ready) {
                map.bottomBar.remove(map.ready);
                map.bottomBar.remove(map.clear);
                map.repaint();      //Aggiorna l'interfaccia grafica

                map.bottomBar.add(map.gameText);
                map.gameText.setText("Questa e la tua mappa");

                if (map.client == null) {
                    map.server.mapTwo.gameText.setText("In attesa dell altro giocatore");

                    map.server.ready -= 1;      //Decremento il valore per capire se entrambi i giocatori sono pronti

                    map.server.bufferOut.write("ready");
                    map.server.bufferOut.newLine();
                    map.server.bufferOut.flush();
                } else {
                    map.client.loop = false;
                    map.client.mapTwo.gameText.setText("In attesa dell altro giocatore");

                    map.client.bufferOut.write("ready");
                    map.client.bufferOut.newLine();
                    map.client.bufferOut.flush();
                }
            } else if (e.getSource() == map.clear) {
                map.ready.setEnabled(false);
                for (int i = 1; i < map.getDimension(); i++) {
                    for (int j = 1; j < map.getDimension(); j++) {
                        map.tile[i][j].hasShip = false;
                        map.tile[i][j].setIcon(null);
                    }
                }

                map.shipselect.ship2.setEnabled(true);
                map.shipselect.ship3.setEnabled(true);
                map.shipselect.ship4.setEnabled(true);
                map.shipselect.ship5.setEnabled(true);
            }
        } catch (Exception ignored) {
        }

        //Metodi richiamati da IP Selector
        try {
            if (e.getSource() == ipSelector.send) {
                //Genero il client e inserisco l'indirizzo IPv4 del server
                if (!ipSelector.IP.getText().equals("")) {
                    ipSelector.setVisible(false);   //Scompare troppo tardi dallo schermo altrimenti

                    //Uso il multithreading, altrimenti ottengo un freeze dell'interfaccia
                    Thread client = new Thread(new Client(ipSelector.playerName, ipSelector.IP.getText()));
                    client.start();

                    ipSelector.dispose();   //Chiudo IP selector
                } else {
                    ipSelector.IP.setBackground(Color.RED);
                }
            } else {
                ipSelector.IP.setText(null);    //Cancello il testo dell'IP
            }
        } catch (Exception ignored) {
        }
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
    public void windowOpened(WindowEvent e) {
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
}
