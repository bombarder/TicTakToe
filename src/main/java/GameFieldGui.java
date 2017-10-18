import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class GameFieldGui {
    private BufferedImage fieldImage;
    private BufferedImage imageOfX;
    private BufferedImage imageOfO;
    final JPanel gamePanel;
    static JFrame frame;

    GameFieldGui(final GameField gameField) {
        frame = new JFrame("Tic Tac Toe");
        frame.setSize(319, 348);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        try {
            fieldImage = ImageIO.read(new File("src/sourceFolder/field.png"));
            imageOfX = ImageIO.read(new File("src/sourceFolder/X.png"));
            imageOfO = ImageIO.read(new File("src/sourceFolder/O.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        gamePanel = new JPanel() {
            public void paint(Graphics g) {
                super.paintComponent(g);
                g.drawImage(fieldImage, 0, 0, null);
                for (int i = 0; i < gameField.getBoard().length; i++) {
                    for (int j = 0; j < gameField.getBoard()[i].length; j++) {
                        if (gameField.getBoard()[i][j] == BoardElement.ONE.getValue()) {
                            g.drawImage(imageOfX, 100 * i + 3, 100 * j + 3, null);
                        } else if (gameField.getBoard()[i][j] == BoardElement.TWO.getValue()) {
                            g.drawImage(imageOfO, 100 * i + 3, 100 * j + 3, null);
                        }
                    }
                }
            }
        };
        gamePanel.setBackground(Color.WHITE);
        gamePanel.setBounds(0, 0, 300, 300);
        gamePanel.addMouseListener(new MouseEventImplementation(gameField));
        frame.add(gamePanel);
        frame.setLocation(800, 300);
        frame.setVisible(true);
    }
}
