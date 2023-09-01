package tetris.src;

import java.awt.*;
import javax.swing.*;

class MyFrame extends JFrame{
    ImageIcon img = new ImageIcon("tetris/img/backImage.PNG");

    /**Layout: 
     * Frame -- Panel -- TetrisPanel
     *                |
     *                -- ScorePanel 
     */
    MyFrame() {
        setTitle("Java Tetris");
        setSize(700, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        
        add(addBackgroundPanel());
        
        setVisible(true);
    }

    public JPanel addBackgroundPanel() {

        JPanel backgroundPanel = new JPanel(new GridLayout(1, 2)) {

            public void paintComponent(Graphics g) {
                g.drawImage(img.getImage(), 0, 0, null);
                setOpaque(false);
                super.paintComponent(g);
            }

        };

        // add(new PausePanel());
        backgroundPanel.add(new TetrisPanel());
        backgroundPanel.add(new ScorePanel());
        
        return backgroundPanel;
    }
}