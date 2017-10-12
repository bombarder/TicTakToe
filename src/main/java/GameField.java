import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static javax.swing.JOptionPane.CANCEL_OPTION;
import static javax.swing.JOptionPane.NO_OPTION;
import static javax.swing.JOptionPane.YES_OPTION;

class GameField {

    private int userStep = 1;
    private static int[][] board = new int[3][3];
    private boolean flag;
    private boolean moveUserState;
    private static JFrame frame;
    private volatile boolean isBotTurn = true;
    private BufferedImage field;
    private BufferedImage imageOfX;
    private BufferedImage imageOfO;

    private final static int[][] winLines = {{1, 2, 3},
            {4, 5, 6},
            {7, 8, 9},
            {1, 4, 7},
            {2, 5, 8},
            {3, 6, 9},
            {1, 5, 9},
            {3, 5, 7}
    };

    GameField() {
        frame = new JFrame("Tic Tac Toe");
        frame.setSize(319, 348);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        try {
            field = ImageIO.read(new File("src/sourceFolder/field.png"));
            imageOfX = ImageIO.read(new File("src/sourceFolder/X.png"));
            imageOfO = ImageIO.read(new File("src/sourceFolder/O.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        final JPanel gamePanel = new JPanel() {
            public void paint(Graphics g) {
                super.paintComponent(g);
                g.drawImage(field, 0, 0, null);
                for (int i = 0; i < board.length; i++) {
                    for (int j = 0; j < board[i].length; j++) {
                        if (board[i][j] == 1) {
                            g.drawImage(imageOfX, 100 * i + 3, 100 * j + 3, null);
                        } else if (board[i][j] == 2) {
                            g.drawImage(imageOfO, 100 * i + 3, 100 * j + 3, null);
                        }
                    }
                }
            }
        };
        gamePanel.setBackground(Color.WHITE);
        gamePanel.setBounds(0, 0, 300, 300);
        gamePanel.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                if (x < 100 && y < 100) {
                    setCellResult(1);
                } else if (x < 100 && y < 200) {
                    setCellResult(2);
                } else if (x < 100 && y < 300) {
                    setCellResult(3);
                } else if (x < 200 && y < 100) {
                    setCellResult(4);
                } else if (x < 200 && y < 200) {
                    setCellResult(5);
                } else if (x < 200 && y < 300) {
                    setCellResult(6);
                } else if (x < 300 && y < 100) {
                    setCellResult(7);
                } else if (x < 300 && y < 200) {
                    setCellResult(8);
                } else if (x < 300 && y < 300) {
                    setCellResult(9);
                }
                gamePanel.repaint();
                changeUserTurn();
                beforeNextMoveChecking();
                isBotTurn = false;
            }

            public void mousePressed(MouseEvent e) {

            }

            public void mouseReleased(MouseEvent e) {

            }

            public void mouseEntered(MouseEvent e) {

            }

            public void mouseExited(MouseEvent e) {

            }
        });
        frame.add(gamePanel);
        frame.setLocation(800, 300);
        frame.setVisible(true);

