package Aiming;

import Managers.GameManager;
import org.academiadecodigo.simplegraphics.keyboard.*;

public class KeyboardInput implements KeyboardHandler {
    private Keyboard keyboard;
    GameManager gameManager = new GameManager();

    public KeyboardInput(GameManager game) {
        this.keyboard = new Keyboard(this);
    }

    public void initKeys(){
        KeyboardEvent quit = new KeyboardEvent();
        quit.setKey(KeyboardEvent.KEY_Q);
        quit.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(quit);

        KeyboardEvent menu = new KeyboardEvent();
        menu.setKey(KeyboardEvent.KEY_SPACE);
        menu.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(menu);
    }
    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        if (keyboardEvent.getKey() == KeyboardEvent.KEY_Q) {
            System.exit(0);
        }
        if (keyboardEvent.getKey() == KeyboardEvent.KEY_SPACE) {
            gameManager.backToMenu();

        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
}
