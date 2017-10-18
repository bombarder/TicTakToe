class GameField {

    private int[][] board = new int[3][3];
    private boolean flag;
    int userStep = BoardElement.ONE.getValue();
    boolean isBotTurn = false;

    void setBoard(int[][] board) {
        this.board = board;
    }

    int[][] getBoard() {
        return board;
    }

    private void changeUserTurn() {
        if (flag) {
            userStep = BoardElement.ONE.getValue();
            flag = false;
        } else {
            userStep = BoardElement.TWO.getValue();
            flag = true;
        }
    }

    int getCellResult(int value) {
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

    void setCellResult(int value) {
        if (value == 1) {
            if (board[0][0] != 0) {
                System.out.println("Cell is busy, choose another one!");
            } else {
                board[0][0] = userStep;
                changeUserTurn();
                isBotTurn = true;
            }
        } else if (value == 2) {
            if (board[0][1] != 0) {
                System.out.println("Cell is busy, choose another one!");
            } else {
                board[0][1] = userStep;
                changeUserTurn();
                isBotTurn = true;
            }
        } else if (value == 3) {
            if (board[0][2] != 0) {
                System.out.println("Cell is busy, choose another one!");
            } else {
                board[0][2] = userStep;
                changeUserTurn();
                isBotTurn = true;
            }
        } else if (value == 4) {
            if (board[1][0] != 0) {
                System.out.println("Cell is busy, choose another one!");
            } else {
                board[1][0] = userStep;
                changeUserTurn();
                isBotTurn = true;
            }
        } else if (value == 5) {
            if (board[1][1] != 0) {
                System.out.println("Cell is busy, choose another one!");
            } else {
                board[1][1] = userStep;
                changeUserTurn();
                isBotTurn = true;
            }
        } else if (value == 6) {
            if (board[1][2] != 0) {
                System.out.println("Cell is busy, choose another one!");
            } else {
                board[1][2] = userStep;
                changeUserTurn();
                isBotTurn = true;
            }
        } else if (value == 7) {
            if (board[2][0] != 0) {
                System.out.println("Cell is busy, choose another one!");
            } else {
                board[2][0] = userStep;
                changeUserTurn();
                isBotTurn = true;
            }
        } else if (value == 8) {
            if (board[2][1] != 0) {
                System.out.println("Cell is busy, choose another one!");
            } else {
                board[2][1] = userStep;
                changeUserTurn();
                isBotTurn = true;
            }
        } else if (value == 9) {
            if (board[2][2] != 0) {
                System.out.println("Cell is busy, choose another one!");
            } else {
                board[2][2] = userStep;
                changeUserTurn();
                isBotTurn = true;
            }
        }
    }
}
