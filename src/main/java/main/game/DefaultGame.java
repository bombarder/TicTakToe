package main.game;

import main.BotController;
import main.GameField;
import main.GameFieldGui;
import main.GameUtils;
import main.MouseEventImplementation;

public class DefaultGame extends AbstractGame {

    public DefaultGame() {
        this.field = new GameField();
        this.gameFieldGui = new GameFieldGui(field, new MouseEventImplementation(field));
    }

    public void play() {
        BotController botController = new BotController(field);
        while (GameUtils.checkForEmptySpaceOnBoard(field) && !GameUtils.checkForWinner(field)) {
            if (field.anotherPlayerTurn) {
                botController.generateSmartMove();
                gameFieldGui.gamePanel.repaint();
                field.anotherPlayerTurn = false;
                GameUtils.beforeNextMoveChecking(field, gameFieldGui);
            }
            gameFieldGui.gamePanel.repaint();
            GameUtils.beforeNextMoveChecking(field, gameFieldGui);
        }
    }
}
