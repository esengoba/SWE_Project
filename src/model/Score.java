package model;
import model.Model;
import controller.Controller;
import view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;


 /**The Score class determines the user's score based on whether the time
  * is enabled. This class exchanges data with the Setting class/ Settings panel*/
public class Score {
    public int userScore;
     View scoreView;
     ArrayList<String> usersAnswers = new ArrayList<String>();

    public Score(View view){
        userScore = 0;
        scoreView = view;
        //scoreController = new Controller(this);
    }

    /**The user's score is reset for each new round*/
    public void resetScore(){
        userScore = 0;
    }

    /**This function checks to see if the timer is enabled based on the user's
     * configuration.
     */
    public boolean isTimerEnabled(){
       return false;
    }

    /**This function updates the score based on whether the timer is enabled
     * and the user got the answer correct. If the timer is enabled, then the
     * user gets 0-10 points depending on how quickly the question is answered.
     * Otherwise, each question counts for 10 points.
     */
    public void updateScore(String response){
        if (!isTimerEnabled()){
               if(scoreView.questionPanel.answerMap.get(scoreView.nextQuestion).get(4).equals(response)){
                    //update the score
                    userScore+= 10;
                }

        }
        System.out.println("User score is now " + userScore);

    }
    /** This method updates an array list of the answers the user selected*/
    public void updateAnswerArray(String ans){
        usersAnswers.add(ans);
    }

}
