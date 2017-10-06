import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static javax.swing.JOptionPane.CANCEL_OPTION;
import static javax.swing.JOptionPane.NO_OPTION;
import static javax.swing.JOptionPane.YES_OPTION;

class GameField implements ActionListener {

    private int userStep = 1;
    private static int[][] board = new int[3][3];
    private static Button[] buttons = new Button[9];
    private boolean flag;
    private boolean moveUserState;
    private static JFrame frame;
    private volatile boolean isBotTurn = true;
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
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel gamePanel = new JPanel();
        GridLayout gridLayout = new GridLayout(3, 3);
        gamePanel.setLayout(gridLayout);

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new Button(i);
            buttons[i].num = i + 1;
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 40));
            buttons[i].addActionListener(this);
            gamePanel.add(buttons[i]);
        }
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
            if (randomX == 0 && randomO == 0) {
                repaintBoard(buttons[0]);
            } else if (randomX == 0 & randomO == 1) {
                repaintBoard(buttons[1]);
            } else if (randomX == 0 & randomO == 2) {
                repaintBoard(buttons[2]);
            } else if (randomX == 1 & randomO == 0) {
                repaintBoard(buttons[3]);
            } else if (randomX == 1 & randomO == 1) {
                repaintBoard(buttons[4]);
            } else if (randomX == 1 & randomO == 2) {
                repaintBoard(buttons[5]);
            } else if (randomX == 2 & randomO == 0) {
                repaintBoard(buttons[6]);
            } else if (randomX == 2 & randomO == 1) {
                repaintBoard(buttons[7]);
            } else if (randomX == 2 & randomO == 2) {
                repaintBoard(buttons[8]);
            }
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
        for (int i = 0; i < currentStateBoard.length; i++) {
            if (currentStateBoard[i] == 1) {
                counter++;
            }
        }
        System.out.println("W => " + Arrays.toString(currentStateBoard) + " X => " + counter);
        if (counter == 2) {
            for (int j = 0; j < currentStateBoard.length; j++) {
                if (currentStateBoard[j] == 0) {
                    setCellResult(winLine[j]);
                    repaintBoard(winLine[j]);
                    moveUserState = true;
                }
            }
        }
    }

    private void repaintBoard(int celLocation) {
        if (celLocation == 1) {
            board[0][0] = userStep;
            repaintBoard(buttons[0]);
        } else if (celLocation == 2) {
            board[0][1] = userStep;
            repaintBoard(buttons[1]);
        } else if (celLocation == 3) {
            board[0][2] = userStep;
            repaintBoard(buttons[2]);
        } else if (celLocation == 4) {
            board[1][0] = userStep;
            repaintBoard(buttons[3]);
        } else if (celLocation == 5) {
            board[1][1] = userStep;
            repaintBoard(buttons[4]);
        } else if (celLocation == 6) {
            board[1][2] = userStep;
            repaintBoard(buttons[5]);
        } else if (celLocation == 7) {
            board[2][0] = userStep;
            repaintBoard(buttons[6]);
        } else if (celLocation == 8) {
            board[2][1] = userStep;
            repaintBoard(buttons[7]);
        } else if (celLocation == 9) {
            board[2][2] = userStep;
            repaintBoard(buttons[8]);
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

    public void actionPerformed(ActionEvent event) {
        for (Button button : buttons) {
            if (event.getSource() == button) {
                setUserMove(button);
                repaintBoard(button);
                beforeNextMoveChecking();
                changeUserTurn();
            }
        }
        isBotTurn = false;
    }

    private void beforeNextMoveChecking() {
        if (checkForWinner()) {
            playAgain("we have a winner!!!");
        }
        if (!checkForEmptySpaceOnBoard()) {
            playAgain("Game over, there is no empty space on board!");
        }
    }

    private void playAgain(String message) {
        int test = JOptionPane.showConfirmDialog(frame, message + "Play again?");
        switch (test) {
            case YES_OPTION:
                board = new int[3][3];
                userStep = 0;
                for (Button button : buttons) {
                    repaintBoard(button);
                }
                break;
            case NO_OPTION:
                System.exit(0);
            case CANCEL_OPTION:
                System.exit(0);
        }
    }

    private void setUserMove(Button button) {
        if (button.num == 1) {
            board[0][0] = userStep;
        } else if (button.num == 2) {
            board[0][1] = userStep;
        } else if (button.num == 3) {
            board[0][2] = userStep;
        } else if (button.num == 4) {
            board[1][0] = userStep;
        } else if (button.num == 5) {
            board[1][1] = userStep;
        } else if (button.num == 6) {
            board[1][2] = userStep;
        } else if (button.num == 7) {
            board[2][0] = userStep;
        } else if (button.num == 8) {
            board[2][1] = userStep;
        } else if (button.num == 9) {
            board[2][2] = userStep;
        }
    }

    private void repaintBoard(Button button) {
        BufferedImage img;
        if (userStep == 1) {
            try {
                img = ImageIO.read(new File("src/sourceFolder/X.png"));
                button.setIcon(new ImageIcon(img.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH)));
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/sourceFolder/xsound.wav").getAbsoluteFile());
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            }
        } else if (userStep == 2) {
            try {
                img = ImageIO.read(new File("src/sourceFolder/0.png"));
                button.setIcon(new ImageIcon(img.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH)));
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/sourceFolder/osound.wav").getAbsoluteFile());
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            }
        } else if ((userStep == 0)) {
            try {
                img = ImageIO.read(new File("src/sourceFolder/empty.png"));
                button.setIcon(new ImageIcon(img.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH)));
            } catch (IOException e) {
                e.printStackTrace();
            }
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
                    generateSmartMove();
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
