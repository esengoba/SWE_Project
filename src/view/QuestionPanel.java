package view;


import controller.Controller;

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
    JButton ansButton1 = createButton("A");
    JButton ansButton2 = createButton("B");
    JButton ansButton3 = createButton("C");
    JButton ansButton4 = createButton("D");
    JLabel questionNumLabel = new JLabel("");
    JTextArea questionTextArea = new JTextArea(2, 20); //
    JProgressBar progbar = new JProgressBar (0, 10);

    /*Variables for the timer*/
    JLabel counter = new JLabel("10", SwingConstants.CENTER);
    public final static int ONE_SECOND = 1000;
    private Timer timer;
    int i = Integer.parseInt(Constants.COUNTMAX);

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
        add(counter);

    }

    /**Reset the timer to 10 */
    public void resetTimer(){
        i = 10;
        timer.restart();
        counter.setForeground(Color.GREEN);
        counter.setText(Integer.toString(i));
    }

    //SMALL BUG: SEE HERE
    /**Countdown implements a ten second timer for each question*/
    public void countdown(){

        timer = new Timer(ONE_SECOND, new ActionListener() {

            public void actionPerformed(ActionEvent evt) {

                if(controller.questionCount > 10) {
                    controller.isGamePlayOver(controller.questionCount);
                    System.out.println("question 1");
                    timer.stop();
                }
                else if (i == 0){
                    resetTimer();
                    controller.controllerView.updateQuestionContent();
                    controller.questionCount++;
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
