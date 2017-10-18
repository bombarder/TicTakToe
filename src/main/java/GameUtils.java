import javax.swing.*;
import static javax.swing.JOptionPane.CANCEL_OPTION;
import static javax.swing.JOptionPane.NO_OPTION;
import static javax.swing.JOptionPane.YES_OPTION;

class GameUtils {

    private static void playAgain(GameField field, String message) {
        int test = JOptionPane.showConfirmDialog(GameFieldGui.frame, message + "Play again?");
        switch (test) {
            case YES_OPTION:
                field.userStep = BoardElement.ZERO.getValue();
                break;
            case NO_OPTION:
                System.exit(0);
            case CANCEL_OPTION:
                System.exit(0);
        }
    }

    static void beforeNextMoveChecking(GameField field, GameFieldGui gameFieldGui) {
        if (checkForWinner(field)) {
            gameFieldGui.gamePanel.repaint();
            playAgain(field, "we have a winner!!!");
            field.setBoard(new int[3][3]);
            gameFieldGui.gamePanel.repaint();
        }
        if (!checkForEmptySpaceOnBoard(field)) {
            gameFieldGui.gamePanel.repaint();
            playAgain(field, "Game over, there is no empty space on board! ");
            field.setBoard(new int[3][3]);
            gameFieldGui.gamePanel.repaint();
        }
    }

    static boolean checkForWinner(GameField field) {
        int[] currentStateBoard = new int[3];
        for (int i = 0; i < BotController.getWinLines().length; i++) {
            int counter = 0;
            for (int j = 0; j < BotController.getWinLines()[i].length; j++) {
                currentStateBoard[j] = field.getCellResult(BotController.getWinLines()[i][j]);
            }
            int firstDigitInLine = currentStateBoard[0];
            for (int aCurrentStateBoard : currentStateBoard) {
                if (aCurrentStateBoard == firstDigitInLine) {
                    counter++;
                }
            }
            if (counter == 3 && firstDigitInLine != 0) {
                return true;
            }
        }
        return false;
    }

    static boolean checkForEmptySpaceOnBoard(GameField field) {
        for (int[] aBoard : field.getBoard()) {
            for (int anABoard : aBoard) {
                if (anABoard == BoardElement.ZERO.getValue()) {
                    return true;
                }
            }
        }
        return false;
    }
}
