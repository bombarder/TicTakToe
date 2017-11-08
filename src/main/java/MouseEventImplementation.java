import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class MouseEventImplementation implements MouseListener {

    private GameField gameField;
    private ObjectOutputStream outputStream;

    MouseEventImplementation(GameField field, ObjectOutputStream out) {
        gameField = field;
        outputStream = out;
    }

    MouseEventImplementation(GameField field) {
        gameField = field;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        int move = 0;

        if (x < 100 && y < 100) {
            move = 1;
        } else if (x < 100 && y < 200) {
            move = 2;
        } else if (x < 100 && y < 300) {
            move = 3;
        } else if (x < 200 && y < 100) {
            move = 4;
        } else if (x < 200 && y < 200) {
            move = 5;
        } else if (x < 200 && y < 300) {
            move = 6;
        } else if (x < 300 && y < 100) {
            move = 7;
        } else if (x < 300 && y < 200) {
            move = 8;
        } else if (x < 300 && y < 300) {
            move = 9;
        }
        gameField.setCellResult(move);
        try {
            outputStream.writeObject(move);
            outputStream.flush();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