        BotThread botThread = new BotThread();
        new Thread(botThread).start();
    }

    private boolean checkForWinner() {
        if (board[0][0] != 0 & (board[0][0] == board[0][1] & board[0][0] == board[0][2])) {
            return true;
        } else if (board[1][0] != 0 & (board[1][0] == board[1][1] & board[1][0] == board[1][2])) {
            return true;
        } else if (board[2][0] != 0 & (board[2][0] == board[2][1] & board[2][0] == board[2][2])) {
            return true;
        } else if (board[0][0] != 0 & (board[0][0] == board[1][1] & board[0][0] == board[2][2])) {
            return true;
        } else if (board[2][0] != 0 & (board[2][0] == board[1][1] & board[2][0] == board[0][2])) {
            return true;
        } else if (board[0][0] != 0 & (board[0][0] == board[1][0] & board[0][0] == board[2][0])) {
            return true;
        } else if (board[0][1] != 0 & (board[0][1] == board[1][1] & board[0][1] == board[2][1])) {
            return true;
        } else if (board[0][2] != 0 & (board[0][2] == board[1][2] & board[0][2] == board[2][2])) {
            return true;
        }
        return false;
    }

    private void changeUserTurn() {
        if (flag) {
            userStep = 1;
            flag = false;
        } else {
            userStep = 2;
            flag = true;
        }
    }

    private void generateMove() {
        int randomX = (int) (Math.random() * 3);
        int randomO = (int) (Math.random() * 3);
        if (board[randomX][randomO] == 0) {
            board[randomX][randomO] = userStep;
        } else {
            generateMove();
        }
    }

    private void generateSmartMove() {
        moveUserState = false;
        for (int i = 0; i < winLines.length && !moveUserState; i++) {
            checkLineForEqualsTwoElements(winLines[i]);
        }
        if (!moveUserState) {
            System.out.println("Random");
            generateMove();
        }
    }

    private void checkLineForEqualsTwoElements(int[] winLine) {
        int[] currentStateBoard = new int[3];
        for (int i = 0; i < winLine.length; i++) {
            currentStateBoard[i] = getCellResult(winLine[i]);
        }
        int counter = 0;
        for (int aCurrentStateBoard : currentStateBoard) {
            if (aCurrentStateBoard == 1) {
                counter++;
            }
        }
        System.out.println("W => " + Arrays.toString(currentStateBoard) + " X => " + counter);
        if (counter == 2) {
            for (int j = 0; j < currentStateBoard.length; j++) {
                if (currentStateBoard[j] == 0) {
                    setCellResult(winLine[j]);
                    moveUserState = true;
                }
            }
        }
    }

    private boolean checkForEmptySpaceOnBoard() {
        for (int[] aBoard : board) {
            for (int anABoard : aBoard) {
                if (anABoard == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    private void beforeNextMoveChecking() {
        if (checkForWinner()) {
            playAgain("we have a winner!!!");
        }
        if (!checkForEmptySpaceOnBoard()) {
            playAgain("Game over, there is no empty space on board! ");
        }
    }

    private void playAgain(String message) {
        int test = JOptionPane.showConfirmDialog(frame, message + "Play again?");
        switch (test) {
            case YES_OPTION:
                board = new int[3][3];
                userStep = 0;
                break;
            case NO_OPTION:
                System.exit(0);
            case CANCEL_OPTION:
                System.exit(0);
        }
    }

    private class BotThread implements Runnable {
        public void run() {
            while (true) {
                if (!isBotTurn) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (!checkForEmptySpaceOnBoard()) {
                        playAgain("Game over, there is no empty space on board! ");
                    }
                    generateSmartMove();
                    frame.repaint();
                    beforeNextMoveChecking();
                    changeUserTurn();
                    isBotTurn = true;
                }
            }
        }
    }

    private int getCellResult(int value) {
        if (value == 1) {
            return board[0][0];
        } else if (value == 2) {
            return board[0][1];
        } else if (value == 3) {
            return board[0][2];
        } else if (value == 4) {
            return board[1][0];
        } else if (value == 5) {
            return board[1][1];
        } else if (value == 6) {
            return board[1][2];
        } else if (value == 7) {
            return board[2][0];
        } else if (value == 8) {
            return board[2][1];
        } else if (value == 9) {
            return board[2][2];
        } else {
            return 0;
        }
    }

    private void setCellResult(int value) {
        if (value == 1) {
            board[0][0] = userStep;
        } else if (value == 2) {
            board[0][1] = userStep;
        } else if (value == 3) {
            board[0][2] = userStep;
        } else if (value == 4) {
            board[1][0] = userStep;
        } else if (value == 5) {
            board[1][1] = userStep;
        } else if (value == 6) {
            board[1][2] = userStep;
        } else if (value == 7) {
            board[2][0] = userStep;
        } else if (value == 8) {
            board[2][1] = userStep;
        } else if (value == 9) {
            board[2][2] = userStep;
        }
    }
}
