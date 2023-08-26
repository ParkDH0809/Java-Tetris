package tetris;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class MyPanel extends JPanel {

    final int BACKGROUND_ROWS = 20;
    final int BACKGROUND_COLS = 10;

    JPanel gamePanel;
    JLabel[][] gameLabel = new JLabel[BACKGROUND_ROWS][BACKGROUND_COLS];

    MyPanel() {
        gamePanel = new JPanel(new GridLayout(BACKGROUND_ROWS, BACKGROUND_COLS));
        gamePanel.setBackground(Color.BLACK);

        makeTetrisBackground();

        add(gamePanel);
    }

    void makeTetrisBackground() {
        for(int i = 0; i < gameLabel.length; i++) {
            for(int j = 0; j < gameLabel[0].length; j++) {
                gameLabel[i][j] = new JLabel("Test");
                gameLabel[i][j].setBackground(Color.BLACK);

                gameLabel[i][j].setBorder(new LineBorder(Color.WHITE));
                // gameLabel[i][j].setOpaque(true);

                gamePanel.add(gameLabel[i][j]);
            }

        }
    }
}
