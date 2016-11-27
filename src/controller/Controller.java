package controller;

import view.View;
import model.Score;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/** The controller class works with the QuestionPanel class and the view
 * class to update the question content for each question and resets
 * the timer.
 */
public class Controller implements ActionListener {
    public View controllerView;
    Score controllerScore;
    public int questionCount = 1;
    public String responseSelected = "";

    public Controller(View view, Score score){
        controllerView = view;
        controllerScore = score;
    }
    public void isGamePlayOver(int o){
        questionCount = 1;
        controllerView.questionPanel.timer.stop();
        //controllerScore.updateScore(responseSelected);
        System.out.println("game play done score update 2");
        controllerView.finalScorePanel.setScoreLabel(controllerScore.userScore);
        controllerView.finalScorePanel.setResultsTextArea(controllerScore.usersQuestions, controllerScore.usersAnswers,
                controllerScore.pointsPerQuestion);
        controllerView.displayScore();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        questionCount++;
        if (questionCount <= 10) {
            System.out.println(questionCount);
            responseSelected = e.getActionCommand();

            controllerScore.updateAnswerArray(responseSelected);
            controllerScore.updateScore(responseSelected);
            controllerView.updateQuestionContent();
            if (controllerScore.isTimerEnabled()){
            controllerView.questionPanel.resetTimer();}
        } else {
            responseSelected = e.getActionCommand();
            controllerScore.updateScore(responseSelected);
            isGamePlayOver(questionCount);
        }
    }
}

