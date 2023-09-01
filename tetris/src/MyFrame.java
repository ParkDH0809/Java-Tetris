package tetris.src;

import java.awt.*;
import javax.swing.*;

class MyFrame extends JFrame{
    Image img = new ImageIcon("tetris/img/backgroundImg.PNG").getImage();

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


        add(new PausePanel());
        add(addBackgroundPanel());
        
        setVisible(true);
    }

    public JPanel addBackgroundPanel() {

        JPanel backgroundPanel = new JPanel(new GridLayout(1, 2)) {

            public void paintComponent(Graphics g) {
                g.drawImage(img, 0, 0, null);
                setOpaque(false);
                super.paintComponent(g);
            }

        };

        backgroundPanel.add(new TetrisPanel());
        backgroundPanel.add(new ScorePanel());
        
        return backgroundPanel;
    }
}