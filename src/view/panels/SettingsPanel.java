package view.panels;

import view.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;


public class SettingsPanel extends MyPanel implements ActionListener {
    /**
     * Settings class is responsible for implementing all the settings
     * for the game which include sound on/off and
     * having the timer on/off
     */

    public JRadioButton timerStatusOn;
    public JRadioButton timerStatusOff;
    private ButtonGroup timerButtonGroup;
    private JRadioButton soundStatusOn;
    private JRadioButton soundStatusOff;
    private ButtonGroup soundButtonGroup;
    public boolean timerEnabled = true;

    public SettingsPanel() {

        //Add buttons
        super();

        setOpaque(false);
        setBounds(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        setLayout(new GridLayout(10, 1));
        JLabel settingsTitle = new JLabel("Settings", SwingConstants.CENTER);
        settingsTitle.setFont(Constants.HOME_FONT);
        add(settingsTitle);
        JLabel emptySpace = new JLabel("");
        JLabel timerLabel = new JLabel("Timer Settings");
        JLabel soundLabel = new JLabel("Sound Settings");
        timerLabel.setFont(Constants.QUESTION_FONT);
        soundLabel.setFont(Constants.QUESTION_FONT);
        timerLabel.setHorizontalAlignment(AbstractButton.CENTER);
        soundLabel.setHorizontalAlignment(AbstractButton.CENTER);


        timerStatusOn = new JRadioButton("ON", true);
        timerStatusOn.setFont(Constants.QUESTION_FONT);
        timerStatusOff = new JRadioButton("OFF", false);
        timerStatusOff.setFont(Constants.QUESTION_FONT);
        timerStatusOn.setHorizontalAlignment(AbstractButton.CENTER);
        timerStatusOff.setHorizontalAlignment(AbstractButton.CENTER);

        soundStatusOn = new JRadioButton("ON", true);
        soundStatusOn.setFont(Constants.QUESTION_FONT);
        soundStatusOff = new JRadioButton("OFF", false);
        soundStatusOff.setFont(Constants.QUESTION_FONT);
        soundStatusOn.setHorizontalAlignment(AbstractButton.CENTER);
        soundStatusOff.setHorizontalAlignment(AbstractButton.CENTER);

        add(timerLabel);
        add(timerStatusOn);
        add(timerStatusOff);
        add(soundLabel);
        add(soundStatusOn);
        add(soundStatusOff);


        timerButtonGroup = new ButtonGroup();
        soundButtonGroup = new ButtonGroup();
        timerButtonGroup.add(timerStatusOn);
        timerButtonGroup.add(timerStatusOff);
        soundButtonGroup.add(soundStatusOn);
        soundButtonGroup.add(soundStatusOff);

       timerStatusOn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Do something here...
                AbstractButton aButton = (AbstractButton) e.getSource();
                System.out.println("Selected: " + aButton.getText());
                timerEnabled = true;
            }
        });

        timerStatusOff.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timerEnabled = false;
            }
        });

        add(createButton(Constants.HOME));

    }

    public void actionPerformed(ActionEvent e) {

    }



}