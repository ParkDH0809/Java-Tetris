package tetris.src;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.util.*;

public class TetrisPanel extends JPanel {


    final static int BACKGROUND_ROWS = 20;
    final static int BACKGROUND_COLS = 10;
    final static int TETRIS_AREA_START_X = 21;
    final static int TETRIS_AREA_START_Y = 5;
    final static int BLOCK_SIZE = 30;
    static boolean[][] fixedBlock = new boolean[BACKGROUND_ROWS][BACKGROUND_COLS];
    static boolean isGameOver = false;

    JPanel tetrisPanel;
    JLabel[][] gameLabel = new JLabel[BACKGROUND_ROWS][BACKGROUND_COLS];
    

    volatile int current_X;
    volatile int current_Y;
    int redColor, greenColor, blueColor;
    
    int[][] currentBlock;
    
    
    Block b;
    Thread timeThread;
    Thread bottomThread;

    TetrisPanel() {
        tetrisPanel = new JPanel(new GridLayout(BACKGROUND_ROWS, BACKGROUND_COLS));
        tetrisPanel.setBackground(Color.BLACK);
        setOpaque(false);

        makeTetrisBackground();
        add(tetrisPanel);

        makeNewBlock();
        keyEventMethod();

        timeThread = initBlockDownThread();
        timeThread.start();

        bottomThread = initBottomThread();
        bottomThread.start();

    }

    Thread initBottomThread() {

        Thread bottomThread = new Thread() {
            @Override
            public void run() {

                int x, y, n;
                Thread.currentThread();
                while(!Thread.interrupted()) {
                    
                    //Game Over
                    if(fixedBlock[0][4]) {
                        timeThread.interrupt();
                        this.interrupt();
                        isGameOver = true;
                    }

                    x = (current_X - TETRIS_AREA_START_X) / BLOCK_SIZE;
                    y = (current_Y - TETRIS_AREA_START_Y) / BLOCK_SIZE;
                    n = checkBottom();
                    
                    if(y + currentBlock.length - n == BACKGROUND_ROWS) {
                        fixBottomBlock(x, y);
                        checkLine(x, y, n);
                        makeNewBlock();
                        continue;
                    }

                    out: for(int i = 0; i < currentBlock.length; i++) {
                        for(int j = 0; j < currentBlock.length; j++) {
                            if(y + i + 1 < BACKGROUND_ROWS && x + j < BACKGROUND_COLS &&
                            currentBlock[i][j] == 1 && fixedBlock[y + i + 1][x + j]) {
                                fixBottomBlock(x, y);
                                checkLine(x, y, n);
                                makeNewBlock();
                                break out;
                            }
                        }
                    }
                }
            }
        };

        return bottomThread;
    }

    void checkLine(int x, int y, int n) {
        for(int i = y; i < y + currentBlock.length - n; i++) {
            for(int j = 0; j < BACKGROUND_COLS; j++) {
                if(!fixedBlock[i][j]) {
                    break;
                }

                if(j == 9) {
                    takedownBlock(i);
                    ScorePanel.score += 100;
                }
            }
        }
    }

    void takedownBlock(int y) {
        
        for(int i = 0; i < BACKGROUND_COLS; i++) {
            fixedBlock[y][i] = false;
            gameLabel[y][i].setBackground(Color.BLACK);
        }

        for(int i = y; i > 0; i--) {
            for(int j = 0; j < BACKGROUND_COLS; j++) {
                fixedBlock[i][j] = fixedBlock[i-1][j];
                gameLabel[i][j].setBackground(gameLabel[i-1][j].getBackground());
            }
        }
    }


    void fixBottomBlock(int x, int y) {
        for(int i = 0; i < currentBlock.length; i++) {
            for(int j = 0; j < currentBlock.length; j++) {
                if(currentBlock[i][j] == 1) {
                    gameLabel[y + i][x + j].setBackground(new Color(redColor, greenColor, blueColor));
                    fixedBlock[y + i][x + j] = true;
                }
            }
        }
    }


