import org.academiadecodigo.simplegraphics.graphics.Canvas;
import java.awt.*;
import java.awt.image.BufferedImage;

public class UIRenderer {

    private Canvas canvas;
    private BufferedImage uiLayer;
    private Graphics2D g2d;
    private int score = 0;
    private int time = 60;
    private int bestScore = 0;

    public UIRenderer() {
        this.canvas = Canvas.getInstance();
        this.uiLayer = new BufferedImage(800, 100, BufferedImage.TYPE_INT_ARGB);
        this.g2d = (Graphics2D) uiLayer.getGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    public void update(int score, int time, int bestScore) {
        this.score = score;
        this.time = time;
        this.bestScore = bestScore;
        render();
    }

    private void render() {
        // clean
        g2d.clearRect(0, 0, 800, 100);

        // config font and color
        g2d.setFont(new Font("Arial", Font.BOLD, 32));
        g2d.setColor(Color.WHITE);

        // draw and "shadow"
        g2d.setColor(new Color(0, 0, 0, 150));
        g2d.fillRect(0, 0, 800, 100);

        // draw text
        g2d.setColor(Color.WHITE);
        g2d.drawString("Score: " + score, 20, 50);
        g2d.drawString("Time: " + time, 300, 50);
        g2d.drawString("Best: " + bestScore, 600, 50);

        // display/draw on canvas
        drawOnCanvas();
    }

    private void drawOnCanvas() {
        Canvas.getInstance().snapshot();
    }

    public void clear() {
        g2d.clearRect(0, 0, 800, 100);
    }

    public BufferedImage getUILayer() {
        return uiLayer;
    }
}