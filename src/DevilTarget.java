public class DevilTarget extends Target {

    public DevilTarget() {
        super("images/devil.png");
    }

    @Override
    public void onHit(GameManager game) {
        game.addTime(-3);
    }
}