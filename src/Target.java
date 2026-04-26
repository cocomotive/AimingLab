import org.academiadecodigo.simplegraphics.pictures.Picture;


public abstract class Target {

    protected Picture sprite;
    protected double x, y;
    protected double radius;

    public Target(String path) {
        sprite = new Picture(0, 0, path);
    }

    public void spawn(double x, double y) {
        this.x = x;
        this.y = y;

        sprite.translate(x, y);
        sprite.draw();

        radius = sprite.getWidth() * 0.5;
    }

    public void destroy() {
        sprite.delete();
    }

    public boolean isHit(double mx, double my) {
        double cx = x + sprite.getWidth() / 2.0;
        double cy = y + sprite.getHeight() / 2.0;

        double dx = mx - cx;
        double dy = my - cy;

        return dx * dx + dy * dy <= radius * radius;
    }

    public abstract void onHit(GameManager game);
}