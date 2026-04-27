package Aiming;

import Managers.GameManager;
import org.academiadecodigo.simplegraphics.graphics.Canvas;

public class Main {

    public static void main(String[] args) {

        GameManager game = new GameManager();

        new Input(game);
        KeyboardInput keyboard = new KeyboardInput(game);
        keyboard.initKeys();
        Canvas canvas = Canvas.getInstance();
        //canvas.toggleFullscreen();

    }
}