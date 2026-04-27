package Targets;

import Aiming.GameState;
import Managers.GameManager;

public class EnemyTarget extends Target {

    public EnemyTarget() {
        super("Resources/enemyTarget.png");
    }

    @Override
    public void onHit(GameManager game) {

        game.addScore(1);

        if (game.getGameState() == GameState.SURVIVAL) {
            game.addTime(-3);

    }
}
}