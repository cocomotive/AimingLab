public class FriendlyTarget extends Target {

    public FriendlyTarget() {
        super("/Users/andreaparra/workspace/Aiming/Aiming/Resources/Target.jpeg");
    }

    @Override
    public void onHit(GameManager game) {
        game.addTime(2);
    }
}
