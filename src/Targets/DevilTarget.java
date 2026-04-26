package Targets;

import Managers.GameManager;

public class DevilTarget extends Target {

    public DevilTarget() {
        super("Resources/devilTarget.png");
    }

    @Override
    public void onHit(GameManager game) {
        game.addScore(-3);
    }
}