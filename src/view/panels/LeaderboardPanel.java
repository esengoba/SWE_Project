package view.panels;

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
    ArrayList<JButton> buttonLeaderList = new ArrayList<JButton>();
    JTextArea leaderStandings = new JTextArea(10, 30);
    JLabel catLabel = new JLabel("Category Standings");
    JButton overallStats = this.createLeaderButton(Constants.OVERALL);
    JButton randomStats = this.createLeaderButton(Constants.RANDOM);
    JButton ratchetStats = this.createLeaderButton(Constants.RATCHETFACTS);
    JButton foodieStats = this.createLeaderButton(Constants.FOODIE);
    JButton classStats = this.createLeaderButton(Constants.SWECLASSFUNNYFACTS);
    JButton jamsStats = this.createLeaderButton(Constants.EARLY2000JAMS);
    JButton geekStats = this.createLeaderButton(Constants.GEEKOUT);

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

        leaderStandings = setJTextArea(leaderStandings, "1. Dream Team - 100");
        leaderStandings.append(Constants.NEWLINE + "2. JoJo - 20");
        leaderStandings.append(Constants.NEWLINE + "3. ELIZ - 15");

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
                catLabel.setText("Overall Standings");
                /**EXAMPLE ALGORITHM
                 *Clear the 'leaderStandings' text area and read in standings from file
                 * * Append each line with the correct stats
                 * */
                break;
            case Constants.RANDOM:
                catLabel.setText("Random Standings");
                break;
            case Constants.FOODIE:
                catLabel.setText("Foodie Standings");
                break;
            case Constants.SWECLASSFUNNYFACTS:
                catLabel.setText("Class Facts Standings");
                break;
            case Constants.EARLY2000JAMS:
                catLabel.setText("2000s Jams Standings");
                break;
            case Constants.GEEKOUT:
                catLabel.setText("Geek Out Standings");
                break;
            case Constants.RATCHETFACTS:
                catLabel.setText("Ratchet Standings");
                break;
            default:
                break;
        }

    }

}
