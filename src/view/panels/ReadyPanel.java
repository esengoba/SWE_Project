package view.panels;

import view.Constants;

import javax.swing.*;
import javax.swing.JTextArea;
import java.awt.*;

/** This is the Ready Panel class which extends the abstract MyPanel.
 * This screen prepares the user for the trivia game to come. Instructions
 * should be printed to the screen for the user. There are two buttons on
 * the screen which allow the user to return to the home screen or proceed
 * to play the game.
 */

public class ReadyPanel extends MyPanel {
    private JLabel readyLabel = new JLabel("Are you ready to play?");
    private JLabel categorySelectionLabel = new JLabel("You selected...");
    public JLabel categoryLabel = new JLabel("hi");
    private JTextArea instructions = new JTextArea();
    public ReadyPanel(){

        super();

        setOpaque(false);
        setBounds(0,0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        setLayout(new GridLayout(6,1));

        instructions = setJTextArea(instructions, "You will be asked 10 questions " +
                "relating to this category. Each question is worth" +
                "up to 10 points. If the timer is enabled, you will have up to 10 seconds to select " +
                "each answer." +
                "Otherwise, take your time!");

        instructions.setFont(Constants.QUESTION_FONT);
        readyLabel.setFont(Constants.TITLE_FONT);
        categorySelectionLabel.setFont(Constants.OTHER_FONT);
        categoryLabel.setFont(Constants.SCREEN_FONT);
        categoryLabel.setForeground(Color.BLUE);
        add(readyLabel, BorderLayout.CENTER);
        add(categorySelectionLabel);
        add(categoryLabel);
        add(instructions);
        add(createButton(Constants.READY));
        add(createButton(Constants.BACK));

    }


}