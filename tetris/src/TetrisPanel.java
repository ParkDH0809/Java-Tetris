package tetris.src;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class TetrisPanel extends JPanel {

    final int BACKGROUND_ROWS = 20;
    final int BACKGROUND_COLS = 10;
    final int BLOCK_SIZE = 30;

    JPanel tetrisPanel;
    JLabel[][] gameLabel = new JLabel[BACKGROUND_ROWS][BACKGROUND_COLS];

    int current_X = 140, current_Y = 5;
    int[][] currentBlock;
    int redColor, greenColor, blueColor;

    TetrisPanel() {
        tetrisPanel = new JPanel(new GridLayout(BACKGROUND_ROWS, BACKGROUND_COLS));
        tetrisPanel.setBackground(Color.BLACK);
        setOpaque(false);

        makeTetrisBackground();
        add(tetrisPanel);

        
        // KeyEvenetThread keyThread = new KeyEvenetThread(tetrisPanel);
        // keyThread.start();

        makeNewBlock();
        keyEventMethod();

    }

    void keyEventMethod() {
        this.setFocusable(true);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()) {
                    case KeyEvent.VK_DOWN:
                        System.out.println("Test_down");
                        current_Y += 30;
                        
                        break;
                    case KeyEvent.VK_RIGHT:
                        System.out.println("Test_right");
                        current_X += 30;
                        break;
                    case KeyEvent.VK_LEFT:
                        System.out.println("Test_left");
                        current_X -= 30;
                        break;
                }

                repaint();
            }
        });

        
    }


    //Tetris 배경 생성 메서드
    void makeTetrisBackground() {
        for(int i = 0; i < gameLabel.length; i++) {
            for(int j = 0; j < gameLabel[0].length; j++) {
                gameLabel[i][j] = new JLabel();
                gameLabel[i][j].setBackground(Color.BLACK);
                gameLabel[i][j].setPreferredSize(new Dimension(BLOCK_SIZE, BLOCK_SIZE));
                gameLabel[i][j].setOpaque(true);
                gameLabel[i][j].setBorder(new LineBorder(Color.GRAY));
                
                tetrisPanel.add(gameLabel[i][j]);
            }
        }
    }

    Block makeNewBlock() {
        Block b = new Block();

        currentBlock = b.getBlock();

        int[] rgb = b.getColor();
        redColor = rgb[0];
        greenColor = rgb[1];
        blueColor = rgb[2];

        return b;
    }

    //Block 그림 메서드
    @Override
    public void paint(Graphics g) {
        // TODO Auto-generated method stub
        super.paint(g);
        g.setColor(new Color(redColor, greenColor, blueColor));
        for (int row = 0; row < currentBlock.length; row++) {
            for (int col = 0; col < currentBlock[row].length; col++) {
                if (currentBlock[row][col] == 1) {
                    g.fillRect((current_X + col * BLOCK_SIZE), (current_Y + row * BLOCK_SIZE), BLOCK_SIZE, BLOCK_SIZE);
                }
            }
        }
    }
}


class Block {
    int type;
    int[][] block;
    int[] rgb = new int[3];

    int[] getColor() {
        return rgb;
    }

    public int[][] getBlock() {
        int n = (int)(Math.random() * 6);

        switch(n) {
            case 0:
                rgb[0] = 255;
                rgb[1] = 0;
                rgb[2] = 0;
                block = new int[][] {
                    {1, 1},
                    {1, 1}
                };
                break;
                
            case 1:
                rgb[0] = 255;
                rgb[1] = 128;
                rgb[2] = 0;
                block = new int[][] {
                    {1, 1, 1, 1}
                };
                break;

            case 2:
                rgb[0] = 255;
                rgb[1] = 255;
                rgb[2] = 0;
                block = new int[][] {
                    {1, 1, 0},
                    {0, 1, 1}
                };
                break;

            case 3:
                rgb[0] = 0;
                rgb[1] = 255;
                rgb[2] = 0;
                block = new int[][] {
                    {0, 1, 1},
                    {1, 1, 0}
                };
                break;
                
            case 4:
                rgb[0] = 0;
                rgb[1] = 0;
                rgb[2] = 255;
                block = new int[][] {
                    {0, 0, 1},
                    {1, 1, 1}
                };
                break;

            case 5:
                rgb[0] = 127;
                rgb[1] = 0;
                rgb[2] = 255;
                block = new int[][] {
                    {1, 0, 0},
                    {1, 1, 1}
                };
                break;
        }

        return block;
    }

}