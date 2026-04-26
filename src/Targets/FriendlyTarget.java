package Targets;

import Managers.GameManager;

public class FriendlyTarget extends Target {

    public FriendlyTarget() {
        super("AimingLab/Resources/friendlyTarget.png");
    }

    @Override
    public void onHit(GameManager game) {
        game.addTime(2);
    }
}
