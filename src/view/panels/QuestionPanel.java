package view.panels;


import controller.Controller;
import view.Constants;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

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

    public QuestionPanel(Controller controller){

        super();
        this.controller = controller;
        setOpaque(false);
        setBounds(0,0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        setLayout(new GridLayout(9,1));
        add(progbar);
        questionNumLabel.setFont(Constants.QUESTION_FONT);
        add(questionNumLabel, BorderLayout.EAST);

        questionTextArea.setText("Question ID");
        questionTextArea.setWrapStyleWord(true);
        questionTextArea.setLineWrap(true);
        questionTextArea.setEditable(true);
        questionTextArea.setOpaque(false);

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

                if (i == 0){

                    if(controller.questionCount == 10) {
                        controller.isGamePlayOver(controller.questionCount);
                        timer.stop();
                    } else {
                        resetTimer();
                        controller.controllerView.updateQuestionContent();
                        controller.questionCount++;
                    }
                }
                else {
                    i--;
                    if (i >= 7)
                        counter.setForeground(Color.GREEN);
                    else if (i > 3)
                        counter.setForeground(Color.YELLOW);
                    else
                        counter.setForeground(Color.RED);
                    counter.setText(Integer.toString(i));
                }
            }
        });
        add(counter);

    }

    /**Reset the timer to 10 */
    public void resetTimer(){
        i = 10;
        timer.restart();
        counter.setForeground(Color.GREEN);
        counter.setText(Integer.toString(i));
    }

    /**Countdown implements a ten second timer for each question*/
    public void countdown(){
        timer.start();
    }

    /*Double check the right action listeners are implemented*/
    public void answerButtonActions(JButton button) {
        button.addActionListener(controller);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String text = e.getActionCommand(); // returns a string with the text on hte button
                //System.out.println("She pressed" + text);
               // playerAnswers.add(text);
                //System.out.println(text);

            }
        });

    }
}
