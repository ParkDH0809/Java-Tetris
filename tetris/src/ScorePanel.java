package tetris.src;

import java.awt.*;
import javax.swing.*;

public class ScorePanel extends JPanel{
    JPanel scorePanel;

    ScorePanel() {
        scorePanel = new JPanel(new GridLayout(1, 1));
        setOpaque(false);

        makeScore();
        
        add(scorePanel);
    }

    void makeScore() {
        JLabel scoreLabel = new JLabel("test");
        add(scoreLabel);
    }
}
