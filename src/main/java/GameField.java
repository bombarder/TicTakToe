import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class GameField implements ActionListener {

    private int userStep = 1;
    private static int[][] board = new int[3][3];
    private static Buttons[] buttons = new Buttons[9];
    private boolean flag;

    GameField() {
        JFrame frame = new JFrame();
        frame.setSize(400, 400);
        JPanel panel = new JPanel();
        GridLayout gridLayout = new GridLayout(3, 3);
        panel.setLayout(gridLayout);
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new Buttons(i);
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

    boolean checkForWinner() {
        if (board[0][0] != 0 & (board[0][0] == board[0][1] & board[0][0] == board[0][2])) {
            System.out.println("We have a winner!!!");
            return true;
        } else if (board[1][0] != 0 & (board[1][0] == board[1][1] & board[1][0] == board[1][2])) {
            System.out.println("We have a winner!!!");
            return true;
        } else if (board[2][0] != 0 & (board[2][0] == board[2][1] & board[2][0] == board[2][2])) {
            System.out.println("We have a winner!!!");
            return true;
        } else if (board[0][0] != 0 & (board[0][0] == board[1][1] & board[0][0] == board[2][2])) {
            System.out.println("We have a winner!!!");
            return true;
        } else if (board[2][0] != 0 & (board[2][0] == board[1][1] & board[2][0] == board[0][2])) {
            System.out.println("We have a winner!!!");
            return true;
        } else if (board[0][0] != 0 & (board[0][0] == board[1][0] & board[0][0] == board[2][0])) {
            System.out.println("We have a winner!!!");
            return true;
        } else if (board[0][1] != 0 & (board[0][1] == board[1][1] & board[0][1] == board[2][1])) {
            System.out.println("We have a winner!!!");
            return true;
        } else if (board[0][2] != 0 & (board[0][2] == board[1][2] & board[0][2] == board[2][2])) {
            System.out.println("We have a winner!!!");
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
        if (event.getSource() == buttons[0]) {
            if (!checkForEmptySpaceOnBoard()) {
                System.out.println("Game over, there is no empty space on board!");
            }
            board[0][0] = userStep;
            if (checkForWinner()) {
                System.out.println("we have a winner!!!");
            }
            buttons[0].setText(convertBoardCellValue(0, 0));
            userMoveStatus();
        } else if (event.getSource() == buttons[1]) {
            if (!checkForEmptySpaceOnBoard()) {
                System.out.println("Game over, there is no empty space on board!");
            }
            board[0][1] = userStep;
            if (checkForWinner()) {
                System.out.println("we have a winner!!!");
            }
            buttons[1].setText(convertBoardCellValue(0, 1));
            userMoveStatus();
        } else if (event.getSource() == buttons[2]) {
            if (!checkForEmptySpaceOnBoard()) {
                System.out.println("Game over, there is no empty space on board!");
            }
            board[0][2] = userStep;
            if (checkForWinner()) {
                System.out.println("we have a winner!!!");
            }
            buttons[2].setText(convertBoardCellValue(0, 2));
            userMoveStatus();
        } else if (event.getSource() == buttons[3]) {
            if (!checkForEmptySpaceOnBoard()) {
                System.out.println("Game over, there is no empty space on board!");
            }
            board[1][0] = userStep;
            if (checkForWinner()) {
                System.out.println("we have a winner!!!");
            }
            buttons[3].setText(convertBoardCellValue(1, 0));
            userMoveStatus();
        } else if (event.getSource() == buttons[4]) {
            if (!checkForEmptySpaceOnBoard()) {
                System.out.println("Game over, there is no empty space on board!");
            }
            board[1][1] = userStep;
            if (checkForWinner()) {
                System.out.println("we have a winner!!!");
            }
            buttons[4].setText(convertBoardCellValue(1, 1));
            userMoveStatus();
        } else if (event.getSource() == buttons[5]) {
            if (!checkForEmptySpaceOnBoard()) {
                System.out.println("Game over, there is no empty space on board!");
            }
            board[1][2] = userStep;
            if (checkForWinner()) {
                System.out.println("we have a winner!!!");
            }
            buttons[5].setText(convertBoardCellValue(1, 2));
            userMoveStatus();
        } else if (event.getSource() == buttons[6]) {
            if (!checkForEmptySpaceOnBoard()) {
                System.out.println("Game over, there is no empty space on board!");
            }
            board[2][0] = userStep;
            if (checkForWinner()) {
                System.out.println("we have a winner!!!");
            }
            buttons[6].setText(convertBoardCellValue(2, 0));
            userMoveStatus();
        } else if (event.getSource() == buttons[7]) {
            if (!checkForEmptySpaceOnBoard()) {
                System.out.println("Game over, there is no empty space on board!");
            }
            board[2][1] = userStep;
            if (checkForWinner()) {
                System.out.println("we have a winner!!!");
            }
            buttons[7].setText(convertBoardCellValue(2, 1));
            userMoveStatus();
        } else if (event.getSource() == buttons[8]) {
            if (!checkForEmptySpaceOnBoard()) {
                System.out.println("Game over, there is no empty space on board!");
            }
            board[2][2] = userStep;
            if (checkForWinner()) {
                System.out.println("we have a winner!!!");
            }
            buttons[8].setText(convertBoardCellValue(2, 2));
            userMoveStatus();
        }
    }
}
