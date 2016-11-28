package view.panels;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import view.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import sun.*;
import java.io.*;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioInputStream;

/**
 * The Settings class is responsible for implementing all the settings
 * for the game which includes sound on/off and
 * having the timer on/off using JRadio buttons.
 */

public class SettingsPanel extends MyPanel implements ActionListener {

    private JRadioButton timerStatusOn;
    private JRadioButton timerStatusOff;
    private ButtonGroup timerButtonGroup;
    private JRadioButton soundStatusOn;
    private JRadioButton soundStatusOff;
    private ButtonGroup soundButtonGroup;
    public boolean timerEnabled = true;
    private AudioInputStream audioFile;
    private String audioFilePath = "src/view/ElectronicPop.wav";
    private InputStream soundInput;
    private Clip clip;

    public SettingsPanel() {

        //Add buttons
        super();

        setOpaque(false);
        setBounds(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        setLayout(new GridLayout(10, 1));
        JLabel settingsTitle = new JLabel("Settings", SwingConstants.CENTER);
        settingsTitle.setFont(Constants.TITLE_FONT);
        add(settingsTitle);
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

        soundStatusOn = new JRadioButton("ON", false);
        soundStatusOn.setFont(Constants.QUESTION_FONT);
        soundStatusOff = new JRadioButton("OFF", true);
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

        soundStatusOn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               try{
                   soundInput = new FileInputStream(new File(audioFilePath));
                   audioFile = AudioSystem.getAudioInputStream(new File(audioFilePath));
                   clip = AudioSystem.getClip();
                   clip.open(audioFile);
                   clip.loop(Clip.LOOP_CONTINUOUSLY);

               }catch(Exception ae){ae.printStackTrace();}

            }
        });

        soundStatusOff.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clip.stop();
            }
        });


    }


    public void actionPerformed(ActionEvent e) {

    }



}