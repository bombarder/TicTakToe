import javax.swing.*;

import java.util.Arrays;

import static javax.swing.JOptionPane.CANCEL_OPTION;
import static javax.swing.JOptionPane.NO_OPTION;
import static javax.swing.JOptionPane.YES_OPTION;

class GameUtils {

    private static void playAgain(GameField field, String message) {
        int test = JOptionPane.showConfirmDialog(GameField.frame, message + "Play again?");
        switch (test) {
            case YES_OPTION:
                field.userStep = 0;
                break;
            case NO_OPTION:
                System.exit(0);
            case CANCEL_OPTION:
                System.exit(0);
        }
    }

    static void beforeNextMoveChecking(GameField field) {
        if (checkForWinner(field)) {
            field.gamePanel.repaint();
            playAgain(field, "we have a winner!!!");
            field.setBoard(new int[3][3]);
            field.gamePanel.repaint();
        }
        if (!checkForEmptySpaceOnBoard(field)) {
            field.gamePanel.repaint();
            playAgain(field, "Game over, there is no empty space on board! ");
            field.setBoard(new int[3][3]);
            field.gamePanel.repaint();
        }
    }

    static boolean checkForWinner(GameField field) {
        int[] currentStateBoard = new int[3];
        for (int i = 0; i < BotController.getWinLines().length; i++) {
            for (int j = 0; j < BotController.getWinLines()[i].length; j++) {
                currentStateBoard[i] = field.getBoard()[i][j];
            }
        }
        int counter = 0;
        for (int aCurrentStateBoard : currentStateBoard) {
            if (aCurrentStateBoard == 1) {
                counter++;
            }
        }
        return counter == 3;
    }

    static boolean checkForEmptySpaceOnBoard(GameField field) {
        for (int[] aBoard : field.getBoard()) {
            for (int anABoard : aBoard) {
                if (anABoard == 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
