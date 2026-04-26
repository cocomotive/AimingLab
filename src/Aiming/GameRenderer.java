package Aiming;

import org.academiadecodigo.simplegraphics.graphics.Canvas;
import java.awt.*;

public class GameRenderer {

    private Canvas canvas;
    private Graphics2D g2d;
    private UIRenderer uiRenderer;

    public GameRenderer(UIRenderer uiRenderer) {
        this.canvas = Canvas.getInstance();
        this.uiRenderer = uiRenderer;
    }

    public void renderUI(int score, int time, int bestScore) {
        // refresh render
        uiRenderer.update(score, time, bestScore);

        // draw using custom case is necesary
        drawCustomUI(score, time, bestScore);
    }

    private void drawCustomUI(int score, int time, int bestScore) {
        // draw on canva
        Canvas.getInstance().snapshot();
    }

    public void clear() {
        uiRenderer.clear();
    }
}