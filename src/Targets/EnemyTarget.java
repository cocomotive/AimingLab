package Targets;

import Managers.GameManager;

public class EnemyTarget extends Target {

    public EnemyTarget() {
        super("Resources/enemyTarget.png");
    }

    @Override
    public void onHit(GameManager game) {
        game.addScore(1);
    }
}