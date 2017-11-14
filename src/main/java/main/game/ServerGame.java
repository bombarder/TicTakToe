package main.game;

import java.io.IOException;

import main.GameField;
import main.GameFieldGui;
import main.Server;
import main.GameUtils;
import main.MouseEventImplementation;

public class ServerGame extends AbstractGame {

    private final Server server;

    public ServerGame(Server server) {
        this.field = new GameField();
        this.gameFieldGui = new GameFieldGui(field, new MouseEventImplementation(field, server.getOutputToClient()));
        this.server = server;
    }

    public void play() {
        int result = 0;
        while (GameUtils.checkForEmptySpaceOnBoard(field) && !GameUtils.checkForWinner(field)) {
            gameFieldGui.gamePanel.repaint();
            try {
                result = (int) server.getInputFromClient().readObject();
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
            field.setCellResult(result);
//            main.GameUtils.beforeNextMoveChecking(field, gameFieldGui);
            gameFieldGui.gamePanel.repaint();
            System.out.println("Server moved");
        }
    }
}
