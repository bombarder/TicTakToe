import javax.swing.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

class Game {
    private GameField field;
    private GameFieldGui gameFieldGui;

    Game(GameField field, GameFieldGui gui) {
        this.field = field;
        this.gameFieldGui = gui;
    }

    void playWithBot() throws InterruptedException {
        BotController botController = new BotController(field);

        while (GameUtils.checkForEmptySpaceOnBoard(field) & !GameUtils.checkForWinner(field)) {
            if (field.anotherPlayerTurn) {
                TimeUnit.SECONDS.sleep(1);
                botController.generateSmartMove();
                gameFieldGui.gamePanel.repaint();
                field.anotherPlayerTurn = false;
                GameUtils.beforeNextMoveChecking(field, gameFieldGui);
            }
            gameFieldGui.gamePanel.repaint();
            GameUtils.beforeNextMoveChecking(field, gameFieldGui);
        }
    }

    void playWithPlayer(Client client) throws IOException {
        if (field.anotherPlayerTurn) {
            int result = 0;
            try {
                result = (int) client.getFromServer().readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            field.setCellResult(result);
            field.anotherPlayerTurn = false;
//            GameUtils.beforeNextMoveChecking(field, gameFieldGui);
            gameFieldGui.gamePanel.repaint();
        } else {
            JOptionPane.showInputDialog("Not yur turn");
        }
    }

    void playWithServer(GameServer server) throws IOException {
        int result = 0;
        while (true) {
            if (!field.anotherPlayerTurn) {
                try {
                    result = (int) server.getInputFromClient().readObject();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                field.setCellResult(result);
                field.anotherPlayerTurn = true;
                gameFieldGui.gamePanel.repaint();
//            GameUtils.beforeNextMoveChecking(field, gameFieldGui);
                System.out.println("Server moved");
            } else {
                JOptionPane.showInputDialog("Not yur turn");
            }
        }
    }
}
