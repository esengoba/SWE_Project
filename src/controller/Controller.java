package controller;

import view.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/** The controller class works with the QuestionPanel class and the view
 * class to update the question content for each question and resets
 * the timer.
 */
public class Controller implements ActionListener {
    View controllerView;
    public Controller(View view){
         controllerView = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        controllerView.updateQuestionContent();
    }
}

