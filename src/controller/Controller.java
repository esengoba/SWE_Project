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
    View controllerView;
    Score controllerScore;
    int questionCount = 1;
    public String responseSelected = "";

    public Controller(View view, Score score){
        controllerView = view;
        controllerScore = score;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (questionCount < 10) {
            responseSelected = e.getActionCommand();
            controllerScore.updateAnswerArray(responseSelected);
            controllerScore.updateScore(responseSelected);
            questionCount++;
            controllerView.updateQuestionContent();
        } else {

            questionCount = 1;
         //   controllerView.finalScorePanel.setScoreLabel(controllerScore.userScore);
            controllerView.displayScore();
        }
    }
}

