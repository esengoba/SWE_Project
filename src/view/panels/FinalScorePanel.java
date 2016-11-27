package view.panels;

import view.Constants;

import javax.swing.*;
import java.awt.*;
import java.io.IOError;

/**This class supports the interface for showing the final score
 * to the user. This class also extends MyPanel.
 */
public class FinalScorePanel extends MyPanel {
    JLabel scoreTitle = new JLabel("Results");
    public JLabel scoreLabel = new JLabel("Your Score");
    //****
    public String userName;
    public JTextField nameField ;
    //*****

    public FinalScorePanel(){
        super();
        setOpaque(false);
        setBounds(0,0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        setLayout(new GridLayout(8,1));
        scoreTitle.setFont(Constants.TITLE_FONT);

        scoreLabel.setFont(Constants.SCREEN_FONT);
        add(scoreTitle, BorderLayout.EAST);

        add(scoreLabel, BorderLayout.EAST);


        //****** if (score counts and makes leaderboard){

        JLabel nameLabel = new JLabel("Please enter your name:");
        nameField = new JTextField(20);
        nameLabel.setFont(Constants.QUESTION_FONT);
        add(nameLabel, BorderLayout.CENTER);
        JLabel emptySpace = new JLabel();

        add(nameField);
        add(createButton(Constants.ENTER));

        //}

        //else{**********
        add (createButton(Constants.LEADERBOARD));
        //}

        add(createButton("BACK")); /*Returns to the home button*/


    }

    /**This function sets the final score for the panel.
     */
   public void setScoreLabel(int s){
       scoreLabel.setText("Your score: " + Integer.toString(s));
    }

    /**This function grabs the user name.*/
    public boolean setUserName(){

        String str = nameField.getText().trim();

            if (str == null || !str.matches("[A-Za-z0-9_]+" )|| str.length()>20 || str.length()< 5) {
                JOptionPane.showMessageDialog(null, "Please enter a valid name. Must be 5-20 characters. Alphanumeric.");
                nameField.requestFocusInWindow();
                nameField.setText("");

                return false;
            }

            else

        userName = str;
        nameField.setText("");
        //System.out.println(userName);
        return true;
    }
}
