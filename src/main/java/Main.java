import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Scanner scanner = new Scanner(System.in);
        GameField field = new GameField();
        System.out.println("First player is always X, insert coordinates...");

        while (!field.checkForWinner()) {
            int row = scanner.nextInt();
            int column = scanner.nextInt();
            if (row > 2 | column > 2) {
                System.out.println("Wrong cell location, try one more time...");
                continue;
            }
            field.write(row, column);
            field.generateBoard();
            if (field.checkForWinner()){
                break;
            }
            System.out.println("Another user turn...");
            TimeUnit.SECONDS.sleep(1);
            field.generateMove();
            field.generateBoard();
            field.userMoveStatus();
            System.out.println("Another user turn...");
        }
    }
}
