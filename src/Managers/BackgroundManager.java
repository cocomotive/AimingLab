package Managers;

import Aiming.UIRenderer;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.simplegraphics.graphics.Canvas;

import java.awt.image.BufferedImage;

public class BackgroundManager {

    private Picture background;
    private UIRenderer uiRenderer;
    private BufferedImage compositeImage;

    public BackgroundManager() {
        this.uiRenderer = new UIRenderer();
    }

    public void setBackground(String path) {
        if (background != null) {
            background.delete();
        }
        background = new Picture(0, 0, path);
        background.draw();
    }

    public void updateUI(int score, int time, int bestScore) {
        uiRenderer.update(score, time, bestScore);
        drawUIOverlay();
    }

    private void drawUIOverlay() {
        // Tomar snapshot del canvas actual
        Canvas.getInstance().snapshot();
    }

    public void clearBackground() {
        if (background != null) {
            background.delete();
        }
        uiRenderer.clear();
    }
}