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

    public String responseSelected = "";
    public Controller(View view, Score score){
        controllerView = view;
        controllerScore = score;
    }

    /*public Controller (Score score){
        controllerScore = score;
    }*/



    @Override
    public void actionPerformed(ActionEvent e) {
        responseSelected = e.getActionCommand();
        /**THE ISSUES IS HERE WHEN YOU UNCOMMENT THESE LINES**/
        controllerScore.updateAnswerArray(responseSelected);
        controllerScore.updateScore(responseSelected);
        controllerView.updateQuestionContent();

    }
}

