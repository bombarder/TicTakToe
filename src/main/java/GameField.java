import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class GameField {

    private int[][] board = new int[3][3];

    private boolean flag;
    int userStep = BoardElement.ONE.getValue();
    boolean isBotTurn = false;
    static JFrame frame;
    private BufferedImage field;
    private BufferedImage imageOfX;
    private BufferedImage imageOfO;
    final JPanel gamePanel;

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
        gamePanel = new JPanel() {
            public void paint(Graphics g) {
                super.paintComponent(g);
                g.drawImage(field, 0, 0, null);
                for (int i = 0; i < board.length; i++) {
                    for (int j = 0; j < board[i].length; j++) {
                        if (board[i][j] == BoardElement.ONE.getValue()) {
                            g.drawImage(imageOfX, 100 * i + 3, 100 * j + 3, null);
                        } else if (board[i][j] == BoardElement.TWO.getValue()) {
                            g.drawImage(imageOfO, 100 * i + 3, 100 * j + 3, null);
                        }
                    }
                }
            }
        };
        gamePanel.setBackground(Color.WHITE);
        gamePanel.setBounds(0, 0, 300, 300);
        gamePanel.addMouseListener(new MouseEventImplementation(this));
        frame.add(gamePanel);
        frame.setLocation(800, 300);
        frame.setVisible(true);
    }

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
