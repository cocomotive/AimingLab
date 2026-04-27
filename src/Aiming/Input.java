package Aiming;

import Managers.GameManager;
import org.academiadecodigo.simplegraphics.mouse.*;
import org.academiadecodigo.simplegraphics.keyboard.*;

public class Input implements MouseHandler {

    private GameManager game;

    public Input(GameManager game) {
        this.game = game;

        Mouse mouse = new Mouse(this);
        mouse.addEventListener(MouseEventType.MOUSE_CLICKED);
        mouse.addEventListener(MouseEventType.MOUSE_MOVED);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        game.handleClick(e.getX(), e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        game.updateMouse(e.getX(), e.getY());
    }
}


