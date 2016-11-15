package view;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**This class supports the interface for showing the final score
 * to the user. This class also extends MyPanel.
 */
public class FinalScorePanel extends MyPanel {
    JLabel scoreTitle = new JLabel("Results");
    public JLabel scoreLabel = new JLabel("Your Score");

    public FinalScorePanel(){
        super();
        setOpaque(false);
        setBounds(0,0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        setLayout(new GridLayout(8,1));
        scoreTitle.setFont(Constants.TITLE_FONT);

        scoreLabel.setFont(Constants.SCREEN_FONT);
        add(scoreTitle, BorderLayout.EAST);

        add(scoreLabel, BorderLayout.EAST);
        add(createButton("BACK")); /*Returns to the home button*/

    }

    /**This function sets the final score for the panel.
     */
   public void setScoreLabel(int s){
       scoreLabel.setText("Your score: " + Integer.toString(s));
    }
}
