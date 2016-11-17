package view.panels;


import view.Constants;

import java.awt.*;

import static view.Constants.WINDOW_HEIGHT;
import static view.Constants.WINDOW_WIDTH;

public class BackgroundPanel extends MyPanel {

    public BackgroundPanel(){
        setBounds(0,0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        setOpaque(true);
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0,0, WINDOW_WIDTH, WINDOW_HEIGHT);

    }

}
