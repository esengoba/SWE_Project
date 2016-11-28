package model;
import view.View;


import java.util.*;
import java.awt.Color;
import java.awt.Graphics;


 /**The Score class determines the user's score based on whether the time
  * is enabled. This class exchanges data with the Setting class and
  * Settings panel*/

public class Score {

    public int userScore;
     View scoreView;
     public ArrayList<String> usersAnswers = new ArrayList<String>();
     public ArrayList<String> usersQuestions = new ArrayList<String>();
     public ArrayList<Integer> pointsPerQuestion = new ArrayList<Integer>();


    public Score(View view){
        userScore = 0;
        scoreView = view;
        //scoreController = new Controller(this);
    }

    /**This method resets user's score for each new round. */
    public void resetScore(){
        userScore = 0;
        usersQuestions.clear();
        usersAnswers.clear();
        pointsPerQuestion.clear();
    }

    /**This method checks to see if the timer is enabled based on the user's
     * configuration.
     */
    public boolean isTimerEnabled(){
       return( scoreView.settingsPanel.timerEnabled) ? true: false;

    }

    /**This method updates the score based on whether the timer is enabled
     * and the user got the answer correct. If the timer is enabled, then the
     * user gets 0-10 points depending on how quickly the question is answered.
     * Otherwise, each question counts for 10 points.
     */
    public void updateScore(String response){
        if (isTimerEnabled()){
            if(scoreView.questionPanel.answerMap.get(scoreView.nextQuestion).get(4).equals(response)){
                updateAnswerArray(response);
                userScore = scoreView.questionPanel.i + userScore;
                updatePointArray(scoreView.questionPanel.i);

            } else { updatePointArray(0);}

        } else {

            if(scoreView.questionPanel.answerMap.get(scoreView.nextQuestion).get(4).equals(response)){
                updateAnswerArray(response);
                userScore+= 10;
                updatePointArray(10);
            } else { updatePointArray(0);}

        }

    }
    /** This method updates an array list of the answers the user selected.*/
    public void updateAnswerArray(String ans){
        usersAnswers.add(ans);
    }

     /** This method updates an array list of the questions from that round.*/
     public void updateQuestionArray(String ans){
         usersQuestions.add(ans);
     }

     /** This method updates an array list of the questions from that round.*/
     public void updatePointArray(int ans){
         pointsPerQuestion.add(ans);
     }

 }
