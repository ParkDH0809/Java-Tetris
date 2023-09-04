package tetris.src;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;

public class ScorePanel extends JPanel{
    
    static volatile boolean isPause = false;
    static volatile int score = 0;

    JPanel scorePanel;
    ScorePanel() {
        scorePanel = new JPanel(new GridLayout(1, 1));
        setOpaque(false);

        scoreArea();
        pauseArea();
        add(scorePanel);

    }

    void scoreArea() {
        JLabel scoreLabel = new JLabel("Score: " + score);
        add(scoreLabel);

        Thread scoreThread = new Thread() {
            @Override
            public void run() {
                while(true) {
                    scoreLabel.setText("Score: " + score);
                }
            }
        };

        scoreThread.start();
    }

    void pauseArea() {
        JButton pauseButton = new JButton("Pause");
        pauseButton.setBackground(new Color(255, 204, 051));
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isPause) {
                    isPause = false;
                    pauseButton.setEnabled(false);

                    Timer timer = new Timer();
                    TimerTask task = new TimerTask() {
                        @Override
                        public void run() {
                            pauseButton.setEnabled(true);
                        }
                    };
                    timer.schedule(task, 3000);
                    System.out.println("isPause: false");
                } else {
                    isPause = true;
                    System.out.println("isPause: true");
                }
                
            }
        });
        add(pauseButton);
    }
}