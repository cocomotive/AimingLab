package Managers;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class CrossManager {

    private Picture sprite;
    private boolean isVisible = false;

    public CrossManager() {
        createSprite();
    }

    private void createSprite() {
        this.sprite = new Picture(0, 0, "Resources/CROSSHAIR.png");

        int size = 40;
        int dw = size - sprite.getWidth();
        int dh = size - sprite.getHeight();
        sprite.grow(dw, dh);

        sprite.draw();
        isVisible = true;
    }

    public void updatePosition(double mouseX, double mouseY) {
        // Se a crosshair foi deletada, recriá-la
        if (!isVisible) {
            createSprite();
        }

        double x = mouseX - sprite.getWidth() / 2.0;
        double y = mouseY - sprite.getHeight() / 2.0;

        sprite.translate(x - sprite.getX(), y - sprite.getY());
    }

    public void show() {
        if (!isVisible) {
            createSprite();
        }
    }

    public void hide() {
        if (isVisible && sprite != null) {
            sprite.delete();
            isVisible = false;
        }
    }

    public void destroy() {
        hide();
        sprite = null;
    }
}