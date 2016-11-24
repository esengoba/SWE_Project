package view.panels;


import view.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**This class implements JRadioButtons to allow toggling the sound to on/off
 *
 */

public class SoundPanel extends MyPanel{
    private JRadioButton statusOn;
    private JRadioButton statusOff;
    private ButtonGroup buttonGroup;

    public SoundPanel(){
        super();

        setOpaque(false);
        setBounds(0,0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        setLayout(new GridLayout(6,1));
        JLabel settingsTitle = new JLabel("Sound", SwingConstants.CENTER);
        settingsTitle.setFont(Constants.HOME_FONT);
        add(settingsTitle);
        JLabel emptySpace = new JLabel("");
        add(emptySpace);
        statusOn = new JRadioButton("ON", true);
        statusOn.setFont(Constants.HOME_FONT);
        //  the following line can be considered if we want the on/off buttons in the centre of the page
//        statusOn.setHorizontalAlignment(AbstractButton.CENTER);
        statusOff = new JRadioButton("OFF", false);
        statusOff.setFont(Constants.HOME_FONT);
        //  the following line can be considered if we want the on/off buttons in the centre of the page
//        statusOff.setHorizontalAlignment(AbstractButton.CENTER);
        add(statusOn);
        add(statusOff);
        add(emptySpace);
        add(createButton(Constants.BACK));

        buttonGroup = new ButtonGroup();
        buttonGroup.add(statusOn);
        buttonGroup.add(statusOff);

        statusOn.addItemListener(new HandlerClass());
        statusOff.addItemListener(new HandlerClass());

    }
    private class HandlerClass implements ItemListener{

        private HandlerClass(){

        }

        public void itemStateChanged(ItemEvent event){

        }
    }
}
