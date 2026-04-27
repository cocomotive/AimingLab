package Managers;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class CrossManager {

    private Picture sprite;

    public CrossManager() {

        this.sprite = new Picture(0, 0, "Resources/CROSSHAIR.png");

        int size = 40;
        int dw = size - sprite.getWidth();
        int dh = size - sprite.getHeight();
        sprite.grow(dw, dh);

        sprite.draw();
    }

    public void updatePosition(double mouseX, double mouseY) {

        double x = mouseX - sprite.getWidth() / 2.0;
        double y = mouseY - sprite.getHeight() / 2.0;

        sprite.translate(x - sprite.getX(), y - sprite.getY());
    }

    public void hide() {
        sprite.delete();
    }
}