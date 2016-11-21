package view;
import model.Model;
import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;


import model.Question;
import model.Score;
import view.panels.*;

import static view.Constants.WINDOW_HEIGHT;
import static view.Constants.WINDOW_WIDTH;

/**The View class creates a single frame where different panels (or
 * interfaces) are interchanged. There are five key panels which
 * extend from an abstract class: background panel, category panel,
 * the home panel,ready panel, and questions panel.
 */
public class View extends JFrame implements ActionListener {
    private Model model;

    private HomePanel homePanel = new HomePanel();
    private BackgroundPanel backgroundPanel = new BackgroundPanel();
    private CategoryPanel categoryPanel = new CategoryPanel();
    private ReadyPanel readyPanel = new ReadyPanel();
    public Question question;
    public FinalScorePanel finalScorePanel = new FinalScorePanel();
    public QuestionPanel questionPanel;
    private SettingsPanel settingsPanel = new SettingsPanel();
    public LeaderboardPanel leaderboardPanel = new LeaderboardPanel();
    JLayeredPane layered = new JLayeredPane();
    public Controller controller;

    public Score score;
    int quesID;
    public String nextQuestion;
    public ArrayList<String> randomQuestions = new ArrayList<>();


    public View() {
        try {
            question = new Question();
        } catch (IOException ex) {}
        //controller = new Controller(this);
        score = new Score(this);
        controller = new Controller(this, score);
        questionPanel = new QuestionPanel(controller);

        model = new Model();
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Name that Thing! -- Home");
        layered.add(homePanel);
        layered.add(backgroundPanel);
        setLayout(new BorderLayout());
        add(layered, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        addActionListener(homePanel.getButton());
        addActionListener(categoryPanel.getButton());
        addActionListener(readyPanel.getButton());
        addActionListener(finalScorePanel.getButton());
        addActionListener(settingsPanel.getButton());
        addActionListener(leaderboardPanel.getButton());

    }
    /* Add this as ActionListener for all buttons */
    public void addActionListener(ArrayList<JButton> buttons){

        for (JButton button: buttons){
            button.addActionListener(this);

        }
    }
    /**This function updates the question content for the question
     * interface. It uses the Question class' methods to get a single
     * question, and the respective answers and displays on the screen.
     */
    public void updateQuestionContent(){
        //get question
        quesID = question.getCurrentQuestionNumber();
        questionPanel.progbar.setValue(quesID);
        questionPanel.questionNumLabel.setText(Integer.toString(quesID));

        //update the question label on the screen
        try {
            questionPanel.questionMap = question.getQuestion();
            questionPanel.answerMap = question.getAnswers();

        } catch (IOException ex) {}

        nextQuestion = randomQuestions.get(0);
        randomQuestions.remove(0);

        questionPanel.questionTextArea.setText(questionPanel.questionMap.get(nextQuestion));

        questionPanel.ansButton1.setText(questionPanel.answerMap.get(nextQuestion).get(0));
        questionPanel.ansButton2.setText(questionPanel.answerMap.get(nextQuestion).get(1));
        questionPanel.ansButton3.setText(questionPanel.answerMap.get(nextQuestion).get(2));
        questionPanel.ansButton4.setText(questionPanel.answerMap.get(nextQuestion).get(3));

        //System.out.println(questionPanel.answerMap);
    }

    public void displayScore(){
        setTitle("Name that Thing! -- Final Score");
        layered.removeAll();
        layered.add(finalScorePanel);
        layered.add(backgroundPanel);
        layered.repaint();
    }

    /** This implements the action listener for the View class allowing for each
     * of the button to carry out a set of functions when pressed. The switch
     * statements allow for the illusion of moving from screen to screen( relayering
     * of individual panels).
     * @param e : result of the button pressed on the screen
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case Constants.START: // do nothing
                setTitle("Name that Thing! -- Category Selection");
                layered.removeAll();
                layered.add(categoryPanel);
                layered.add(backgroundPanel);
                break;
            case Constants.SETTINGS:
                setTitle("Name that Thing! -- Settings Selection");
                layered.removeAll();
                layered.add(settingsPanel);
                layered.add(backgroundPanel);
                break;
            case Constants.LEADERBOARD:
                layered.removeAll();
                layered.add(leaderboardPanel);
                layered.add(backgroundPanel);
                break;
            case Constants.GEEKOUT:
                layered.removeAll();
                layered.add(readyPanel);
                layered.add(backgroundPanel);
                question.setCategoryPathName(1);
                break;
            case Constants.EARLY2000JAMS:
                layered.removeAll();
                layered.add(readyPanel);
                layered.add(backgroundPanel);
                question.setCategoryPathName(2);
                break;
            case Constants.FOODIE:
                layered.removeAll();
                layered.add(readyPanel);
                layered.add(backgroundPanel);
                question.setCategoryPathName(3);
                break;
            case Constants.RATCHETFACTS:
                layered.removeAll();
                layered.add(readyPanel);
                layered.add(backgroundPanel);
                question.setCategoryPathName(4);
                break;
            case Constants.SWECLASSFUNNYFACTS:
                layered.removeAll();
                layered.add(readyPanel);
                layered.add(backgroundPanel);
                question.setCategoryPathName(5);
                break;
            case Constants.RANDOM:
                layered.removeAll();
                layered.add(readyPanel);
                layered.add(backgroundPanel);
                question.setCategoryPathName(6);
                break;
            case Constants.READY:
                setTitle("Name that Thing! -- Question");
                layered.removeAll();
                layered.add(questionPanel);
                layered.add(backgroundPanel);
                questionPanel.countdown();
                question.reset();
                score.resetScore();
                randomQuestions.addAll(question.setGameQuestions());
                updateQuestionContent();
                break;
            case Constants.BACK:                       /*Not fully functional*/
                setTitle("Name that Thing! -- Home");  /*Implement to return to the last visited panel*/
                layered.removeAll();
                layered.add(homePanel);
                layered.add(backgroundPanel);
                // Need to update the reinitialization of random questions after the first game
                randomQuestions = new ArrayList<>();
            default:
                break;
        }
        layered.repaint();
    }

    public static void main(String[] args) {
        View v = new View();
    }
}
