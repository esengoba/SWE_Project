package view.panels;


import controller.Controller;
import view.Constants;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**The Question Panel class implements the GUI for the question
 * page. This class also implements a timer for when the user
 * selects to have a more competitive game play experience.
 */
public class
QuestionPanel extends MyPanel {
    Controller controller;
    public HashMap<String, String> questionMap = new HashMap<>();
    public HashMap<String, ArrayList<String>> answerMap = new HashMap<>();
    ArrayList<String> playerAnswers = new ArrayList<String>();

    /*Buttons/fields for the answers and questions*/
    public JButton ansButton1 = createButton("A");
    public JButton ansButton2 = createButton("B");
    public JButton ansButton3 = createButton("C");
    public JButton ansButton4 = createButton("D");
    public JLabel questionNumLabel = new JLabel("");
    public JTextArea questionTextArea = new JTextArea(2, 20); //
    public JProgressBar progbar = new JProgressBar (0, 10);

    /*Variables for the timer*/
    public JLabel counter = new JLabel("10", SwingConstants.CENTER);
    public final static int ONE_SECOND = 1000;
    public Timer timer;
    public int i = Integer.parseInt(Constants.COUNTMAX);
    Color softRed = new Color(178,34,34);
    Color softGreen = new Color(0,100,0);
    Color softOrange = new Color(255,127,80);

    public QuestionPanel(Controller controller){

        super();
        this.controller = controller;
        setOpaque(false);
        setBounds(0,0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        setLayout(new GridLayout(9,1));
        add(progbar);
        questionNumLabel.setFont(Constants.QUESTION_FONT);
        add(questionNumLabel, BorderLayout.EAST);
        questionTextArea = setJTextArea(questionTextArea,"Question ID" );

        questionTextArea.setFont(Constants.QUESTION_FONT);
        add(questionTextArea, BorderLayout.EAST);

        add(ansButton1, Constants.QUESTION_FONT);
        answerButtonActions(ansButton1);

        add(ansButton2, Constants.QUESTION_FONT);
        answerButtonActions(ansButton2);

        add(ansButton3, Constants.QUESTION_FONT);
        answerButtonActions(ansButton3);

        add(ansButton4, Constants.QUESTION_FONT);
        answerButtonActions(ansButton4);


            counter.setFont(Constants.QUESTION_FONT);
            counter.setForeground(Color.GREEN);

            timer = new Timer(ONE_SECOND, new ActionListener() {

                public void actionPerformed(ActionEvent evt) {

                    if (i == 0) {

                        if (controller.questionCount == 10) {
                            controller.isGamePlayOver(controller.questionCount);
                            timer.stop();
                        } else {
                            resetTimer();
                            controller.controllerView.score.updateScore("");
                            controller.controllerView.updateQuestionContent();
                            controller.questionCount++;
                        }
                    } else {
                        i--;
                        if (i >= 7)
                            counter.setForeground(softGreen);
                        else if (i > 3)
                            counter.setForeground(softOrange);
                        else
                            counter.setForeground(softRed);
                        counter.setText(Integer.toString(i));
                    }
                }
            });
            add(counter);

    }

    /** This method resets the timer to 10. */
    public void resetTimer(){
        i = 10;
        timer.restart();
        counter.setForeground(softGreen);
        counter.setText(Integer.toString(i));
    }

    /**This method implements a ten second timer for each question.*/
    public void countdown(){
        timer.start();
    }

    public void answerButtonActions(JButton button) {
        button.addActionListener(controller);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }

        });

    }
}
