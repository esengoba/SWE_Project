package view;
import model.Leaderboard;
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

    private HomePanel homePanel = new HomePanel();
    private BackgroundPanel backgroundPanel = new BackgroundPanel();
    private CategoryPanel categoryPanel = new CategoryPanel();
    private ReadyPanel readyPanel = new ReadyPanel();
    public Question question;
    public FinalScorePanel finalScorePanel = new FinalScorePanel();
    public QuestionPanel questionPanel;
    public SettingsPanel settingsPanel = new SettingsPanel();
    public LeaderboardPanel leaderboardPanel = new LeaderboardPanel();
    JLayeredPane layered = new JLayeredPane();
    public Controller controller;
    public Leaderboard leaderboard;

    public Score score;
    int quesID;
    public String nextQuestion;
    public ArrayList<String> randomQuestions = new ArrayList<>();


    public View() {
        leaderboard = new Leaderboard();

        try {
            question = new Question();
        } catch (IOException ex) {}
        //controller = new Controller(this);
        score = new Score(this);
        controller = new Controller(this, score);
        questionPanel = new QuestionPanel(controller);

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
    /* This method adds the View's ActionListener for all buttons in
     * the array list. */
    public void addActionListener(ArrayList<JButton> buttons){

        for (JButton button: buttons){
            button.addActionListener(this);

        }
    }
    /**This method updates the question content for the question
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
        score.updateQuestionArray(questionPanel.questionMap.get(nextQuestion));

        questionPanel.ansButton1.setText(questionPanel.answerMap.get(nextQuestion).get(0));
        questionPanel.ansButton2.setText(questionPanel.answerMap.get(nextQuestion).get(1));
        questionPanel.ansButton3.setText(questionPanel.answerMap.get(nextQuestion).get(2));
        questionPanel.ansButton4.setText(questionPanel.answerMap.get(nextQuestion).get(3));
    }

    /** This method displays the final results of the game by
     * displaying the final score panel.
     */
    public void displayScore(){
        setTitle("Name that Thing! -- Final Score");
        layered.removeAll();
        layered.add(finalScorePanel);
        addActionListener(finalScorePanel.getButton());
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
                setTitle("Name that Thing! -- Leaderboard Standings");
                layered.removeAll();
                finalScorePanel = new FinalScorePanel(); //***********************temp
                layered.add(leaderboardPanel);
                layered.add(backgroundPanel);
                break;
            case Constants.GEEKOUT:
                layered.removeAll();
                layered.add(readyPanel);
                layered.add(backgroundPanel);
                question.setCategoryPathName(1);
                leaderboard.setCategoryPathName(1);
                readyPanel.categoryLabel.setText("GEEK OUT");
                break;
            case Constants.EARLY2000JAMS:
                layered.removeAll();
                layered.add(readyPanel);
                layered.add(backgroundPanel);
                question.setCategoryPathName(2);
                leaderboard.setCategoryPathName(2);
                readyPanel.categoryLabel.setText("EARLY 2000s JAMS");
                break;
            case Constants.FOODIE:
                layered.removeAll();
                layered.add(readyPanel);
                layered.add(backgroundPanel);
                question.setCategoryPathName(3);
                leaderboard.setCategoryPathName(3);
                readyPanel.categoryLabel.setText("FOODIE");
                break;
            case Constants.POPCULTURE:
                layered.removeAll();
                layered.add(readyPanel);
                layered.add(backgroundPanel);
                question.setCategoryPathName(4);
                leaderboard.setCategoryPathName(4);
                readyPanel.categoryLabel.setText("POP CULTURE");
                break;
            case Constants.SWECLASSFUNNYFACTS:
                layered.removeAll();
                layered.add(readyPanel);
                layered.add(backgroundPanel);
                question.setCategoryPathName(5);
                leaderboard.setCategoryPathName(5);
                readyPanel.categoryLabel.setText("CLASS FACTS");
                break;
            case Constants.RANDOM:
                layered.removeAll();
                layered.add(readyPanel);
                layered.add(backgroundPanel);
                question.setCategoryPathName(6);
                leaderboard.setCategoryPathName(6);
                readyPanel.categoryLabel.setText("RANDOM: A BIT OF EVERYTHING");
                break;
            case Constants.ENTER:
                if (finalScorePanel.setUserName()){
                    setTitle("Name that Thing! -- Leaderboard Standings");
                    leaderboard.addScore(finalScorePanel.getUsername(), finalScorePanel.getScore());
                    //remove button
                    finalScorePanel = new FinalScorePanel();
                    layered.removeAll();
                    layered.add(leaderboardPanel);
                    layered.add(backgroundPanel);
                    break;}
                else {break;}
            case Constants.READY:
                setTitle("Name that Thing! -- Question");
                layered.removeAll();
                layered.add(questionPanel);
                layered.add(backgroundPanel);
                if (score.isTimerEnabled()){
                    questionPanel.counter.setVisible(true);
                    questionPanel.countdown();
                    question.reset();
                    score.resetScore();
                    randomQuestions.addAll(question.setGameQuestions());
                    updateQuestionContent();
                    questionPanel.resetTimer();
                } else{
                    questionPanel.counter.setVisible(false);
                    question.reset();
                    score.resetScore();
                    randomQuestions.addAll(question.setGameQuestions());
                    updateQuestionContent();
                }
                break;
            case Constants.BACK:
                setTitle("Name that Thing! -- Category Selection");
                layered.removeAll();
                layered.add(categoryPanel);
                layered.add(backgroundPanel);
                break;
            case Constants.HOME:
                setTitle("Name that Thing! -- Home");
                layered.removeAll();
                finalScorePanel = new FinalScorePanel(); //***********************temp
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
