package Targets;

import Managers.GameManager;
import Aiming.GameState;

public class DevilTarget extends Target {

    public DevilTarget() {
        super("Resources/devilTarget.png");
    }

    @Override
    public void onHit(GameManager game) {
        if (game.getGameState() == GameState.SURVIVAL) {
            game.addTime(-20);
        }
    }
}