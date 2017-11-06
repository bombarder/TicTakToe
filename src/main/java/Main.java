import javax.swing.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        String[] options = new String[]{"Single player", "client", "server"};
        int response = JOptionPane.showOptionDialog(null,
                "Choose your game option, please", "Tic Tac Toe",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);
        if (response == 0) {
            new Game().start();
        } else if (response == 1) {
//            String ipAddress = JOptionPane.showInputDialog("Please, input your ip address:");
//            new Client().start();
        } else if (response == 2) {
//            new GameServer().start();
//            System.out.println("server has chosen");
        }
    }
}
