
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("War of bots started...");
        GameField field = new GameField();

        while (!field.checkForWinner()) {
            if (userMove(field)) break;
            if (userMove(field)) break;
        }
    }

    private static boolean userMove(GameField field) throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        field.generateMove();
        field.generateBoard();
        if (field.checkForWinner()){
            return true;
        }
        if (!field.checkForEmptySpaceOnBoard()){
            System.out.println("There is no empty space on board...");
            return true;
        }
        System.out.println("Another user turn...");
        return false;
    }
}
