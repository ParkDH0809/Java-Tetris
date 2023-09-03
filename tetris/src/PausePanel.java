package tetris.src;

import javax.swing.*;

public class PausePanel extends JPanel {
    ImageIcon img = new ImageIcon("tetris/img/pauseImg.PNG");
    JLabel pauseLabel;

    PausePanel() {
        // setBounds(21, 5, 300, 600)
        setBounds(TetrisPanel.TETRIS_AREA_START_X, TetrisPanel.TETRIS_AREA_START_Y,
                TetrisPanel.BACKGROUND_COLS * TetrisPanel.BLOCK_SIZE,
                TetrisPanel.BACKGROUND_ROWS * TetrisPanel.BLOCK_SIZE);

        pauseLabel = new JLabel();
        pauseLabel.setIcon(img);
        setVisible(false);

        add(pauseLabel);

        initPauseThread(this).start();

    }

    Thread initPauseThread(JPanel pause) {
        Thread pauseThread = new Thread() {
            @Override
            public void run() {
                Thread.currentThread();
                while (!Thread.interrupted()) {
                    if (ScorePanel.isPause) {
                        pause.setVisible(true);
                        
                        while (true) {
                            if (!ScorePanel.isPause) {
                                    changeImage("count_3Img.PNG", 1000);
                                    changeImage("count_2Img.PNG", 1000);
                                    changeImage("count_1Img.PNG", 1000);

                                    setVisible(false);
                                    changeImage("pauseImg.PNG", 0);
                                    break;
                            }
                        }
                    }
                    if(TetrisPanel.isGameOver) {
                        
                        changeImage("gameoverImg.PNG", 0);
                        pause.setVisible(true);
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        this.interrupt();
                    }
                }
            }
        };
        
        return pauseThread;
    }

    void changeImage(String imgName, int time) {
        System.out.println("Test");
        img = new ImageIcon("tetris/img/" + imgName);
        pauseLabel.setIcon(img);

        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
