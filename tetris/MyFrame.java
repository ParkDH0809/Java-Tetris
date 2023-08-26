package tetris;

import javax.swing.*;

class MyFrame extends JFrame{
    MyFrame() {
        setTitle("Java Tetris");
        setSize(500, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        add(new MyPanel());

        setVisible(true);
    }
}