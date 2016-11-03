package model;
import java.util.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/** The Question class is responsible for reading in questions and answers from
 * each respective category and saving them to a data structure. This class
 * also retrieves an individual question its answers.
 */
public class Question {

        int num = 0, categoryID, randomID;
        String pathName = "";
        String pathNameAnswers = "";

        public Question() throws IOException {
            setCategoryPathName(6); // by default
            this.getQuestion();
        }

    /** Gets the current question number during game play */
        public int getCurrentQuestionNumber() {
            if (num > 11){
                return num = 0;
            }
            return num + 1;
        }

    /**
     * Create a map that maps every question to its corresponding value
     */
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

    /**
     * Gets a map of answers. Each question's ID maps to a specific ArrayList
     * of answers.
     */
         public HashMap<String, ArrayList<String>> getAnswers() throws IOException{
             HashMap<String, ArrayList<String>> ans = new HashMap<>();
            getListOfAnswers(ans);
             return ans;
        }

    /**
     * Read the files containing the questions. Each question is mapped (as a value) to
     * a 3 digits questionID that is unique for the specific question. The ID ensures
     * questions are mapped to their corresponding answers.
     */
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

    /**
     * Read the file containing the answers
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

    /**This method randomly generates a question ID number.*/
     public int selectQuestion(){
         Random random = new Random();

         switch (categoryID){
             case 1: randomID = (int )(Math.random() * (120 - 101) + 101);
                 break; //Geek Out
             case 2: randomID = (int )(Math.random() * (220 - 201) + 201);
                 break; //Jams
             case 3: randomID = (int )(Math.random() * (320 - 301) + 301);
                 break; //Foodie
             case 4: randomID = (int )(Math.random() * (405 - 401) + 401);
                 break;//Ratchet
             case 5: randomID = (int )(Math.random() * (505 - 501) + 501);
                 break;//Class Facts
             case 6: randomID = (int )(Math.random() * (605 - 601) + 601);
                 break; //Random
             default: break;
         }
         return randomID;
     }

}