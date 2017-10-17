import java.util.Arrays;
import java.util.Random;

class BotController {

    private GameField field;
    private boolean moveUserState;
    private final static Random random = new Random();

    private final static int[][] winLines = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9},
            {1, 4, 7},
            {2, 5, 8},
            {3, 6, 9},
            {1, 5, 9},
            {3, 5, 7}
    };

    BotController(GameField field) {
        this.field = field;
    }

    static int[][] getWinLines() {
        return winLines;
    }

    private void generateMove() {
        int cell;
        do {
            cell = random.nextInt(9) + 1;
        } while (field.getCellResult(cell) != 0);
        field.setCellResult(cell);
    }

    void generateSmartMove() {
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
            currentStateBoard[i] = field.getCellResult(winLine[i]);
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
                    field.setCellResult(winLine[j]);
                    moveUserState = true;
                }
            }
        }
    }
}
