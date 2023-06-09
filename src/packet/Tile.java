package packet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class Tile extends JButton implements MouseListener {
    //Attributi della casella
    boolean hasShip;    //Attributo che indica se é presente o meno una parte di barca
    boolean isHit = false;      //Attributo che indica se una parte di barca é stata colpita (fase di gioco)
    boolean somethingSunk = false;      //Attributo usato per capire se c'é stato o meno un affondo

    int i;      //Coordinate ordinata
    int j;      //Coordinate ascissa

    //Immagini salvate

    ImageIcon ship2_1 = new ImageIcon(new ImageIcon("images/ship2/ship2_1.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship2_2 = new ImageIcon(new ImageIcon("images/ship2/ship2_2.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship2_1Tilted = new ImageIcon(new ImageIcon("images/ship2/ship2_1Tilted.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship2_2Tilted = new ImageIcon(new ImageIcon("images/ship2/ship2_2Tilted.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));

    ImageIcon ship3_1 = new ImageIcon(new ImageIcon("images/ship3/ship3_1.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship3_2 = new ImageIcon(new ImageIcon("images/ship3/ship3_2.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship3_3 = new ImageIcon(new ImageIcon("images/ship3/ship3_3.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship3_1Tilted = new ImageIcon(new ImageIcon("images/ship3/ship3_1Tilted.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship3_2Tilted = new ImageIcon(new ImageIcon("images/ship3/ship3_2Tilted.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship3_3Tilted = new ImageIcon(new ImageIcon("images/ship3/ship3_3Tilted.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));

    ImageIcon ship4_1 = new ImageIcon(new ImageIcon("images/ship4/ship4_1.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship4_2 = new ImageIcon(new ImageIcon("images/ship4/ship4_2.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship4_3 = new ImageIcon(new ImageIcon("images/ship4/ship4_3.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship4_4 = new ImageIcon(new ImageIcon("images/ship4/ship4_4.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship4_1Tilted = new ImageIcon(new ImageIcon("images/ship4/ship4_1Tilted.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship4_2Tilted = new ImageIcon(new ImageIcon("images/ship4/ship4_2Tilted.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship4_3Tilted = new ImageIcon(new ImageIcon("images/ship4/ship4_3Tilted.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship4_4Tilted = new ImageIcon(new ImageIcon("images/ship4/ship4_4Tilted.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));

    ImageIcon ship5_1 = new ImageIcon(new ImageIcon("images/ship5/ship5_1.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship5_2 = new ImageIcon(new ImageIcon("images/ship5/ship5_2.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship5_3 = new ImageIcon(new ImageIcon("images/ship5/ship5_3.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship5_4 = new ImageIcon(new ImageIcon("images/ship5/ship5_4.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship5_5 = new ImageIcon(new ImageIcon("images/ship5/ship5_5.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship5_1Tilted = new ImageIcon(new ImageIcon("images/ship5/ship5_1Tilted.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship5_2Tilted = new ImageIcon(new ImageIcon("images/ship5/ship5_2Tilted.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship5_3Tilted = new ImageIcon(new ImageIcon("images/ship5/ship5_3Tilted.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship5_4Tilted = new ImageIcon(new ImageIcon("images/ship5/ship5_4Tilted.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship5_5Tilted = new ImageIcon(new ImageIcon("images/ship5/ship5_5Tilted.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));

    ImageIcon ship2_1Hit = new ImageIcon(new ImageIcon("images/ship2/ship2_1Hit.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship2_2Hit = new ImageIcon(new ImageIcon("images/ship2/ship2_2Hit.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship2_1HitTilted = new ImageIcon(new ImageIcon("images/ship2/ship2_1HitTilted.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship2_2HitTilted = new ImageIcon(new ImageIcon("images/ship2/ship2_2HitTilted.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));

    ImageIcon ship3_1Hit = new ImageIcon(new ImageIcon("images/ship3/ship3_1Hit.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship3_2Hit = new ImageIcon(new ImageIcon("images/ship3/ship3_2Hit.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship3_3Hit = new ImageIcon(new ImageIcon("images/ship3/ship3_3Hit.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship3_1HitTilted = new ImageIcon(new ImageIcon("images/ship3/ship3_1HitTilted.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship3_2HitTilted = new ImageIcon(new ImageIcon("images/ship3/ship3_2HitTilted.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship3_3HitTilted = new ImageIcon(new ImageIcon("images/ship3/ship3_3HitTilted.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));

    ImageIcon ship4_1Hit = new ImageIcon(new ImageIcon("images/ship4/ship4_1Hit.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship4_2Hit = new ImageIcon(new ImageIcon("images/ship4/ship4_2Hit.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship4_3Hit = new ImageIcon(new ImageIcon("images/ship4/ship4_3Hit.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship4_4Hit = new ImageIcon(new ImageIcon("images/ship4/ship4_4Hit.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship4_1HitTilted = new ImageIcon(new ImageIcon("images/ship4/ship4_1HitTilted.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship4_2HitTilted = new ImageIcon(new ImageIcon("images/ship4/ship4_2HitTilted.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship4_3HitTilted = new ImageIcon(new ImageIcon("images/ship4/ship4_3HitTilted.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship4_4HitTilted = new ImageIcon(new ImageIcon("images/ship4/ship4_4HitTilted.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));

    ImageIcon ship5_1Hit = new ImageIcon(new ImageIcon("images/ship5/ship5_1Hit.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship5_2Hit = new ImageIcon(new ImageIcon("images/ship5/ship5_2Hit.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship5_3Hit = new ImageIcon(new ImageIcon("images/ship5/ship5_3Hit.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship5_4Hit = new ImageIcon(new ImageIcon("images/ship5/ship5_4Hit.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship5_5Hit = new ImageIcon(new ImageIcon("images/ship5/ship5_5Hit.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship5_1HitTilted = new ImageIcon(new ImageIcon("images/ship5/ship5_1HitTilted.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship5_2HitTilted = new ImageIcon(new ImageIcon("images/ship5/ship5_2HitTilted.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship5_3HitTilted = new ImageIcon(new ImageIcon("images/ship5/ship5_3HitTilted.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship5_4HitTilted = new ImageIcon(new ImageIcon("images/ship5/ship5_4HitTilted.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon ship5_5HitTilted = new ImageIcon(new ImageIcon("images/ship5/ship5_5HitTilted.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));

    ImageIcon shipHit = new ImageIcon(new ImageIcon("images/shipHit.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    ImageIcon badHit = new ImageIcon(new ImageIcon("images/badHit.png").getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH));

    Map map;    //La casella deve conoscere la mappa per permettere modifiche grafiche

    public Tile(Map page, int i, int j) {
        this.map = page;        //Ogni casella é a conoscenza dell'intera struttura dell mappa

        //Coordinate della casella
        this.i = i;
        this.j = j;

        addMouseListener(this);     //Aggiunta di un mouse listener
    }


    //Se sto cliccando su una casella questa deve darmi un feedback visivo
    @Override
    public void mousePressed(MouseEvent e) {
        setContentAreaFilled(true);
        setBackground(Color.GRAY);
    }

    //Come rilascio la casella questa deve tornare visivamente come prima
    @Override
    public void mouseReleased(MouseEvent e) {
        setContentAreaFilled(false);
        setBackground(null);
    }


    @Override
    public void mouseEntered(MouseEvent e) {
        //Se ho un tipo di barca da posizionare selezionato devo mostrare su quali caselle la sto per piazzare (fase di piazzamento)
        if (!map.actuallyPlaying) {
            shipHovering();
        }
    }

    //Se non ho posizionato la barca rimetto la grafica delle caselle come prima
    @Override
    public void mouseExited(MouseEvent e) {
        if (!map.tilted) {
            try {
                //Se la barca selezionata é quella da 2
                if (map.getShipType().equals("ship2")) {
                    if (!hasShip) {
                        setContentAreaFilled(false);
                        setBackground(null);
                        setIcon(null);
                    }
                    if (!map.tile[i - 1][j].hasShip) {
                        map.tile[i - 1][j].setContentAreaFilled(false);
                        map.tile[i - 1][j].setBackground(null);
                        map.tile[i - 1][j].setIcon(null);
                    }
                }
                //Se la barca selezionata é quella da 3
                else if (map.getShipType().equals("ship3")) {
                    if (!hasShip) {
                        setContentAreaFilled(false);
                        setBackground(null);
                        setIcon(null);
                    }
                    if (!map.tile[i - 1][j].hasShip && !map.tile[i + 1][j].hasShip) {
                        map.tile[i - 1][j].setContentAreaFilled(false);
                        map.tile[i + 1][j].setContentAreaFilled(false);
                        map.tile[i - 1][j].setBackground(null);
                        map.tile[i + 1][j].setBackground(null);
                        map.tile[i - 1][j].setIcon(null);
                        map.tile[i + 1][j].setIcon(null);
                    }
                }
                //Se la barca selezionata é quella da 4
                else if (map.getShipType().equals("ship4")) {
                    if (!hasShip) {
                        setContentAreaFilled(false);
                        setBackground(null);
                        setIcon(null);
                    }
                    if (!map.tile[i - 1][j].hasShip && !map.tile[i + 1][j].hasShip && !map.tile[i - 2][j].hasShip) {
                        map.tile[i - 1][j].setContentAreaFilled(false);
                        map.tile[i + 1][j].setContentAreaFilled(false);
                        map.tile[i - 2][j].setContentAreaFilled(false);
                        map.tile[i - 1][j].setBackground(null);
                        map.tile[i + 1][j].setBackground(null);
                        map.tile[i - 2][j].setBackground(null);
                        map.tile[i - 1][j].setIcon(null);
                        map.tile[i + 1][j].setIcon(null);
                        map.tile[i - 2][j].setIcon(null);
                    }
                }
                //Se la barca selezionata é quella da 5
                else if (map.getShipType().equals("ship5")) {
                    if (!hasShip) {
                        setContentAreaFilled(false);
                        setBackground(null);
                        setIcon(null);
                    }
                    if (!map.tile[i - 1][j].hasShip && !map.tile[i + 1][j].hasShip && !map.tile[i - 2][j].hasShip && !map.tile[i + 2][j].hasShip) {
                        map.tile[i - 1][j].setContentAreaFilled(false);
                        map.tile[i + 1][j].setContentAreaFilled(false);
                        map.tile[i - 2][j].setContentAreaFilled(false);
                        map.tile[i + 2][j].setContentAreaFilled(false);
                        map.tile[i - 1][j].setBackground(null);
                        map.tile[i + 1][j].setBackground(null);
                        map.tile[i - 2][j].setBackground(null);
                        map.tile[i + 2][j].setBackground(null);
                        map.tile[i - 1][j].setIcon(null);
                        map.tile[i + 1][j].setIcon(null);
                        map.tile[i - 2][j].setIcon(null);
                        map.tile[i + 2][j].setIcon(null);
                    }
                }
            } catch (Exception ignored) {
            }
        } else {
            try {
                //Se la barca selezionata é quella da 2
                if (map.getShipType().equals("ship2")) {
                    if (!hasShip) {
                        setContentAreaFilled(false);
                        setBackground(null);
                        setIcon(null);
                    }
                    if (!map.tile[i][j - 1].hasShip) {
                        map.tile[i][j - 1].setContentAreaFilled(false);
                        map.tile[i][j - 1].setBackground(null);
                        map.tile[i][j - 1].setIcon(null);
                    }
                }
                //Se la barca selezionata é quella da 3
                else if (map.getShipType().equals("ship3")) {
                    if (!hasShip) {
                        setContentAreaFilled(false);
                        setBackground(null);
                        setIcon(null);
                    }
                    if (!map.tile[i][j - 1].hasShip && !map.tile[i][j + 1].hasShip) {
                        map.tile[i][j - 1].setContentAreaFilled(false);
                        map.tile[i][j + 1].setContentAreaFilled(false);
                        map.tile[i][j - 1].setBackground(null);
                        map.tile[i][j + 1].setBackground(null);
                        map.tile[i][j - 1].setIcon(null);
                        map.tile[i][j + 1].setIcon(null);
                    }
                }
                //Se la barca selezionata é quella da 4
                else if (map.getShipType().equals("ship4")) {
                    if (!hasShip) {
                        setContentAreaFilled(false);
                        setBackground(null);
                        setIcon(null);
                    }
                    if (!map.tile[i][j - 1].hasShip && !map.tile[i][j + 1].hasShip && !map.tile[i][j - 2].hasShip) {
                        map.tile[i][j - 1].setContentAreaFilled(false);
                        map.tile[i][j + 1].setContentAreaFilled(false);
                        map.tile[i][j - 2].setContentAreaFilled(false);
                        map.tile[i][j - 1].setBackground(null);
                        map.tile[i][j + 1].setBackground(null);
                        map.tile[i][j - 2].setBackground(null);
                        map.tile[i][j - 1].setIcon(null);
                        map.tile[i][j + 1].setIcon(null);
                        map.tile[i][j - 2].setIcon(null);
                    }
                }
                //Se la barca selezionata é quella da 5
                else if (map.getShipType().equals("ship5")) {
                    if (!hasShip) {
                        setContentAreaFilled(false);
                        setBackground(null);
                        setIcon(null);
                    }
                    if (!map.tile[i][j - 1].hasShip && !map.tile[i][j + 1].hasShip && !map.tile[i][j - 2].hasShip && !map.tile[i][j + 2].hasShip) {
                        map.tile[i][j - 1].setContentAreaFilled(false);
                        map.tile[i][j + 1].setContentAreaFilled(false);
                        map.tile[i][j - 2].setContentAreaFilled(false);
                        map.tile[i][j + 2].setContentAreaFilled(false);
                        map.tile[i][j - 1].setBackground(null);
                        map.tile[i][j + 1].setBackground(null);
                        map.tile[i][j - 2].setBackground(null);
                        map.tile[i][j + 2].setBackground(null);
                        map.tile[i][j - 1].setIcon(null);
                        map.tile[i][j + 1].setIcon(null);
                        map.tile[i][j - 2].setIcon(null);
                        map.tile[i][j + 2].setIcon(null);
                    }
                }
            } catch (Exception ignored) {
            }
        }
    }


    //Nella fase di piazzamento mi mostra dove sto piazzando le barche
    public void shipHovering() {
        if (!map.tilted) {
            try {
                //Se la barca selezionata é quella da 2
                if (map.getShipType().equals("ship2")) {
                    //Con questa condizione evito anche che vengano cancellati i colori
                    if (i >= 1 && !hasShip && !map.tile[i - 1][j].hasShip) {
                        setIcon(ship2_2);
                        map.tile[i - 1][j].setIcon(ship2_1);
                    }
                }
                //Se la barca selezionata é quella da 3
                else if (map.getShipType().equals("ship3")) {
                    //Con questa condizione evito anche che vengano cancellati i colori
                    if (i >= 1 && i <= map.getDimension() - 1 && !hasShip && !map.tile[i - 1][j].hasShip && !map.tile[i + 1][j].hasShip) {
                        setIcon(ship3_2);
                        map.tile[i - 1][j].setIcon(ship3_1);
                        map.tile[i + 1][j].setIcon(ship3_3);
                    }
                }
                //Se la barca selezionata é la quella da 4
                else if (map.getShipType().equals("ship4")) {
                    //Con questa condizione evito anche che vengano cancellati i colori
                    if (i >= 2 && i <= map.getDimension() - 1 && !hasShip && !map.tile[i - 1][j].hasShip && !map.tile[i + 1][j].hasShip && !map.tile[i - 2][j].hasShip) {
                        setIcon(ship4_3);
                        map.tile[i - 1][j].setIcon(ship4_2);
                        map.tile[i + 1][j].setIcon(ship4_4);
                        map.tile[i - 2][j].setIcon(ship4_1);
                    }
                }
                //Se la barca selezionata é quella da 5
                else if (map.getShipType().equals("ship5")) {
                    //Con questa condizione evito anche che vengano cancellati i colori
                    if (i >= 2 && i <= map.getDimension() - 2 && !hasShip && !map.tile[i - 1][j].hasShip && !map.tile[i + 1][j].hasShip && !map.tile[i - 2][j].hasShip && !map.tile[i + 2][j].hasShip) {
                        setIcon(ship5_3);
                        map.tile[i - 1][j].setIcon(ship5_2);
                        map.tile[i + 1][j].setIcon(ship5_4);
                        map.tile[i - 2][j].setIcon(ship5_1);
                        map.tile[i + 2][j].setIcon(ship5_5);
                    }
                }
            } catch (Exception ignored) {
            }
        } else {
            try {
                //Se la barca selezionata é quella da 2
                if (map.getShipType().equals("ship2")) {
                    //Con questa condizione evito anche che vengano cancellati i colori
                    if (j >= 1 && !hasShip && !map.tile[i][j - 1].hasShip) {
                        setIcon(ship2_2Tilted);
                        map.tile[i][j - 1].setIcon(ship2_1Tilted);
                    }
                }
                //Se la barca selezionata é quella da 3
                else if (map.getShipType().equals("ship3")) {
                    //Con questa condizione evito anche che vengano cancellati i colori
                    if (j >= 1 && j <= map.getDimension() - 1 && !hasShip && !map.tile[i][j - 1].hasShip && !map.tile[i][j + 1].hasShip) {
                        setIcon(ship3_2Tilted);
                        map.tile[i][j - 1].setIcon(ship3_1Tilted);
                        map.tile[i][j + 1].setIcon(ship3_3Tilted);
                    }
                }
                //Se la barca selezionata é la quella da 4
                else if (map.getShipType().equals("ship4")) {
                    //Con questa condizione evito anche che vengano cancellati i colori
                    if (j >= 2 && j <= map.getDimension() - 1 && !hasShip && !map.tile[i][j - 1].hasShip && !map.tile[i][j + 1].hasShip && !map.tile[i][j - 2].hasShip) {
                        setIcon(ship4_3Tilted);
                        map.tile[i][j - 1].setIcon(ship4_2Tilted);
                        map.tile[i][j + 1].setIcon(ship4_4Tilted);
                        map.tile[i][j - 2].setIcon(ship4_1Tilted);
                    }
                }
                //Se la barca selezionata é quella da 5
                else if (map.getShipType().equals("ship5")) {
                    //Con questa condizione evito anche che vengano cancellati i colori
                    if (j >= 2 && j <= map.getDimension() - 2 && !hasShip && !map.tile[i][j - 1].hasShip && !map.tile[i][j + 1].hasShip && !map.tile[i][j - 2].hasShip && !map.tile[i][j + 2].hasShip) {
                        setIcon(ship5_3Tilted);
                        map.tile[i][j - 1].setIcon(ship5_2Tilted);
                        map.tile[i][j + 1].setIcon(ship5_4Tilted);
                        map.tile[i][j - 2].setIcon(ship5_1Tilted);
                        map.tile[i][j + 2].setIcon(ship5_5Tilted);
                    }
                }
            } catch (Exception ignored) {
            }
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        //Se sono nella fase di piazzamento richiamo il metodo placeShip col tipo di barca corretto
        if (!map.actuallyPlaying) {
            if (!map.tilted) {
                try {
                    if (map.getShipType().equals("ship2") && !hasShip && !map.tile[i - 1][j].hasShip) {
                        placeShip("ship2");
                    } else if (map.getShipType().equals("ship3") && !hasShip && !map.tile[i - 1][j].hasShip && !map.tile[i + 1][j].hasShip) {
                        placeShip("ship3");
                    } else if (map.getShipType().equals("ship4") && !hasShip && !map.tile[i - 1][j].hasShip && !map.tile[i + 1][j].hasShip && !map.tile[i - 2][j].hasShip) {
                        placeShip("ship4");
                    } else if (map.getShipType().equals("ship5") && !hasShip && !map.tile[i - 1][j].hasShip && !map.tile[i + 1][j].hasShip && !map.tile[i - 2][j].hasShip && !map.tile[i + 2][j].hasShip) {
                        placeShip("ship5");
                    }
                } catch (Exception ignored) {
                }
            } else {
                try {
                    if (map.getShipType().equals("ship2") && !hasShip && !map.tile[i][j - 1].hasShip) {
                        placeShip("ship2");
                    } else if (map.getShipType().equals("ship3") && !hasShip && !map.tile[i][j - 1].hasShip && !map.tile[i][j + 1].hasShip) {
                        placeShip("ship3");
                    } else if (map.getShipType().equals("ship4") && !hasShip && !map.tile[i][j - 1].hasShip && !map.tile[i][j + 1].hasShip && !map.tile[i][j - 2].hasShip) {
                        placeShip("ship4");
                    } else if (map.getShipType().equals("ship5") && !hasShip && !map.tile[i][j - 1].hasShip && !map.tile[i][j + 1].hasShip && !map.tile[i][j - 2].hasShip && !map.tile[i][j + 2].hasShip) {
                        placeShip("ship5");
                    }
                } catch (Exception ignored) {
                }
            }
        }

        //Se sono nella fase di gioco invio le informazioni sulla casella cliccata all'altro giocatore
        else if (map.mapNumber.equals("mapTwo")) {
            if (map.client == null) {
                if (map.server.yourTurn) {
                    //Invio al client la posizione della casella colpita
                    try {
                        map.server.tileUsed = (i + "," + j);

                        map.server.bufferOut.write(map.server.tileUsed);
                        map.server.bufferOut.newLine();
                        map.server.bufferOut.flush();
                    } catch (IOException e1) {
                        throw new RuntimeException(e1);
                    }
                }
            } else {
                if (map.client.yourTurn) {
                    //Invio al server la posizione della casella colpita
                    try {
                        map.client.tileUsed = (i + "," + j);

                        map.client.bufferOut.write(map.client.tileUsed);
                        map.client.bufferOut.newLine();
                        map.client.bufferOut.flush();
                    } catch (IOException e1) {
                        throw new RuntimeException(e1);
                    }
                }
            }
        }
    }

    //Metodo che viene richiamato per il piazzamento della barca nella fase di piazzamento
    public void placeShip(String shipType) {
        if (!map.tilted) {
            //Se la barca selezionata é quella da 2
            if (shipType.equals("ship2")) {
                hasShip = true;
                map.tile[i - 1][j].hasShip = true;

                //Inserimento all'interno di un array delle coordinate che occupa la barca
                map.shipTwo_Tiles[0] = (map.tile[i - 1][j].i - 1) + "," + (map.tile[i - 1][j].j - 1);
                map.shipTwo_Tiles[1] = (i - 1) + "," + (j - 1);
            }
            //Se la barca selezionata é quella da 3
            else if (shipType.equals("ship3")) {
                hasShip = true;
                map.tile[i - 1][j].hasShip = true;
                map.tile[i + 1][j].hasShip = true;

                //Inserimento all'interno di un array delle coordinate che occupa la barca
                map.shipThree_Tiles[0] = (map.tile[i - 1][j].i - 1) + "," + (map.tile[i - 1][j].j - 1);
                map.shipThree_Tiles[1] = (i - 1) + "," + (j - 1);
                map.shipThree_Tiles[2] = (map.tile[i + 1][j].i - 1) + "," + (map.tile[i + 1][j].j - 1);
            }
            //Se la barca selezionata é la terza
            else if (shipType.equals("ship4")) {
                hasShip = true;
                map.tile[i - 1][j].hasShip = true;
                map.tile[i + 1][j].hasShip = true;
                map.tile[i - 2][j].hasShip = true;

                //Inserimento all'interno di un array delle coordinate che occupa la barca
                map.shipFour_Tiles[0] = (map.tile[i - 2][j].i - 1) + "," + (map.tile[i - 2][j].j - 1);
                map.shipFour_Tiles[1] = (map.tile[i - 1][j].i - 1) + "," + (map.tile[i - 1][j].j - 1);
                map.shipFour_Tiles[2] = (i - 1) + "," + (j - 1);
                map.shipFour_Tiles[3] = (map.tile[i + 1][j].i - 1) + "," + (map.tile[i + 1][j].j - 1);
            }
            //Se la barca selezionata é quella da 5
            else if (shipType.equals("ship5")) {
                hasShip = true;
                map.tile[i - 1][j].hasShip = true;
                map.tile[i + 1][j].hasShip = true;
                map.tile[i - 2][j].hasShip = true;
                map.tile[i + 2][j].hasShip = true;

                //Inserimento all'interno di un array delle coordinate che occupa la barca
                map.shipFive_Tiles[0] = (map.tile[i - 2][j].i - 1) + "," + (map.tile[i - 2][j].j - 1);
                map.shipFive_Tiles[1] = (map.tile[i - 1][j].i - 1) + "," + (map.tile[i - 1][j].j - 1);
                map.shipFive_Tiles[2] = (i - 1) + "," + (j - 1);
                map.shipFive_Tiles[3] = (map.tile[i + 1][j].i - 1) + "," + (map.tile[i + 1][j].j - 1);
                map.shipFive_Tiles[4] = (map.tile[i + 2][j].i - 1) + "," + (map.tile[i + 2][j].j - 1);
            }
        } else {
            //Se la barca selezionata é quella da 2
            if (shipType.equals("ship2")) {
                hasShip = true;
                map.tile[i][j - 1].hasShip = true;

                //Inserimento all'interno di un array delle coordinate che occupa la barca
                map.shipTwo_Tiles[0] = (map.tile[i][j - 1].i - 1) + "," + (map.tile[i][j - 1].j - 1);
                map.shipTwo_Tiles[1] = (i - 1) + "," + (j - 1);

                map.shipTwo_isTilted = true;
            }
            //Se la barca selezionata é quella da 3
            else if (shipType.equals("ship3")) {
                hasShip = true;
                map.tile[i][j - 1].hasShip = true;
                map.tile[i][j + 1].hasShip = true;

                //Inserimento all'interno di un array delle coordinate che occupa la barca
                map.shipThree_Tiles[0] = (map.tile[i][j - 1].i - 1) + "," + (map.tile[i][j - 1].j - 1);
                map.shipThree_Tiles[1] = (i - 1) + "," + (j - 1);
                map.shipThree_Tiles[2] = (map.tile[i][j + 1].i - 1) + "," + (map.tile[i][j + 1].j - 1);

                map.shipThree_isTilted = true;
            }
            //Se la barca selezionata é la terza
            else if (shipType.equals("ship4")) {
                hasShip = true;
                map.tile[i][j - 1].hasShip = true;
                map.tile[i][j + 1].hasShip = true;
                map.tile[i][j - 2].hasShip = true;

                //Inserimento all'interno di un array delle coordinate che occupa la barca
                map.shipFour_Tiles[0] = (map.tile[i][j - 2].i - 1) + "," + (map.tile[i][j - 2].j - 1);
                map.shipFour_Tiles[1] = (map.tile[i][j - 1].i - 1) + "," + (map.tile[i][j - 1].j - 1);
                map.shipFour_Tiles[2] = (i - 1) + "," + (j - 1);
                map.shipFour_Tiles[3] = (map.tile[i][j + 1].i - 1) + "," + (map.tile[i][j + 1].j - 1);

                map.shipFour_isTilted = true;

            }
            //Se la barca selezionata é quella da 5
            else if (shipType.equals("ship5")) {
                hasShip = true;
                map.tile[i][j - 1].hasShip = true;
                map.tile[i][j + 1].hasShip = true;
                map.tile[i][j - 2].hasShip = true;
                map.tile[i][j + 2].hasShip = true;

                //Inserimento all'interno di un array delle coordinate che occupa la barca
                map.shipFive_Tiles[0] = (map.tile[i][j - 2].i - 1) + "," + (map.tile[i][j - 2].j - 1);
                map.shipFive_Tiles[1] = (map.tile[i][j - 1].i - 1) + "," + (map.tile[i][j - 1].j - 1);
                map.shipFive_Tiles[2] = (i - 1) + "," + (j - 1);
                map.shipFive_Tiles[3] = (map.tile[i][j + 1].i - 1) + "," + (map.tile[i][j + 1].j - 1);
                map.shipFive_Tiles[4] = (map.tile[i][j + 2].i - 1) + "," + (map.tile[i][j + 2].j - 1);

                map.shipFive_isTilted = true;
            }
        }

        map.setShipType("");
        map.shipselect.setVisible(true);
        map.tilted = false;
        map.clear.setEnabled(true);

        if (!map.shipselect.ship2.isEnabled() && !map.shipselect.ship3.isEnabled() && !map.shipselect.ship4.isEnabled() && !map.shipselect.ship5.isEnabled()) {
            map.ready.setEnabled(true);
        }
    }


    //Metodo che viene richiamato quando si clicca su una casella
    public void tileHit() {
        somethingSunk = false;

        //Se quella casella non era stata cliccata allora posso procedere
        if (!isHit) {
            isHit = true;   //Imposto la casella come colpita

            //Se é presente una barca allora devo mostrare che questa é stata colpita e informare l'avversario
            if (hasShip) {
                setIcon(shipHit);

                if (map.client == null) {
                    try {
                        map.server.bufferOut.write("goodHit");
                        map.server.bufferOut.newLine();
                        map.server.bufferOut.flush();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    try {
                        map.client.bufferOut.write("goodHit");
                        map.client.bufferOut.newLine();
                        map.client.bufferOut.flush();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

                //Controllo se é stata colpita una casella della barca da 2
                for (int a = 0; a < map.shipTwo_Tiles.length; a++) {
                    if (map.shipTwo_Tiles[a].equals((i - 1) + "," + (j - 1))) {
                        map.shipTwo_Iterator--;     //Decremento il contatore delle caselle operative

                        //Controllo se quella barca é stata affondata
                        if (map.shipTwo_Iterator == 0) {     //Se il contatore é arrivato a 0 allora la barca é stata affondata
                            map.shipTwo_Sunk = true;    //Imposto la barca come affondata
                            somethingSunk = true;

                            //Comunico al giocatore avversario che ha affondato la barca da 2
                            if (map.client == null) {
                                try {
                                    if(!map.shipTwo_isTilted){
                                        map.server.bufferOut.write("shipTwoSunk");
                                    }else{
                                        map.server.bufferOut.write("shipTwoSunkTilt");
                                    }
                                    map.server.bufferOut.newLine();
                                    map.server.bufferOut.flush();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            } else {
                                try {
                                    if(!map.shipTwo_isTilted){
                                        map.client.bufferOut.write("shipTwoSunk");
                                    }else{
                                        map.client.bufferOut.write("shipTwoSunkTilt");
                                    }
                                    map.client.bufferOut.newLine();
                                    map.client.bufferOut.flush();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }

                            //Cambio le icone nella mia mappa 1
                            for (int b = 0; b < map.shipTwo_Tiles.length; b++) {
                                String[] coordinates = map.shipTwo_Tiles[b].split(",");     //Splitto le coordinate
                                int x = Integer.parseInt(coordinates[0]) + 1;     //Converto la coordinata x in intero
                                int y = Integer.parseInt(coordinates[1]) + 1;     //Converto la coordinata y in intero

                                if(!map.shipTwo_isTilted){
                                    if (b == 0) {
                                        map.tile[x][y].setIcon(ship2_1Hit);
                                    } else if (b == 1) {
                                        map.tile[x][y].setIcon(ship2_2Hit);
                                    }
                                }
                                else{
                                    if (b == 0) {
                                        map.tile[x][y].setIcon(ship2_1HitTilted);
                                    } else if (b == 1) {
                                        map.tile[x][y].setIcon(ship2_2HitTilted);
                                    }
                                }


                                //Comunico all'avversario le coordinate delle caselle della barca da 2 affondate
                                if (map.client == null) {
                                    try {
                                        map.server.bufferOut.write(map.shipTwo_Tiles[b]);
                                        map.server.bufferOut.newLine();
                                        map.server.bufferOut.flush();
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }
                                } else {
                                    try {
                                        map.client.bufferOut.write(map.shipTwo_Tiles[b]);
                                        map.client.bufferOut.newLine();
                                        map.client.bufferOut.flush();
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            }

                            break;    //Evito ripetizioni inutili di controllo se la barca é affondata
                        }
                    }
                }
                //Controllo se é stata colpita una casella della barca da 3
                for (int a = 0; a < map.shipThree_Tiles.length; a++) {
                    if (map.shipThree_Tiles[a].equals((i - 1) + "," + (j - 1))) {
                        map.shipThree_Iterator--;     //Decremento il contatore delle caselle operative

                        //Controllo se quella barca é stata affondata
                        if (map.shipThree_Iterator == 0) {     //Se il contatore é arrivato a 0 allora la barca é stata affondata
                            map.shipThree_Sunk = true;    //Imposto la barca come affondata
                            somethingSunk = true;

                            //Comunico al giocatore avversario che ha affondato la barca da 2
                            if (map.client == null) {
                                try {
                                    if(!map.shipThree_isTilted){
                                        map.server.bufferOut.write("shipThreeSunk");
                                    }else{
                                        map.server.bufferOut.write("shipThreeSunkTilt");
                                    }
                                    map.server.bufferOut.newLine();
                                    map.server.bufferOut.flush();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            } else {
                                try {
                                    if(!map.shipThree_isTilted){
                                        map.client.bufferOut.write("shipThreeSunk");
                                    }else{
                                        map.client.bufferOut.write("shipThreeSunkTilt");
                                    }
                                    map.client.bufferOut.newLine();
                                    map.client.bufferOut.flush();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }

                            //Cambio le icone nella mia mappa 1
                            for (int b = 0; b < map.shipThree_Tiles.length; b++) {
                                String[] coordinates = map.shipThree_Tiles[b].split(",");     //Splitto le coordinate
                                int x = Integer.parseInt(coordinates[0]) + 1;     //Converto la coordinata x in intero
                                int y = Integer.parseInt(coordinates[1]) + 1;     //Converto la coordinata y in intero

                                if(!map.shipThree_isTilted){
                                    if (b == 0) {
                                        map.tile[x][y].setIcon(ship3_1Hit);
                                    } else if (b == 1) {
                                        map.tile[x][y].setIcon(ship3_2Hit);
                                    } else if (b == 2) {
                                        map.tile[x][y].setIcon(ship3_3Hit);
                                    }
                                }else{
                                    if (b == 0) {
                                        map.tile[x][y].setIcon(ship3_1HitTilted);
                                    } else if (b == 1) {
                                        map.tile[x][y].setIcon(ship3_2HitTilted);
                                    } else if (b == 2) {
                                        map.tile[x][y].setIcon(ship3_3HitTilted);
                                    }
                                }


                                //Comunico all'avversario le coordinate delle caselle della barca da 3 affondate
                                if (map.client == null) {
                                    try {
                                        map.server.bufferOut.write(map.shipThree_Tiles[b]);
                                        map.server.bufferOut.newLine();
                                        map.server.bufferOut.flush();
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }
                                } else {
                                    try {
                                        map.client.bufferOut.write(map.shipThree_Tiles[b]);
                                        map.client.bufferOut.newLine();
                                        map.client.bufferOut.flush();
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            }

                            break;    //Evito ripetizioni inutili di controllo se la barca é affondata
                        }
                    }
                }
                //Controllo se é stata colpita una casella della barca da 4
                for (int a = 0; a < map.shipFour_Tiles.length; a++) {
                    if (map.shipFour_Tiles[a].equals((i - 1) + "," + (j - 1))) {
                        map.shipFour_Iterator--;     //Decremento il contatore delle caselle operative

                        //Controllo se quella barca é stata affondata
                        if (map.shipFour_Iterator == 0) {     //Se il contatore é arrivato a 0 allora la barca é stata affondata
                            map.shipFour_Sunk = true;    //Imposto la barca come affondata
                            somethingSunk = true;

                            //Comunico al giocatore avversario che ha affondato la barca da 4
                            if (map.client == null) {
                                try {
                                    if(!map.shipFour_isTilted){
                                        map.server.bufferOut.write("shipFourSunk");
                                    }else{
                                        map.server.bufferOut.write("shipFourSunkTilt");
                                    }
                                    map.server.bufferOut.newLine();
                                    map.server.bufferOut.flush();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            } else {
                                try {
                                    if(!map.shipFour_isTilted){
                                        map.client.bufferOut.write("shipFourSunk");
                                    }else{
                                        map.client.bufferOut.write("shipFourSunkTilt");
                                    }
                                    map.client.bufferOut.newLine();
                                    map.client.bufferOut.flush();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }

                            //Cambio le icone nella mia mappa 1
                            for (int b = 0; b < map.shipFour_Tiles.length; b++) {
                                String[] coordinates = map.shipFour_Tiles[b].split(",");     //Splitto le coordinate
                                int x = Integer.parseInt(coordinates[0]) + 1;     //Converto la coordinata x in intero
                                int y = Integer.parseInt(coordinates[1]) + 1;     //Converto la coordinata y in intero

                                if(!map.shipFour_isTilted){
                                    if (b == 0) {
                                        map.tile[x][y].setIcon(ship4_1Hit);
                                    } else if (b == 1) {
                                        map.tile[x][y].setIcon(ship4_2Hit);
                                    } else if (b == 2) {
                                        map.tile[x][y].setIcon(ship4_3Hit);
                                    } else if (b == 3) {
                                        map.tile[x][y].setIcon(ship4_4Hit);
                                    }
                                }else{
                                    if (b == 0) {
                                        map.tile[x][y].setIcon(ship4_1HitTilted);
                                    } else if (b == 1) {
                                        map.tile[x][y].setIcon(ship4_2HitTilted);
                                    } else if (b == 2) {
                                        map.tile[x][y].setIcon(ship4_3HitTilted);
                                    } else if (b == 3) {
                                        map.tile[x][y].setIcon(ship4_4HitTilted);
                                    }
                                }


                                //Comunico all'avversario le coordinate delle caselle della barca da 4 affondate
                                if (map.client == null) {
                                    try {
                                        map.server.bufferOut.write(map.shipFour_Tiles[b]);
                                        map.server.bufferOut.newLine();
                                        map.server.bufferOut.flush();
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }
                                } else {
                                    try {
                                        map.client.bufferOut.write(map.shipFour_Tiles[b]);
                                        map.client.bufferOut.newLine();
                                        map.client.bufferOut.flush();
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            }

                            break;    //Evito ripetizioni inutili di controllo se la barca é affondata
                        }
                    }
                }

                //Controllo se é stata colpita una casella della barca da 5
                for (int a = 0; a < map.shipFive_Tiles.length; a++) {
                    if (map.shipFive_Tiles[a].equals((i - 1) + "," + (j - 1))) {
                        map.shipFive_Iterator--;     //Decremento il contatore delle caselle operative

                        //Controllo se quella barca é stata affondata
                        if (map.shipFive_Iterator == 0) {     //Se il contatore é arrivato a 0 allora la barca é stata affondata
                            map.shipFive_Sunk = true;    //Imposto la barca come affondata
                            somethingSunk = true;

                            //Comunico al giocatore avversario che ha affondato la barca da 5
                            if (map.client == null) {
                                try {
                                    if(!map.shipFive_isTilted){
                                        map.server.bufferOut.write("shipFiveSunk");
                                    }else{
                                        map.server.bufferOut.write("shipFiveSunkTilt");
                                    }
                                    map.server.bufferOut.newLine();
                                    map.server.bufferOut.flush();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            } else {
                                try {
                                    if(!map.shipFive_isTilted){
                                        map.client.bufferOut.write("shipFiveSunk");
                                    }else{
                                        map.client.bufferOut.write("shipFiveSunkTilt");
                                    }
                                    map.client.bufferOut.newLine();
                                    map.client.bufferOut.flush();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }

                            //Cambio le icone nella mia mappa 1
                            for (int b = 0; b < map.shipFive_Tiles.length; b++) {
                                String[] coordinates = map.shipFive_Tiles[b].split(",");     //Splitto le coordinate
                                int x = Integer.parseInt(coordinates[0]) + 1;     //Converto la coordinata x in intero
                                int y = Integer.parseInt(coordinates[1]) + 1;     //Converto la coordinata y in intero

                                if(!map.shipFive_isTilted){
                                    if (b == 0) {
                                        map.tile[x][y].setIcon(ship5_1Hit);
                                    } else if (b == 1) {
                                        map.tile[x][y].setIcon(ship5_2Hit);
                                    } else if (b == 2) {
                                        map.tile[x][y].setIcon(ship5_3Hit);
                                    } else if (b == 3) {
                                        map.tile[x][y].setIcon(ship5_4Hit);
                                    } else if (b == 4) {
                                        map.tile[x][y].setIcon(ship5_5Hit);
                                    }
                                }else{
                                    if (b == 0) {
                                        map.tile[x][y].setIcon(ship5_1HitTilted);
                                    } else if (b == 1) {
                                        map.tile[x][y].setIcon(ship5_2HitTilted);
                                    } else if (b == 2) {
                                        map.tile[x][y].setIcon(ship5_3HitTilted);
                                    } else if (b == 3) {
                                        map.tile[x][y].setIcon(ship5_4HitTilted);
                                    } else if (b == 4) {
                                        map.tile[x][y].setIcon(ship5_5HitTilted);
                                    }
                                }


                                //Comunico all'avversario le coordinate delle caselle della barca da 5 affondate
                                if (map.client == null) {
                                    try {
                                        map.server.bufferOut.write(map.shipFive_Tiles[b]);
                                        map.server.bufferOut.newLine();
                                        map.server.bufferOut.flush();
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }
                                } else {
                                    try {
                                        map.client.bufferOut.write(map.shipFive_Tiles[b]);
                                        map.client.bufferOut.newLine();
                                        map.client.bufferOut.flush();
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            }

                            break;    //Evito ripetizioni inutili di controllo se la barca é affondata
                        }
                    }
                }

                //Se non c'é stato un affondo lo comunico all'avversario
                if (!somethingSunk) {
                    if (map.client == null) {
                        try {
                            map.server.bufferOut.write("noSunk");
                            map.server.bufferOut.newLine();
                            map.server.bufferOut.flush();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        try {
                            map.client.bufferOut.write("noSunk");
                            map.client.bufferOut.newLine();
                            map.client.bufferOut.flush();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    //Effetto audio colpita (senza affondo)
                    try {
                        File file = new File("HitSoundEffect.wav"); // Inserire il percorso del file audio clic
                        Clip clip = AudioSystem.getClip();
                        clip.open(AudioSystem.getAudioInputStream(file));
                        clip.start();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    map.shipSunkCounterInt++;    //Incremento il contatore delle barche affondate
                    if (map.client == null) {
                        map.server.mapOne.shipSunkText.setText("Barche affondate: " + map.shipSunkCounterInt);
                    } else {
                        map.client.mapOne.shipSunkText.setText("Barche affondate: " + map.shipSunkCounterInt);
                    }

                    //Effetto audio affondo
                    try {
                        File file = new File("SunkSoundEffect.wav"); // Inserire il percorso del file audio clic
                        Clip clip = AudioSystem.getClip();
                        clip.open(AudioSystem.getAudioInputStream(file));
                        clip.start();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

                //Controllo se tutte le barche sono affondate e in caso lo comunico all'avversario
                if (map.shipTwo_Sunk && map.shipThree_Sunk && map.shipFour_Sunk && map.shipFive_Sunk) {
                    if (map.client == null) {
                        try {
                            map.server.bufferOut.write("win");
                            map.server.bufferOut.newLine();
                            map.server.bufferOut.flush();

                            new VictoryScreen("lost");
                            map.server.mapTwo.gameText.setText("Hai perso");

                            try {
                                Thread.sleep(4000);
                                System.exit(0);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        try {
                            map.client.bufferOut.write("win");
                            map.client.bufferOut.newLine();
                            map.client.bufferOut.flush();

                            new VictoryScreen("lost");
                            map.client.mapTwo.gameText.setText("Hai perso");

                            try {
                                Thread.sleep(4000);
                                System.exit(0);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                //Se non é presente una barca devo mostrare che quella casella é stata colpita ma a vuoto e informare l'avversario
            } else {
                //Effetto audio colpo a vuoto
                try {
                    File file = new File("WaterHit.wav"); // Inserire il percorso del file audio clic
                    Clip clip = AudioSystem.getClip();
                    clip.open(AudioSystem.getAudioInputStream(file));
                    clip.start();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                setIcon(badHit);
                if (map.client == null) {
                    try {
                        map.server.bufferOut.write("badHit");
                        map.server.bufferOut.newLine();
                        map.server.bufferOut.flush();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    try {
                        map.client.bufferOut.write("badHit");
                        map.client.bufferOut.newLine();
                        map.client.bufferOut.flush();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            //Se l'avversario ha cliccato nuovamente su una casella colpita in precedenza ripeto i cicli
        } else {
            if (map.client == null) {
                try {
                    map.server.bufferOut.write("reClick");
                    map.server.bufferOut.newLine();
                    map.server.bufferOut.flush();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                try {
                    map.client.bufferOut.write("reClick");
                    map.client.bufferOut.newLine();
                    map.client.bufferOut.flush();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
