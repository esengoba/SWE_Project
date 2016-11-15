package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class SettingsPanel extends MyPanel {
    /** Settings class is responsible for implementing all the settings for the game which include sound on/off and
     * having the timer on/off
     */
    public SettingsPanel(){

        //Add buttons
        super();

        setOpaque(false);
        setBounds(0,0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        setLayout(new GridLayout(6,1));
        JLabel triviaTitle = new JLabel("Settings", SwingConstants.CENTER);
        triviaTitle.setFont(Constants.HOME_FONT);
        add(triviaTitle);
        JLabel emptySpace = new JLabel("");
        add(emptySpace);
        add(createButton(Constants.SOUND));
        add(createButton(Constants.TIMER));
        add(createButton(Constants.BACK));

    }



}
