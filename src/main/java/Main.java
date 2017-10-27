import javax.swing.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        String[] options = new String[]{"Single player", "Client", "Server"};
        int response = JOptionPane.showOptionDialog(null,
                "Choose your game option", "Tic Tac Toe",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);
        if (response == 0) {
            new Game().start();
        } else if (response == 1){
            System.out.println("Client has chosen");
        } else if (response == 2){
            System.out.println("Server has chosen");
        }
    }
}
