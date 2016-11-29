package view.panels;

import model.Leaderboard;
import view.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/** This panel sets the GUI for the Leaderboard panel. This panel
 * works with the Leaderboard class to display the standings for
 * each category and overall to the user.
 */

public class LeaderboardPanel extends MyPanel implements ActionListener {
    private ArrayList<JButton> buttonLeaderList = new ArrayList<JButton>();
    private JTextArea leaderStandings = new JTextArea(10, 30);
    private JLabel catLabel = new JLabel("Category Standings");
    JButton overallStats = this.createLeaderButton(Constants.OVERALL);
    JButton randomStats = this.createLeaderButton(Constants.RANDOM);
    JButton ratchetStats = this.createLeaderButton(Constants.POPCULTURE);
    JButton foodieStats = this.createLeaderButton(Constants.FOODIE);
    JButton classStats = this.createLeaderButton(Constants.SWECLASSFUNNYFACTS);
    JButton jamsStats = this.createLeaderButton(Constants.EARLY2000JAMS);
    JButton geekStats = this.createLeaderButton(Constants.GEEKOUT);
    
    // Create a  Leaderboard object
    private Leaderboard leaderboard = new Leaderboard();

    public LeaderboardPanel(){
        super();

        setOpaque(false);
        setBounds(0,0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        setLayout(new GridLayout(4,1));
        JLabel leaderboardLabel = new JLabel("Leaderboard Standings");
        leaderboardLabel.setFont(Constants.TITLE_FONT);
        add(leaderboardLabel);

        JPanel panel1 = createNewPanel();
        JPanel panel2 = createNewPanel();
        panel1.setLayout(new GridLayout(4, 2));
        panel2.setLayout(new GridLayout(1,1));
        add(leaderboardLabel);
        catLabel.setFont(Constants.OTHER_FONT);

        // Define the area where to display the list of top scorers
        leaderStandings = setJTextArea(leaderStandings, "");

        panel2.add(catLabel);
        panel2.add(leaderStandings);
        panel1.add(overallStats);
        panel1.add(ratchetStats);
        panel1.add(randomStats);
        panel1.add(foodieStats);
        panel1.add(classStats);
        panel1.add(jamsStats);
        panel1.add(geekStats);
        panel1.add(createButton(Constants.HOME));
        panel2.add(catLabel);
        panel2.add(leaderStandings);


        addActionListener(buttonLeaderList);
        add(panel2);
        add(panel1);
    }

    JPanel createNewPanel(){
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        panel.setOpaque(false);
        return panel;
    }

    JButton createLeaderButton(String text) {
        JButton button = new JButton(text);
        button.setFont(Constants.BUTTON_FONT);
        buttonLeaderList.add(button);
        return button;
    }

    public void addActionListener(ArrayList<JButton> buttons){

        for (JButton button: buttons){
            button.addActionListener(this);

        }
    }


    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case Constants.OVERALL:
                resetStanding();
                catLabel.setText("Overall Standings");
                leaderboard.setCategoryPathName(0);
                displayCategory();
                break;
            case Constants.GEEKOUT:
                resetStanding();
                catLabel.setText("Geek Out Standings");
                leaderboard.setCategoryPathName(1);
                displayCategory();
                break;
            case Constants.EARLY2000JAMS:
                resetStanding();
                catLabel.setText("2000s Jams Standings");
                leaderboard.setCategoryPathName(2);
                displayCategory();
                break;
            case Constants.FOODIE:
                resetStanding();
                catLabel.setText("Foodie Standings");
                leaderboard.setCategoryPathName(3);
                displayCategory();
                break;
            case Constants.POPCULTURE:
                resetStanding();
                catLabel.setText("Pop Culture Standings");
                leaderboard.setCategoryPathName(4);
                displayCategory();
                break;
            case Constants.SWECLASSFUNNYFACTS:
                resetStanding();
                catLabel.setText("Class Facts Standings");
                leaderboard.setCategoryPathName(5);
                displayCategory();
                break;
            case Constants.RANDOM:
                resetStanding();
                catLabel.setText("Random Standings");
                leaderboard.setCategoryPathName(6);
                displayCategory();
                break;
            default:
                break;
        }
    }

    /**
     * Reset the standing when the user switches back and forth between
     * categories on the leaderboard page
     */
    private void resetStanding() { leaderStandings.setText(""); }

    /**
     * Helper function to display each category
     */
    private void displayCategory(){
        Integer counter = 1;
        ArrayList<Leaderboard.User> data = leaderboard.getData();

        for (Leaderboard.User u: data) {
            String line = counter.toString() + ". " + u.getUsername() + " - " + u.getScore() + "\n";
            leaderStandings.append(line);
            counter++;
        }
    }

}
