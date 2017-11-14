package main.game;

import java.io.IOException;

import main.Client;
import main.GameField;
import main.GameFieldGui;
import main.GameUtils;
import main.MouseEventImplementation;

public class ClientGame extends AbstractGame {

    private Client client;

    public ClientGame(Client client) {
        this.field = new GameField();
        this.gameFieldGui = new GameFieldGui(field, new MouseEventImplementation(field, client.getOutputToServer()));
        this.client = client;
    }

    public void play() {
        int result = 0;
        while (GameUtils.checkForEmptySpaceOnBoard(field) && !GameUtils.checkForWinner(field)) {
            gameFieldGui.gamePanel.repaint();
            try {
                result = (int) client.getFromServer().readObject();
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
            field.setCellResult(result);
//            main.GameUtils.beforeNextMoveChecking(field, gameFieldGui);
            gameFieldGui.gamePanel.repaint();
            System.out.println("Client moved");
        }
    }
}
