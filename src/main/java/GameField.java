import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class GameField implements ActionListener {

    private int userStep = 1;
    private static int[][] board = new int[3][3];
    private static Button[] buttons = new Button[9];
    private boolean flag;

    GameField() {
        JFrame frame = new JFrame("Tic Tac Toe");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        GridLayout gridLayout = new GridLayout(3, 3);
        panel.setLayout(gridLayout);
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new Button(i);
            buttons[i].setFont(new Font("Arial", Font.PLAIN,40));
            buttons[i].addActionListener(this);
            panel.add(buttons[i]);
        }
        frame.add(panel);
        frame.setVisible(true);
    }

    private String convertBoardCellValue(int horizontal, int vertical) {
        if (board[horizontal][vertical] == 1) {
            return " X ";
        } else {
            return " O ";
        }
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

    private void userMoveStatus() {
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
            userMoveStatus();
            board[randomX][randomO] = userStep;
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
                if (!checkForEmptySpaceOnBoard()) {
                    System.out.println("Game over, there is no empty space on board!");
                }
                if (button == buttons[0]) {
                    board[0][0] = userStep;
                    button.setText(convertBoardCellValue(0, 0));
                } else if (button == buttons[1]) {
                    board[0][1] = userStep;
                    button.setText(convertBoardCellValue(0, 1));
                } else if (button == buttons[2]) {
                    board[0][2] = userStep;
                    button.setText(convertBoardCellValue(0, 2));
                } else if (button == buttons[3]) {
                    board[1][0] = userStep;
                    button.setText(convertBoardCellValue(1, 0));
                } else if (button == buttons[4]) {
                    board[1][1] = userStep;
                    button.setText(convertBoardCellValue(1, 1));
                } else if (button == buttons[5]) {
                    board[1][2] = userStep;
                    button.setText(convertBoardCellValue(1, 2));
                } else if (button == buttons[6]) {
                    board[2][0] = userStep;
                    button.setText(convertBoardCellValue(2, 0));
                } else if (button == buttons[7]) {
                    board[2][1] = userStep;
                    button.setText(convertBoardCellValue(2, 1));
                } else if (button == buttons[8]) {
                    board[2][2] = userStep;
                    button.setText(convertBoardCellValue(2, 2));
                }
                if (checkForWinner()) {
                    System.out.println("we have a winner!!!");
                }
                userMoveStatus();
            }
        }
    }
}
