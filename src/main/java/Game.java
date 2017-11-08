import java.io.IOException;
import java.util.concurrent.TimeUnit;

class Game {
    private GameField field;
    private GameFieldGui gameFieldGui;

    public GameField getField() {
        return field;
    }

    public void setField(GameField field) {
        this.field = field;
    }

    Game(GameField field, GameFieldGui gui) {
        this.field = field;
        this.gameFieldGui = gui;
    }

    void playWithBot() throws InterruptedException {
//        field = new GameField();
//        gameFieldGui = new GameFieldGui(field);
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
//        field = new GameField();
//        Client client = new Client();
//        gameFieldGui = new GameFieldGui(field, new MouseEventImplementation(field, client.getOut()));

        while (GameUtils.checkForEmptySpaceOnBoard(field) & !GameUtils.checkForWinner(field)) {
            if (field.anotherPlayerTurn) {
                gameFieldGui.gamePanel.repaint();
                field.anotherPlayerTurn = false;
                GameUtils.beforeNextMoveChecking(field, gameFieldGui);
            }
            gameFieldGui.gamePanel.repaint();
            field.anotherPlayerTurn = true;
            GameUtils.beforeNextMoveChecking(field, gameFieldGui);
        }
    }

    void playWithServer(GameServer server) throws IOException {
//        gameFieldGui = new GameFieldGui(field, new MouseEventImplementation(field, client.getOut()));

        while (GameUtils.checkForEmptySpaceOnBoard(field) & !GameUtils.checkForWinner(field)) {
            if (field.anotherPlayerTurn) {
                gameFieldGui.gamePanel.repaint();
                field.anotherPlayerTurn = false;
                GameUtils.beforeNextMoveChecking(field, gameFieldGui);
            }
            gameFieldGui.gamePanel.repaint();
            field.anotherPlayerTurn = true;
            GameUtils.beforeNextMoveChecking(field, gameFieldGui);
        }
    }
}
