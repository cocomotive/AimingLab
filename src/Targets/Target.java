package Targets;

import Managers.GameManager;
import Managers.TargetManager;
import org.academiadecodigo.simplegraphics.pictures.Picture;


public abstract class Target {

    protected Picture sprite;
    protected TargetManager targetManager;
    protected double x, y;
    protected double radius;
    protected String path;
    public Target(Picture sprite, double x, double y, double radius, String name) {}


    public Target(String path) {
        this.path = path;
    }

    public void spawn(double x, double y) {

        sprite = new Picture(0, 0, path);

        int targetSize = 90;
        int deltaW = targetSize - sprite.getWidth();
        int deltaH = targetSize - sprite.getHeight();

        sprite.grow(deltaW, deltaH);

        double finalX = x - sprite.getWidth() / 2.0;
        double finalY = y - sprite.getHeight() / 2.0 ;
        sprite.translate(finalX, finalY);

        sprite.draw();

        radius = sprite.getWidth() * 0.5;


    }

    public void destroy() {
        sprite.delete();
    }

    public boolean isHit(double mx, double my) {

        double cx = sprite.getX() + sprite.getWidth() / 2.0;
        double cy = sprite.getY() + sprite.getHeight() / 2.0;

        double dx = mx - cx;
        double dy = my - cy;

        return dx * dx + dy * dy <= radius * radius;
    }

    public double getX() {
        return sprite.getX();
    }

    public double getY() {
        return sprite.getY();
    }

    public double getWidth() {
        return sprite.getWidth();
    }

    public double getHeight() {
        return sprite.getHeight();
    }

    public abstract void onHit(GameManager game);
}