    Thread initBlockDownThread() {

        Thread timeThread = new Thread() {
            public void run() {

                Long beforeTime = System.currentTimeMillis();
                Long afterTime;

                Thread.currentThread();
                while(!Thread.interrupted()) {
                    try {
                        afterTime = System.currentTimeMillis();
                        if(ScorePanel.isPause) {
                            while (true) { 
                                if(!ScorePanel.isPause) {
                                    Thread.sleep(3200);
                                    break;
                                }
                            }
                        }

                        if(afterTime - beforeTime >= 1000) {
                            System.out.println("Time Test");
                            current_Y += BLOCK_SIZE;
                            repaint();

                            beforeTime = afterTime;
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    };
                }
            }
        };

        return timeThread;
    }

    void keyEventMethod() {
        this.setFocusable(true);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()) {
                    case KeyEvent.VK_DOWN:
                        current_Y += BLOCK_SIZE;
                        break;

                    case KeyEvent.VK_LEFT:
                        if(moveLeft())
                            current_X -= BLOCK_SIZE;
                        break;

                    case KeyEvent.VK_RIGHT:
                        if(moveRight())
                            current_X += BLOCK_SIZE;
                        break;

                    case KeyEvent.VK_Z:
                        changeShape();
                        break;
                }
                repaint();
            }
        });
    }


    boolean moveLeft() {
        if(current_X + (checkPositionLeft() * BLOCK_SIZE) - BLOCK_SIZE > 0)
            return true;
        return false;
    }

    boolean moveRight() {
        if(current_X + (checkPositionRight() * BLOCK_SIZE) - BLOCK_SIZE < 260)
            return true;
        return false;
    }

    int checkBottom() {
        int n = 0;
        out: for(int i = currentBlock.length - 1; i >= 0 ; i--) {
            for(int j = currentBlock.length - 1; j >= 0; j--) 
                if(currentBlock[i][j] == 1)
                    break out;
            n++;
            
        }
        return n;
    }

    
    int checkPositionLeft() {
        int n = 0;
        out: for(int i = 0; i < currentBlock.length; i++) {
            for(int j = 0; j < currentBlock.length; j++)
                if(currentBlock[j][i] == 1)
                    break out;
            n++;
        }

        return n;
    }

    int checkPositionRight() {
        int n = currentBlock.length - 1;
        out: for(int i = currentBlock.length - 1; i >= 0 ; i--) {
            for(int j = currentBlock.length - 1; j >= 0; j--) 
                if(currentBlock[j][i] == 1)
                    break out;
            n--;
        }

        return n;
    }

    void changeShape() {
        int[][] changeBlock = new int[currentBlock.length][currentBlock.length];

        //블록 복사
        for(int i = 0; i < currentBlock.length; i++) {
            changeBlock[i] = currentBlock[i].clone();
            Arrays.fill(currentBlock[i], 0);
        }
        
        //돌리기 작업
        if(currentBlock.length == 3) {             //ㄱ, ㄴ 도형
            for(int i = 0; i < currentBlock.length; i++) {
                for(int j = 0; j < currentBlock.length; j++) {
                    if(changeBlock[i][j] == 1)
                        currentBlock[j][currentBlock.length - 1 - i] = 1;
                }
            }
        } else {
            for(int i = 0; i < currentBlock.length; i++) {
                for(int j = 0; j < currentBlock.length; j++) {
                    if(changeBlock[i][j] == 1)
                        currentBlock[j][i] = 1;
                }
            }
        }

        checkOutLeft();
        checkOutRight();
    }   

    void checkOutLeft() {
        if(current_X >= 30)
            return;
            
        if(checkPositionLeft() == 0 && current_X < 0)
            current_X += BLOCK_SIZE;
    }

    void checkOutRight() {
        int n =  checkPositionRight();

        if(current_X + (BLOCK_SIZE * n) < 320)
            return;
        
        current_X -= BLOCK_SIZE * (current_X + (BLOCK_SIZE * n) - 291) / BLOCK_SIZE;
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

    void makeNewBlock() {
        b = new Block();

        current_X = TETRIS_AREA_START_X + BLOCK_SIZE * 4;
        current_Y = TETRIS_AREA_START_Y;

        currentBlock = b.getBlock();

        int[] rgb = b.getColor();
        redColor = rgb[0];
        greenColor = rgb[1];
        blueColor = rgb[2];
    }

    //Block 그림 메서드
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        for (int row = 0; row < currentBlock.length; row++) {
            for (int col = 0; col < currentBlock.length; col++) {
                if (currentBlock[row][col] == 1) {
                    g.setColor(new Color(redColor, greenColor, blueColor));
                    g.fillRect((current_X + col * BLOCK_SIZE), (current_Y + row * BLOCK_SIZE), BLOCK_SIZE, BLOCK_SIZE);

                    //Border Line
                    g.setColor(Color.WHITE);
                    g.fillRect((current_X + col * BLOCK_SIZE), (current_Y + row * BLOCK_SIZE), BLOCK_SIZE, 1);
                    g.fillRect((current_X + col * BLOCK_SIZE), (current_Y + row * BLOCK_SIZE), 1, BLOCK_SIZE);
                }
            }
        }
    }
}


class Block {
    int[][] block;
    int[] rgb = new int[3];

    int[] getColor() {
        return rgb;
    }

    public int[][] getBlock() {
        int n = (int)(Math.random() * 7);

        switch(n) {
            case 0:
                rgb[0] = 255;
                rgb[1] = 255;
                rgb[2] = 0;
                block = new int[][] {
                    {1, 1},
                    {1, 1}
                };
                break;
                
            case 1:
                
                rgb[0] = 0;
                rgb[1] = 255;
                rgb[2] = 255;
                block = new int[][] {
                    {1, 1, 1, 1},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0}
                };
                break;

            case 2:
                rgb[0] = 255;
                rgb[1] = 0;
                rgb[2] = 0;
                block = new int[][] {
                    {1, 1, 0},
                    {0, 1, 1},
                    {0, 0, 0}
                };
                break;

            case 3:
                rgb[0] = 0;
                rgb[1] = 255;
                rgb[2] = 0;
                block = new int[][] {
                    {0, 1, 1},
                    {1, 1, 0},
                    {0, 0, 0}
                };
                break;
                
            case 4:
                rgb[0] = 255;
                rgb[1] = 127;
                rgb[2] = 0;
                block = new int[][] {
                    {1, 1, 1},
                    {1, 0, 0},
                    {0, 0, 0}
                };
                break;

            case 5:
                rgb[0] = 0;
                rgb[1] = 0;
                rgb[2] = 255;
                block = new int[][] {
                    {1, 1, 1},
                    {0, 0, 1},
                    {0, 0, 0}
                };
                break;

            case 6:
                rgb[0] = 128;
                rgb[1] = 0;
                rgb[2] = 128;
                block = new int[][] {
                    {0, 1, 0},
                    {1, 1, 1},
                    {0, 0, 0}
                };
                break;
        }

        return block;
    }

}

