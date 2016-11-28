package model;


import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


/** The Question class is responsible for reading in questions and answers from
 * each respective category and saving them to a data structure. This class
 * also retrieves an individual question its answers. Essentially, this file
 * is considered with the back end question information/storage.
 */

public class Question {

     int num = 1, categoryID;
     String pathName = "";
     String pathNameAnswers = "";
     HashSet<String> distinctQuestions = new HashSet<String>();

        public Question() throws IOException {
            setCategoryPathName(6); // Default to the random - USELESS
            this.getQuestion();
        }

    /** This method gets the current question number during game play. */
        public int getCurrentQuestionNumber() {
            return num++;
        }

    /** This method resets the question number for each round.*/
        public void reset(){
            num = 1;
         }

    /** This method creates a map that maps every question to its
     * corresponding value.*/
        public HashMap<String, String> getQuestion() throws IOException {
            ArrayList<String> questions = new ArrayList<>();
            ArrayList<String> questionID = new ArrayList<>();
            HashMap<String, String> retMap = new HashMap<>();
            HashMap<String, ArrayList<String>> answers = new HashMap<>();

            getListOfQuestions(questions, questionID, answers);
            getListOfAnswers(answers);

            for (int i = 0; i < questions.size(); i++){
                retMap.put(questionID.get(i), questions.get(i));
            }

            return retMap;
         }

    /** This method gets a map of answers. Each question's ID maps
     * to a specific ArrayList of answers.*/
         public HashMap<String, ArrayList<String>> getAnswers() throws IOException{
             HashMap<String, ArrayList<String>> ans = new HashMap<>();
            getListOfAnswers(ans);
             return ans;
        }

    /** This method reads the files containing the questions. Each question is
     * mapped (as a value) to a 3 digits questionID that is unique for the
     * specific question. The ID ensures questions are mapped to their
     * corresponding answers.*/
         public void getListOfQuestions(ArrayList<String> questions, ArrayList<String> questionID, HashMap<String, ArrayList<String>> answers) throws IOException {
             ArrayList<String> temp = new ArrayList<>();

             boolean isOdd = true;

             for (String line : Files.readAllLines(Paths.get(pathName))) {

                if (isOdd){
                    answers.put(line, temp);
                    questionID.add(line);
                    isOdd = false;
                } else{
                    questions.add(line);
                    isOdd = true;
                }
             }
         }

    /** This method reads the file containing the answers
     * These answers, stored in an ArrayList, are mapped to a questionID key to ensure
     * the exact matching between questions and answers.
     */

         public void getListOfAnswers(HashMap<String, ArrayList<String>> ans) throws IOException {

             int num = 1;
             String key = "";
             ArrayList<String> temp = new ArrayList<>();

          for (String line : Files.readAllLines(Paths.get(pathNameAnswers))) {
             if ((num % 6) == 1){
                 temp = new ArrayList<>();
                 key = line;
              }else{
                 temp.add(line);
                 ans.put(key, temp);
              }
              num++;
         }
     }

    /**This method sets the path name to the correct text files depending on
     * which category the player selected from the Category screen.
     * @param path
     */
     public void setCategoryPathName(int path) {
         String[] questions = {"geekout.txt", "jams.txt", "foodie.txt", "ratchet.txt", "classFacts.txt", "random.txt"};
         String[] answers = {"geekoutAnswers.txt", "jamsAnswers.txt","foodieAnswers.txt","ratchetAnswers.txt","classFactsAnswers.txt", "randomAnswers.txt"};

         pathName = "src/model/questions/" + questions[path-1];
         pathNameAnswers = "src/model/answers/" + answers[path-1];
         categoryID = path;
     }

    /** This method randomly generates a question ID number.
     * */
     public String selectRandomQuestion(ArrayList<String> a){
         Random r = new Random();
         return a.get(r.nextInt(a.size()));
     }
    /** This method sets all of the questions for each round.
     * */
     public HashSet<String> setGameQuestions(){
         HashMap<String, String> questionMap = new HashMap<>();
         HashMap<String, ArrayList<String>> answerMap = new HashMap<>();
         HashSet<String> tenQuestionsID = new HashSet<>();

         try {
             questionMap = getQuestion();
             answerMap = getAnswers();
         } catch (IOException e) {
             e.printStackTrace();
         }

         ArrayList<String> IDs = new ArrayList<>(questionMap.keySet());

         while (tenQuestionsID.size() <= 10){
             String randID = selectRandomQuestion(IDs);
             tenQuestionsID.add(randID);
         }

         return tenQuestionsID;
     }

}