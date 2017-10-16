import java.util.concurrent.TimeUnit;

class Game {
    void start() throws InterruptedException {
        GameField field = new GameField();
        BotController botController = new BotController(field);

        while (GameUtils.checkForEmptySpaceOnBoard(field) & !GameUtils.checkForWinner(field)) {
            if (field.isBotTurn) {
                TimeUnit.SECONDS.sleep(1);
                botController.generateSmartMove();
                field.gamePanel.repaint();
                field.isBotTurn = false;
                GameUtils.beforeNextMoveChecking(field);
            }
            GameUtils.beforeNextMoveChecking(field);
        }
    }
}
