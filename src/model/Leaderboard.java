package model;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Leaderboard {
    private String pathName = "";

    /**
     * Constructor
     */
    public Leaderboard(){
        this.setCategoryPathName(0);
    }

    /**This method sets the path name to the correct text files depending on
     * which category the player selected from the Category screen.
     * @param path
     */
    public void setCategoryPathName(int path) { // not used, YET
        String[] questions = {"overall.txt", "geekout.txt", "jams.txt", "foodie.txt", "ratchet.txt", "classFacts.txt", "random.txt"};

        pathName = "src/model/leaderboard/" + questions[path];
    }


    /**
     * This function takes the ID and score of the player, and places it in
     * the respective file if the score is among the top ten highest scores.
     * @param score
     */
    public void addScore(String username, Integer score){
        User addUser = new User(username, score);

        /*
         * If score in top ten, then add the score to the file.
         * Otherwise, do nothing
         */
        if (isTopTen(score)){
            ArrayList<User> fileData = getData();
            ArrayList<String> writingLines = new ArrayList<>();

            fileData.add(addUser); // Add new high score to file

            // Sort oldData by values
            Collections.sort(fileData);

            // Drop the lowest score, the last one in the list, if the list has 10 elements
            if (fileData.size() > 10) {
                fileData.remove(fileData.size() - 1);
            }

            for (User u : fileData) {
                String line = u.getUsername() + "|" + u.getScore().toString();
                writingLines.add(line);
            }

            // Write the new sorted data to the file, overwriting the existing one
            Path file = Paths.get(pathName);
            try {
                Files.write(file, writingLines, Charset.forName("UTF-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * This function checks if a given score is among the top ten in a file
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
     * This function basically reads a file to retrieve information for
     * Leaderboard statistics. This could be used as a helper function for
     * isTopTen and other potential functions in View.java as well as other
     * classes and functions.
     * @return a map containing the user ID and his/her specific score.
     */
    // The argument is useless SO FAR, but could be an alternative to pathName
    public ArrayList<User> getData(){
        // map to be returned
        ArrayList<User> ret = new ArrayList<>();
        String[] lineData;

        try {
            for (String line : Files.readAllLines(Paths.get(pathName))) { // used one file for testing purposes
                lineData = line.split("\\|");

                // Add data to the Arraylist
                User xUser = new User(lineData[0], Integer.valueOf(lineData[1])); // Order in files: name|score
                ret.add(xUser);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ret;
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
