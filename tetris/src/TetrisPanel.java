package tetris.src;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class TetrisPanel extends JPanel implements KeyListener{

    final int BACKGROUND_ROWS = 20;
    final int BACKGROUND_COLS = 10;
    
    JPanel tetrisPanel;
    JLabel[][] gameLabel = new JLabel[BACKGROUND_ROWS][BACKGROUND_COLS];

    int blockX = 140, blockY = 5;
    int[][] currentShape = {{1, 1}, {1, 1}};


    TetrisPanel() {
        tetrisPanel = new JPanel(new GridLayout(BACKGROUND_ROWS, BACKGROUND_COLS));
        tetrisPanel.setBackground(Color.BLACK);
        setOpaque(false);

        makeTetrisBackground();
        add(tetrisPanel);



        addBlock();
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



    //block을 그리는 메서드
    @Override
    public void paint(Graphics g) {
        // TODO Auto-generated method stub
        super.paint(g);
        for (int row = 0; row < currentShape.length; row++) {
            for (int col = 0; col < currentShape[row].length; col++) {
                if (currentShape[row][col] == 1) {
                    g.fillRect((blockX + col), (blockY + row), 30, 30);
                }
            }
        }
    }


    void addBlock() {
        Block b = new Block();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_LEFT) {// 왼쪽 화살표 키 처리
            if (canMoveTo(blockX - 1, blockY)) {
                blockX--;
            }
        } else if (keyCode == KeyEvent.VK_RIGHT) {// 오른쪽 화살표 키 처리
            if (canMoveTo(blockX + 1, blockY)) {
                blockX++;
            }
        } else if (keyCode == KeyEvent.VK_DOWN) {// 아래 화살표 키 처리
            if (canMoveTo(blockX, blockY + 1)) {
                blockY++;
            } else {
                // mergeShapeToBoard();
                // checkAndClearLines();
                // createNewShape();
                // blockX = BOARD_WIDTH / 2 - 1;
                // blockY = 0;
            }
        }
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyPressed'");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
    }

    private boolean canMoveTo(int x, int y) {

        return true;
    }
}


class Block {
    int type;
    int[][] block = {
        {1, 1},
        {1, 1}
    };

    

}