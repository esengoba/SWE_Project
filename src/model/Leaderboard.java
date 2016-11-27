package model;

import javax.jws.soap.SOAPBinding;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Leaderboard {

    int num = 1, categoryID;
    String pathName = "";
    String pathNameAnswers = "";

    HashMap<User, Integer> test1 = new HashMap<>();
    Map<User, Integer> test2 = new HashMap<>();

    /**
     * Constructor
     */
    public Leaderboard(){
        this.getData("");
    }

    /**This method sets the path name to the correct text files depending on
     * which category the player selected from the Category screen.
     * @param path
     */
    public void setCategoryPathName(int path) {
        String[] questions = {"geekout.txt", "jams.txt", "foodie.txt", "ratchet.txt", "classFacts.txt", "random.txt"};
        String[] answers = {"geekoutAnswers.txt", "jamsAnswers.txt","foodieAnswers.txt","ratchetAnswers.txt","classFactsAnswers.txt", "randomAnswers.txt"};

        pathName = "src/model/leaderboard/" + questions[path-1];
        // pathName = "src/model/leaderboard/overall.txt"; // TEMPORARY
        categoryID = path;
    }


    /**
     * This function takes the ID and score of the player, and places it in
     * the respective file if the score is among the top ten highest scores.
     * @param userID
     * @param score
     */
    public void addScore(String username, String userID, Integer score){
        User addUser = new User(username, userID);

        /*
         * If score in top ten, then add the score to the file.
         * Otherwise, do nothing
         */
        if (isTopTen(score)){
            HashMap<User, Integer> oldData = getData("");
            oldData.put(addUser, score); // Add new high score to file

            // Sort oldData by values
            Map<User, Integer> newData = sortByValues(oldData);

            test1 = oldData;
            test2 = newData;
            // Write the new sorted data to the file, overwriting the existing one

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
        ArrayList<Integer> topTenScores = new ArrayList<>();
        topTenScores.addAll(getData(" ").values());
        Collections.sort(topTenScores);
        System.out.println(test1);
        System.out.println(test2);

        return (topTenScores.get(0) < score);
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
    // The argument is useless so far since pathName is doing the job
    public HashMap<User, Integer> getData(String category){
        // map to be returned
        HashMap<User, Integer> ret = new HashMap<>();
        String[] lineData;

        try {
            for (String line : Files.readAllLines(Paths.get("src/model/leaderboard/overall.txt"))) {
                lineData = line.split("\\|");
                // System.out.println(lineData[1]);

                // Add data to the map
                User xUser = new User(lineData[1], lineData[0]); // Order in files: ID|name|score
                ret.put(xUser, Integer.valueOf(lineData[2])); // key is user info, and score is value
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // System.out.println(ret);

        return ret;
    }

    /**
     * This class helps to bind each username to a respective userID
     * This will help in binding each user's info to his/her specific
     * score.
     */
    public class User implements Comparable {
        String username;
        String userID;
        Integer score;

        public User(String n, String i, Integer s) {
            this.username = n;
            this.userID = i;
            this.score = s;
        }

        // Getters
        public String getUsername() {
            return username;
        }
        public String getUserID() {
            return userID;
        }
        public Integer getScore() { return score; }

        // Setters
        public void setUsername(String name) { username = name; }
        public void setUserID(String id) { userID = id; }
        public void setScore(Integer s) { score = s; }

        /*
        @Override
        public int compareTo(User compareUser) {
            int compare = ((User)compareUser).getScore();

            // Ascending order
            return this.score - compare;
        }
        */

        @Override
        public int compareTo(Object compareUser) {
            int compare = ((User)compareUser).getScore();

            // Ascending order
            return this.score - compare;
        }
    }

    /**
     * This function helps up sort a map by values
     * @param unsortedMap
     * @return sortedMap
     */
    public Map<User, Integer> sortByValues(Map<User, Integer> unsortedMap){
        Map<User, Integer> sortedMap = new TreeMap<>(new ValueComparator(unsortedMap));
        sortedMap.putAll(unsortedMap);
        return sortedMap;
    }

    /**
     * This class helps compare the values of the map containing users' data
     * It serves as a helper class for sorting a map by values
     */
    public class ValueComparator implements Comparator{
        Map<User, Integer> map;

        public ValueComparator(Map map){
            this.map = map;
        }

        @Override
        public int compare(Object key1, Object key2) {
            Comparable val1 = (Comparable) map.get(key1);
            Comparable val2 = (Comparable) map.get(key2);
            return val2.compareTo(val1);
        }
    }

}
