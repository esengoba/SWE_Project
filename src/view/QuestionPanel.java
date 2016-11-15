package view;


import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class QuestionPanel extends MyPanel {

    public HashMap<String, String> questionMap = new HashMap<>();
    public HashMap<String, ArrayList<String>> answerMap = new HashMap<>();
    ArrayList<String> playerAnswers = new ArrayList<String>();

    JButton ansButton1 = createButton("A");
    JButton ansButton2 = createButton("B");
    JButton ansButton3 = createButton("C");
    JButton ansButton4 = createButton("D");
    JLabel questionNumLabel = new JLabel("Question ID");
    JTextArea questionTextArea = new JTextArea(2, 20); //
    JProgressBar progbar = new JProgressBar (0, 10);
    JLabel counter = new JLabel("Counter from 10 to 0", SwingConstants.CENTER);
    Controller controller;
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
        //updateQuestionContent();

        counter.setFont(Constants.QUESTION_FONT);
        add(counter);

    }

    public void countdown(){
       /* JLabel counter = new JLabel("Counter from 10 to 0", SwingConstants.CENTER);
        counter.setFont(Constants.QUESTION_FONT);
        add(counter);*/

        final java.util.Timer timer = new java.util.Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int i = Integer.parseInt(Constants.COUNTMAX);
            public void run() {
                int temp = i--;
                counter.setText(String.valueOf(temp));

                if (temp >= 7)
                    counter.setForeground(Color.GREEN);
                else if (temp > 3)
                    counter.setForeground(Color.YELLOW);
                else
                    counter.setForeground(Color.RED);

                if (i< 0)
                    timer.cancel();
            }
        }, 0, 1000);
    }

    /*Double check the right action listeners are implemented*/
    public void answerButtonActions(JButton button) {
        button.addActionListener(controller);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String text = e.getActionCommand(); // returns a string with the text on hte button
                /*TO DO: Implement*/
                /* Highlight answer and then go the next screen*/
                /* Restart the timer*/
                /*Update the pages*/
                /*Store their selection in the user answer map*/

                System.out.println("She pressed" + text);
                playerAnswers.add(text);
                System.out.println(text);
                //change screen


            }
        });

    }
}
