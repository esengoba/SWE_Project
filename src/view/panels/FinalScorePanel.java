package view.panels;

import view.Constants;

import javax.swing.*;
import java.awt.*;

/**This class supports the interface for showing the final score
 * to the user. This class also extends MyPanel.
 */
public class FinalScorePanel extends MyPanel {
    JLabel scoreTitle = new JLabel("Results");
    public JLabel scoreLabel = new JLabel("Your Score");
    public String userName;
    public JTextField nameField;
    public JTextArea questionResult = new JTextArea(1, 10);
    JScrollPane jScrollPane1 = new JScrollPane(questionResult);

    public FinalScorePanel(){
        super();
        setOpaque(false);
        setBounds(0,0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        setLayout(new GridLayout(9,1));
        scoreTitle.setFont(Constants.TITLE_FONT);

        scoreLabel.setFont(Constants.SCREEN_FONT);
        add(scoreTitle, BorderLayout.EAST);
        add(scoreLabel, BorderLayout.EAST);

        //if (score counts and makes leaderboard){ *** need to add these conditions
        questionResult.setWrapStyleWord(true);
        questionResult = setJTextArea(questionResult, "");
        questionResult.setAutoscrolls(true);
        questionResult.append("1" + Constants.NEWLINE);
        questionResult.append("2" + Constants.NEWLINE);
        questionResult.append("3" + Constants.NEWLINE);
        questionResult.append("4" + Constants.NEWLINE);
        questionResult.append("5" + Constants.NEWLINE);
        questionResult.append("6" + Constants.NEWLINE);
        questionResult.append("7" + Constants.NEWLINE);
        questionResult.append("8" + Constants.NEWLINE);
        questionResult.append("9" + Constants.NEWLINE);
        questionResult.append("10" + Constants.NEWLINE);
        add(jScrollPane1);

        JLabel nameLabel = new JLabel("Please enter your name:");
        nameField = new JTextField(20);
        nameLabel.setFont(Constants.QUESTION_FONT);
        add(nameLabel, BorderLayout.CENTER);
        add(nameField);
        add(createButton(Constants.ENTER));

        //}

        //else{
        // use a different layout to display the result of the questions
        add (createButton(Constants.LEADERBOARD));
        //}

        add(createButton(Constants.HOME)); /*Returns to the home button*/

    }


    /**This method sets the final score for the panel.*/
    public void setScoreLabel(int s){
       scoreLabel.setText("Your score: " + Integer.toString(s));
    }

    /**This method grabs the user name.*/
    public boolean setUserName(){

        String str = nameField.getText().trim();

        if (str == null || !str.matches("[A-Za-z0-9_]+" )|| str.length()>20 || str.length()< 5) {
            JOptionPane.showMessageDialog(null, "Please enter a valid name. Must be 5-20 characters. " +
                    "Alphanumeric.");
            nameField.requestFocusInWindow();
            nameField.setText("");

            return false;
        }

        else

            userName = str;
        nameField.setText("");
        return true;
    }

}
