import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        GameField field = new GameField();
        System.out.println("First player is always X, insert coordinates...");
        while (true){
            int row = scanner.nextInt();
            int column = scanner.nextInt();
            field.write(row, column);
            field.generateBoard();
            field.userMoveStatus();
            if (field.checkForWinner()){
                break;
            }
        }
    }
}
