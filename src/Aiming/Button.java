package Aiming;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Button {
    private Picture sprite;
    private double x, y;
    private int width, height;
    private Runnable action;

    public Button(double x, double y, String imagePath, Runnable action) {
        this.x = x;
        this.y = y;
        this.action = action;

        sprite = new Picture(x, y, imagePath);
        sprite.draw();

        width = sprite.getWidth();
        height = sprite.getHeight();
    }

    public boolean isClicked(double mx, double my) {
        return mx >= x && mx <= x + width &&
                my >= y && my <= y + height;
    }

    public void click() {
        action.run();
    }

    public void destroy() {
        sprite.delete();
    }
}
