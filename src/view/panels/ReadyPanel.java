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
    public ReadyPanel(){

        super();

        setOpaque(false);
        setBounds(0,0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        setLayout(new GridLayout(9,1));
        JLabel readyLabel = new JLabel("Are you ready to play?");
        JTextArea instructions = new JTextArea("You will be asked 10 questions" +
                "relating to this category. You will have up to 10 seconds to select each answer.");
        instructions.setWrapStyleWord(true);
        instructions.setLineWrap(true);
        instructions.setEditable(true);
        instructions.setOpaque(false);
        instructions.setFont(Constants.QUESTION_FONT);
        readyLabel.setFont(Constants.TITLE_FONT);
        add(readyLabel, BorderLayout.CENTER);
        JLabel emptySpace = new JLabel();
        add(emptySpace);
        add(instructions);
        add(createButton(Constants.READY));
        add(createButton(Constants.BACK));

    }


}