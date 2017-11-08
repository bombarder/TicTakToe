import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {

        String[] options = new String[]{"Single player", "client", "server"};
        GameField field = new GameField();
        GameFieldGui gameFieldGui;
        Game game;

        int response = JOptionPane.showOptionDialog(null,
                "Choose your game option, please", "Tic Tac Toe",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);

        if (response == 0) {
            gameFieldGui = new GameFieldGui(field);
            game = new Game(field, gameFieldGui);
            game.playWithBot();
        } else if (response == 1) {
            Client client = new Client();
            gameFieldGui = new GameFieldGui(field, new MouseEventImplementation(field, client.getOut()));
            game = new Game(field, gameFieldGui);
//            String ipAddress = JOptionPane.showInputDialog("Please, input your ip address:");
            game.playWithPlayer(client);
        } else if (response == 2) {
            GameServer server = new GameServer();
            gameFieldGui = new GameFieldGui(field, new MouseEventImplementation(field));
            game = new Game(field, gameFieldGui);
            server.acceptClients();
            game.playWithServer(server);
        }
    }
}
