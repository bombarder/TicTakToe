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
        int result = 0;
        while (GameUtils.checkForEmptySpaceOnBoard(field) & !GameUtils.checkForWinner(field)) {
            try {
                result = (int)client.getIn().readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            field.setCellResult(result);
            gameFieldGui.gamePanel.repaint();
            GameUtils.beforeNextMoveChecking(field, gameFieldGui);
            System.out.println("Client moved");
        }
    }

    void playWithServer(GameServer server) throws IOException {
        int result = 0;
        while (GameUtils.checkForEmptySpaceOnBoard(field) & !GameUtils.checkForWinner(field)) {
            try {
                result = (int) server.getOis().readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            field.setCellResult(result);
            server.getOos().writeObject(result);
            gameFieldGui.gamePanel.repaint();
            GameUtils.beforeNextMoveChecking(field, gameFieldGui);
            System.out.println("Server moved");
        }
    }
}
