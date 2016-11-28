package view.panels;

import view.Constants;

import javax.swing.*;
import java.awt.*;

/**This class sets the interface for the user to
 * pick a category. The user can select from one of six
 * categories for game play.
 */

public class CategoryPanel extends MyPanel {

    public CategoryPanel(){
        //Add buttons
        super();

        setOpaque(false);
        setBounds(0,0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        setLayout(new GridLayout(9,1));
        JLabel categoryTitle = new JLabel("Please select one category for game play.");
        categoryTitle.setFont(Constants.BUTTON_FONT);
        add(categoryTitle, BorderLayout.EAST);
        add(createButton(Constants.GEEKOUT));
        add(createButton(Constants.EARLY2000JAMS));
        add(createButton(Constants.SWECLASSFUNNYFACTS));
        add(createButton(Constants.POPCULTURE));
        add(createButton(Constants.FOODIE));
        add(createButton(Constants.RANDOM));
        add(createButton(Constants.HOME));

    }


}