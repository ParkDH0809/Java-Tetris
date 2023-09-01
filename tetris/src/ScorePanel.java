package tetris.src;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ScorePanel extends JPanel{
    JPanel scorePanel;
    static volatile boolean isPause = false;
    ScorePanel() {
        scorePanel = new JPanel(new GridLayout(1, 1));
        setOpaque(false);

        scoreArea();
        pauseArea();
        add(scorePanel);
    }

    void scoreArea() {
        JLabel scoreLabel = new JLabel("Score : ");
        add(scoreLabel);
    }

    void pauseArea() {
        JButton pauseButton = new JButton("Pause");
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isPause) {
                    isPause = false;
                    System.out.println("isPause: false");
                }
                else {
                    isPause = true;
                    System.out.println("isPause: true");
                }
                
            }
        });
        add(pauseButton);
    }
}