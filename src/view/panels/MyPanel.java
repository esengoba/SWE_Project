package view.panels;

import view.Constants;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/** This abstract class extends JPanel and allows for the creation of
 * buttons with a specified characteristics for each panel.
 * It also creates an array list of buttons for the respective panel.
 */
public abstract class MyPanel extends JPanel {
    ArrayList<JButton> buttonList = new ArrayList<JButton>();

    public JButton createButton(String text){
        JButton button = new JButton(text);
        button.setFont(Constants.BUTTON_FONT);
        buttonList.add(button);
        return button;
    }


    public ArrayList<JButton> getButton(){
        return buttonList;
    }

    public JTextArea setJTextArea(JTextArea jt, String text){
        jt.setText(text);
        jt.setWrapStyleWord(true);
        jt.setLineWrap(true);
        jt.setEditable(false);
        jt.setOpaque(false);

        return jt;
    }

}
