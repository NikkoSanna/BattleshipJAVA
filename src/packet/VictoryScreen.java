package packet;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class VictoryScreen extends JFrame {
    //Oggetti legati all'interfaccia
    Container c = this.getContentPane();

    JLabel text = new JLabel();

    Font font;
    public VictoryScreen(String str){
        //Font da file esterno
        try{
            font  = Font.createFont(Font.TRUETYPE_FONT, new File("Font.ttf")).deriveFont(Font.PLAIN, 18);
        } catch (Exception ignored){}

        //Aggiunta del font
        text.setFont(font);

        //Selezione scritta in base alla vittoria/sconfitta
        if(str.equals("lost")){
            text.setText("Mi spiace ma hai perso");
        }else{
            text.setText("Congratulazioni per aver vinto");
        }

        c.add(text);

        //Impostazioni di visualizzazione
        this.pack();
        this.setLocation(ScreenSize.getWidth() / 3, ScreenSize.getHeight() / 2);
        this.setResizable(false);
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        this.setVisible(true);
    }
}
