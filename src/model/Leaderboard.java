package model;

import view.Constants;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/** The Leaderboard class determines which users will be added to the
 * leaderboard using a comparator. The class adds the user's
 * score and name are added to the appropriate files.
 */

public class Leaderboard {
    private String pathName = "";

    public Leaderboard(){
        this.setCategoryPathName(1);
    }

    /**
     * This method sets the path name to the correct text files depending on
     * which category the player selected from the Category screen.
     * @param path
     */
    public void setCategoryPathName(int path) {
        String[] questions = getFileNames();
        pathName = "src/model/leaderboard/" + questions[path];
    }

    /**
     * Getters helper method
     */
    public String[] getFileNames(){
        return new String[]{"overall.txt", "geekout.txt", "jams.txt", "foodie.txt", "ratchet.txt", "classFacts.txt", "random.txt"};
    }

    /**
     * This method takes the ID and score of the player, and places it in
     * the respective file if the score is among the top ten highest scores.
     * @param score
     */
    public void addScore(String username, Integer score){
        User addUser = new User(username, score);

        /*
         * If score in top ten, then add the score to the file.
         * Otherwise, do nothing.
         */
        if (isTopTen(score)){
            ArrayList<User> fileData = getData();

            fileData.add(addUser); // Add new high score to file

            // Sort oldData by values
            Collections.sort(fileData);

            // Drop the lowest score, the last one in the list, if the list has 10 elements
            if (fileData.size() > Constants.MAXLEAD) {
                fileData.remove(fileData.size() - 1);
            }

            writeFile(fileData, pathName);
        }

        // Update overall score too, if necessary
        writeOverall();
    }

    /**
     * addScore helper function to write to a file
     * @param fileData
     * @param path
     */
    public void writeFile(ArrayList<User> fileData,  String path){
        ArrayList<String> writingLines = new ArrayList<>();

        for (User u : fileData) {
            String line = u.getUsername() + "|" + u.getScore().toString();
            writingLines.add(line);
        }

        // Write the new sorted data to the file, overwriting the existing one
        Path file = Paths.get(path);
        try {
            Files.write(file, writingLines, Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This function takes all the high scores from different categories,
     * sorts them, and picks the top scores for display in overall.txt
     */
    public void writeOverall(){
        ArrayList<User> data = new ArrayList<>();
        String[] files = getFileNames();

        for (int i = 1; i < Constants.MAXLEAD; i++){
            ArrayList<User> temp = new ArrayList<>();
            readFile(temp, "src/model/leaderboard/" + files[i]);
            data.addAll(temp);
        }

        Collections.sort(data);

        data = new ArrayList<>(data.subList(0, Constants.MAXLEAD)); // To get only data to print on screen
        writeFile(data, "src/model/leaderboard/overall.txt");
    }

    /**
     * This method checks if a given score is among the top ten in a file
     * of users' scores. This is a helper method to the addScore function.
     * @param score
     * @return true means we need to add the user on the leaders list, and
     *          false means the user did not score enough points to make the
     *          leaders' list.
     */
    public boolean isTopTen(Integer score){
        ArrayList<User> data = getData();
        Collections.sort(data);

        return score > data.get(data.size() - 1).getScore();
    }

    /**
     * This method basically reads a file to retrieve information for
     * Leaderboard statistics. This could be used as a helper method for
     * isTopTen and other potential methods in View.java as well as other
     * classes and functions.
     * @return a map containing the user ID and his/her specific score.
     */
    public ArrayList<User> getData(){
        ArrayList<User> ret = new ArrayList<>();
        readFile(ret, pathName);
        return ret;
    }

    /**
     * getData helper function to read files
     * @param ret
     * @param path
     */
    public void readFile(ArrayList<User> ret, String path){
        String[] lineData;

        try {
            for (String line : Files.readAllLines(Paths.get(path))) { // used one file for testing purposes
                lineData = line.split("\\|");

                // Add data to the Arraylist
                User xUser = new User(lineData[0], Integer.valueOf(lineData[1])); // Order in files: name|score
                ret.add(xUser);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This class helps to bind each username to a respective userID
     * This will help in binding each user's info to his/her specific
     * score.
     */
    public class User implements Comparable {
        String username;
        Integer score;

        public User(String n, Integer s) {
            this.username = n;
            this.score = s;
        }

        // Getters
        public String getUsername() {
            return username;
        }
        public Integer getScore() { return score; }

        // Setters
        public void setUsername(String name) { username = name; }
        public void setScore(Integer s) { score = s; }

        @Override
        public int compareTo(Object compareUser) {
            int compare = ((User)compareUser).getScore();

            // Sorts by descending order of score
            return compare - this.score;

            // If ascending order
            // return this.score - compare;
        }
    }

}
