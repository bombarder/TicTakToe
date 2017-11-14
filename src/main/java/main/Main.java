package main;

import javax.swing.*;

import main.game.ClientGame;
import main.game.DefaultGame;
import main.game.Game;
import main.game.ServerGame;

import static javax.swing.JOptionPane.DEFAULT_OPTION;
import static javax.swing.JOptionPane.PLAIN_MESSAGE;

public class Main {

    private static final String MESSAGE = "Choose your main.game option, please";
    private static final String DIALOG_NAME = "Tic Tac Toe";
    private static final String[] NAME_OF_BUTTONS = new String[]{"Single player", "client", "server"};

    public static void main(String[] args) throws Exception {
        int response = JOptionPane.showOptionDialog(null, MESSAGE, DIALOG_NAME, DEFAULT_OPTION, PLAIN_MESSAGE, null, NAME_OF_BUTTONS, NAME_OF_BUTTONS[0]);
        getGame(response).play();
    }

    private static Game getGame(int response) throws Exception {
        switch (response) {
            case 1:
                return new ClientGame(new Client());
            case 2:
                return new ServerGame(new Server());
            default:
                return new DefaultGame();
        }
    }
}
