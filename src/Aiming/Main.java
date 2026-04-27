package Aiming;

import Managers.GameManager;

public class Main {

    public static void main(String[] args) {

        GameManager game = new GameManager();

        new Input(game);
        KeyboardInput keyboard = new KeyboardInput(game);
        keyboard.initKeys();
    }
}