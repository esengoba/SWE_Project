package view;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import javax.swing.*;


public class Constants {
    //The size of the game play screen
    public static final int WINDOW_WIDTH = 700;
    public static final int WINDOW_HEIGHT = WINDOW_WIDTH * 2 / 3;

    public static final Font SCREEN_FONT = new Font("American Typewriter", Font.PLAIN, 40);
    public static final Font BUTTON_FONT = new Font("American Typewriter", Font.PLAIN, 20);
    public static final Font QUESTION_FONT = new Font("American Typewriter", Font.PLAIN, 20);
    public static final Font HOME_FONT = new Font("American Typewriter", Font.PLAIN, 70);

    // Button string constants for navigation
    public static final String BACK = "BACK";
    public static final String READY = "READY?";

    // Buttons string constants for home screen

    public static final String START = "START";
    public static final String SETTINGS = "SETTINGS";
    public static final String LEADERBOARD = "LEADERBOARD";

    // Title for the font
    public static final Font TITLE_FONT = new Font("American Typewriter", Font.BOLD, 60);

    // Length of the counter
    public static final String COUNTMAX = "10";

    // Category names
    public static final String GEEKOUT = "GEEK OUT";
    public static final String FOODIE = "FOODIE";
    public static final String EARLY2000JAMS = "EARLY 2000s JAMS";
    public static final String RATCHETFACTS = "RATCHET FACTS";
    public static final String SWECLASSFUNNYFACTS = "SWE CLASS FUNNY FACTS";
    public static final String RANDOM = "RANDOM";

    //Settings categories
    public static final String TIMER = "TIMER";
    public static final String SOUND = "SOUND";
}
