public class DevilTarget extends Target {

    public DevilTarget() {
        super("/Users/andreaparra/workspace/Aiming/Aiming/Resources/Target.jpeg");
    }

    @Override
    public void onHit(GameManager game) {
        game.addTime(-3);
    }
}