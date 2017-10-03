import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class GameField implements ActionListener {

    private int userStep = 1;
    private static int[][] board = new int[3][3];
    private static Button[] buttons = new Button[9];
    private boolean flag;
    private static JFrame frame;
    private volatile boolean isBotTurn = true;

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
            infoBox("we have a winner!!!"
            );
        }
        if (!checkForEmptySpaceOnBoard()) {
            infoBox("Game over, there is no empty space on board!"
            );
        }
    }

    private static void infoBox(String infoMessage) {
        JOptionPane.showMessageDialog(frame, infoMessage,
                "Attention:", JOptionPane.INFORMATION_MESSAGE);
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
        if (userStep == 1) {
            button.setText("X");
        } else {
            button.setText("0");
        }
    }

    private class BotThread implements Runnable {
        public void run() {
            while (true) {
                if (!isBotTurn) {
                    generateMove();
                    changeUserTurn();
                    isBotTurn = true;
                }
            }
        }
    }
}
