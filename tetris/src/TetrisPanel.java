package tetris.src;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class TetrisPanel extends JPanel {

    final int BACKGROUND_ROWS = 20;
    final int BACKGROUND_COLS = 10;

    JPanel tetrisPanel;
    JLabel[][] gameLabel = new JLabel[BACKGROUND_ROWS][BACKGROUND_COLS];

    TetrisPanel() {
        tetrisPanel = new JPanel(new GridLayout(BACKGROUND_ROWS, BACKGROUND_COLS));
        tetrisPanel.setBackground(Color.BLACK);
        setOpaque(false);

        makeTetrisBackground();

        add(tetrisPanel);
    }

    void makeTetrisBackground() {
        for(int i = 0; i < gameLabel.length; i++) {
            for(int j = 0; j < gameLabel[0].length; j++) {
                gameLabel[i][j] = new JLabel();
                gameLabel[i][j].setBackground(Color.BLACK);
                gameLabel[i][j].setPreferredSize(new Dimension(30, 30));
                gameLabel[i][j].setOpaque(true);
                gameLabel[i][j].setBorder(new LineBorder(Color.GRAY));
                
                tetrisPanel.add(gameLabel[i][j]);
            }

        }
    }
}
