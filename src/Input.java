import org.academiadecodigo.simplegraphics.mouse.*;

public class Input implements MouseHandler {

    private GameManager game;

    public Input(GameManager game) {
        this.game = game;

        Mouse mouse = new Mouse(this);
        mouse.addEventListener(MouseEventType.MOUSE_CLICKED);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        game.handleClick(e.getX(), e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {}
}
