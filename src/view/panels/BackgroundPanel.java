package view.panels;


import view.Constants;

import java.awt.*;

import static view.Constants.WINDOW_HEIGHT;
import static view.Constants.WINDOW_WIDTH;

/** This class sets the background characteristics
 * for the entire game.*/

public class BackgroundPanel extends MyPanel {
    Color background = new Color(240, 234, 214);

    public BackgroundPanel(){
        setBounds(0,0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        setOpaque(true);
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.setColor(background);
        g.fillRect(0,0, WINDOW_WIDTH, WINDOW_HEIGHT);

    }

}
