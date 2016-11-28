package view.panels;

import view.Constants;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * This class supports the interface for showing the final score
 * to the user. This class also extends MyPanel.
 */
public class FinalScorePanel extends MyPanel {
    JLabel scoreTitle = new JLabel("Results");
    public JLabel scoreLabel = new JLabel("Your Score");
    public String username;
    public Integer score;
    public JTextField nameField;
    public JLabel nameLabel;

    public JTextArea gamePlayResult = new JTextArea(1, 10);
    JScrollPane jScrollPane1 = new JScrollPane(gamePlayResult);


    public FinalScorePanel(){
        super();
        setOpaque(false);
        setBounds(0,0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        setLayout(new GridLayout(9,1));
        scoreTitle.setFont(Constants.TITLE_FONT);

        scoreLabel.setFont(Constants.SCREEN_FONT);
        add(scoreTitle, BorderLayout.EAST);
        add(scoreLabel, BorderLayout.EAST);
        gamePlayResult.setWrapStyleWord(true);
        gamePlayResult = setJTextArea(gamePlayResult, "");
        gamePlayResult.setAutoscrolls(true);
        gamePlayResult.append("1" + Constants.NEWLINE);
        add(jScrollPane1);

        nameLabel= new JLabel();
        nameLabel.setText("You made the Leaderboard! Please enter your name:");
        nameField = new JTextField(20);
        nameLabel.setFont(Constants.QUESTION_FONT);
        add(nameLabel, BorderLayout.CENTER);
        add(nameField);

    }

    /**
     * This method sets the final score for the panel.
     */
    public void setScoreLabel(int s, boolean a){ //set display

       scoreLabel.setText("Your score: " + Integer.toString(s));

        if (!a){
            nameLabel.setVisible(false);
            nameField.setVisible(false);
            add(createButton(Constants.LEADERBOARD));
            add(createButton(Constants.HOME));

        } else{
            System.out.println("ENTERBUTT");
            add(createButton(Constants.ENTER));
            // Add score
            score = s;
        }

        FinalScorePanel.this.revalidate();
        FinalScorePanel.this.repaint();

    }

    /**
     * This method grabs the username.
     */
    public boolean setUserName(){
        String str = nameField.getText().trim();

        if (str == null || !str.matches("[A-Za-z0-9_]+" )|| str.length()>20 || str.length()< 5) {
            JOptionPane.showMessageDialog(null, "Please enter a valid name. Must be 5-20 characters. " +
                    "Alphanumeric.");
            nameField.requestFocusInWindow();
            nameField.setText("");

            return false;
        } else {
            username = str;
        }
        nameField.setText("");

        return true;
    }

    public void clearTextArea(){
        gamePlayResult.setText("Your results" + Constants.NEWLINE);
    }

    public void setResultsTextArea(ArrayList<String> questions, ArrayList<String> answers, ArrayList<Integer> points){
        clearTextArea();
        System.out.println("txt: " + points);
        for(int i = 0; i < points.size(); i++){
            gamePlayResult.append(i+1 +"." + questions.get(i) + Constants.NEWLINE);
            gamePlayResult.append(" You got..." + points.get(i) + " point(s)" + Constants.NEWLINE);
            System.out.println("txt: " + points.get(i));
        }
        System.out.println("txt: " + gamePlayResult.getText());
    }

    /**
     * Getters to add username and score into the database
     * @return
     */
    public String getUsername(){ return username; }
    public Integer getScore() { return score; }

}
