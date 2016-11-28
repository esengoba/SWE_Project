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

    /** This method creates a button with the specified font
     * and text. The button is also added to the array list
     * which holds all the buttons on a panel.
     * @param text
     * @return
     */
    public JButton createButton(String text){
        JButton button = new JButton(text);
        button.setFont(Constants.BUTTON_FONT);
        buttonList.add(button);
        return button;
    }

    /**This method returns all of the buttons from a panel.
     * @return an array list of buttons
     */
    public ArrayList<JButton> getButton(){
        return buttonList;
    }

    /** This method sets the characteristics for a JTextArea*/
    public JTextArea setJTextArea(JTextArea jt, String text){
        jt.setText(text);
        jt.setWrapStyleWord(true);
        jt.setLineWrap(true);
        jt.setEditable(false);
        jt.setOpaque(false);

        return jt;
    }

}
