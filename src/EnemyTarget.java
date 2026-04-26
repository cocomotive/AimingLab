public class EnemyTarget extends Target {

    public EnemyTarget() {
        super("/Users/andreaparra/workspace/Aiming/Aiming/Resources/Target.jpeg");
    }

    @Override
    public void onHit(GameManager game) {
        game.addScore(1);
    }
}