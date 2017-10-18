import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class MouseEventImplementation implements MouseListener {

    private GameField gameField;

    MouseEventImplementation(GameField field) {
        gameField = field;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if (x < 100 && y < 100) {
            gameField.setCellResult(1);
        } else if (x < 100 && y < 200) {
            gameField.setCellResult(2);
        } else if (x < 100 && y < 300) {
            gameField.setCellResult(3);
        } else if (x < 200 && y < 100) {
            gameField.setCellResult(4);
        } else if (x < 200 && y < 200) {
            gameField.setCellResult(5);
        } else if (x < 200 && y < 300) {
            gameField.setCellResult(6);
        } else if (x < 300 && y < 100) {
            gameField.setCellResult(7);
        } else if (x < 300 && y < 200) {
            gameField.setCellResult(8);
        } else if (x < 300 && y < 300) {
            gameField.setCellResult(9);
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
