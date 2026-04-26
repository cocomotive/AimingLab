public class FriendlyTarget extends Target {

    public FriendlyTarget() {
        super("images/friendly.png");
    }

    @Override
    public void onHit(GameManager game) {
        game.addTime(2);
    }
}
