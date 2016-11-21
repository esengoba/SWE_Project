package model;

import java.util.HashMap;

public class Leaderboard {

    /**
     * This function takes the ID and score of the player, and places it in
     * the respective file if the score is among the top ten highest scores.
     * @param userID
     * @param score
     */
    public void addScore(String userID, String score){

    }

    /**
     * This function checks if a given score is among the top ten in a file
     * of users' scores. This is a helper method to the addScore function.
     * @param score
     * @return true or false
     */
    public boolean isTopTen(String score){
        return false;
    }

    /**
     * This function basically reads a file to retrieve information for
     * Leaderboard statistics. This could be used as a helper function for
     * isTopTen and other potential functions in View.java as well as other
     * classes and functions.
     * @param category the path of the file containing the Leaderboard info
     *                 for a certain category.
     * @return a map containing the user ID and his/her specific score.
     */
    public HashMap<String, String> getData(String category){
        return new HashMap<>();
    }
}