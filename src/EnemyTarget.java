public class EnemyTarget extends Target {

    public EnemyTarget() {
        super("images/enemy.png");
    }

    @Override
    public void onHit(GameManager game) {
        game.addScore(1);
    }
}