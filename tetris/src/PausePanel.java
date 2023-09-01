package tetris.src;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PausePanel extends JPanel{
    ImageIcon img = new ImageIcon("tetris/img/pause.PNG");
    JPanel pausePanel;
    PausePanel() {
        this.setBounds(TetrisPanel.TETRIS_AREA_START_X, TetrisPanel.TETRIS_AREA_START_Y, TetrisPanel.BACKGROUND_COLS * 30, TetrisPanel.BACKGROUND_ROWS * 30);
        pausePanel = new JPanel() {
            
            public void paintComponent(Graphics g) {
                g.drawImage(img.getImage(), 0, 0, null);
                // setOpaque(true);
                super.paintComponent(g);
            }

        };
        add(pausePanel);
        
    }
}
