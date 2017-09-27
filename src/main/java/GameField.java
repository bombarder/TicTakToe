
class GameField {

    private int userStep = 1;
    private int[][] board = new int[3][3];
    private boolean flag;

    void generateBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 2) {
                    System.out.print(" x ");
                } else if (board[i][j] == 1) {
                    System.out.print(" o ");
                } else {
                    System.out.print("   ");
                }
                if (j != board.length - 1) {
                    System.out.print("|");
                }
            }
            if (i != board.length - 1) {
                System.out.println();
                System.out.print("-----------");
                System.out.println();
            } else {
                System.out.println();
            }
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

    void generateMove() {
        int randomX = (int) (Math.random() * 3);
        int randomO = (int) (Math.random() * 3);
        if (board[randomX][randomO] == 0) {
            userMoveStatus();
            board[randomX][randomO] = userStep;
        } else {
            generateMove();
        }
    }

    boolean checkForEmptySpaceOnBoard() {
        for (int[] aBoard : board) {
            for (int anABoard : aBoard) {
                if (anABoard == 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
