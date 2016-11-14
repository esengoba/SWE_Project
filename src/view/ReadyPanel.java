package view;

import javax.swing.*;
import java.awt.*;

/** This is the Ready Panel class which extends the abstract MyPanel.
 * This screen prepares the user for the trivia game to come. Instructions
 * should be printed to the screen for the user. There are two buttons on
 * the screen which allow the user to return to the home screen or proceed
 * to play the game.
 */

public class ReadyPanel extends MyPanel {
    public ReadyPanel(){

        super();

        setOpaque(false);
        setBounds(0,0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        setLayout(new GridLayout(7,1));
        JLabel readyLabel = new JLabel("Are you ready to play Name that Thing?!");
        readyLabel.setFont(Constants.BUTTON_FONT);
        add(readyLabel, BorderLayout.CENTER);
        add(createButton(Constants.READY));
        add(createButton(Constants.BACK));

    }


}