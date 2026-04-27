package Targets;

import Managers.GameManager;

public class FriendlyTarget extends Target {

    public FriendlyTarget() {
        super("Resources/friendlyTarget.png");
    }

    @Override
    public void onHit(GameManager game) {
        game.addScore(10);
    }
}
