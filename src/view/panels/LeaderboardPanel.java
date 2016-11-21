package view.panels;


import view.Constants;

import javax.swing.*;
import java.awt.*;

public class LeaderboardPanel extends MyPanel {
    public LeaderboardPanel(){

        super();

        setOpaque(false);
        setBounds(0,0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        setLayout(new GridLayout(9,1));
        JLabel leaderboardLabel = new JLabel("Leaderboard Standings");
        leaderboardLabel.setFont(Constants.BUTTON_FONT);
        add(leaderboardLabel, BorderLayout.CENTER);
        JLabel emptySpace = new JLabel();
        add(emptySpace);
        add(createButton(Constants.BACK));

    }
}
