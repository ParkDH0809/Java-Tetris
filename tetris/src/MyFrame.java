package tetris.src;

import java.awt.*;
import javax.swing.*;

class MyFrame extends JFrame{
    MyFrame() {
        setTitle("Java Tetris");
        setSize(700, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new GridLayout(1, 2));

        add(new TetrisPanel());
        add(new ScorePanel());
        
        setVisible(true);
    }
}