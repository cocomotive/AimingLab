package Aiming;

import org.academiadecodigo.simplegraphics.pictures.Picture;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class UIRenderer {

    private Picture scoreImage;
    private Picture timeImage;
    private Picture bestScoreImage;
    private boolean isVisible = false;

    public UIRenderer() {
        // inicialize empty images
        scoreImage = createTextImage("Score: 0", 0, 0);
        timeImage = createTextImage("Time: 60", 0, 40);
        bestScoreImage = createTextImage("Best: 0", 600, 0);
    }

    private Picture createTextImage(String text, int x, int y) {
        BufferedImage img = new BufferedImage(300, 50, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = (Graphics2D) img.getGraphics();

        //g2d.setColor(Color.BLACK);
        //g2d.fillRect(0, 0, 300, 50);

        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        g2d.drawString(text, 10, 35);

        g2d.dispose();

        // save temp image
        try {
            File temp = File.createTempFile("ui_text", ".png");
            ImageIO.write(img, "png", temp);
            return new Picture(x, y, temp.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void show() {
        if (!isVisible) {
            if (scoreImage != null) scoreImage.draw();
            if (timeImage != null) timeImage.draw();
            if (bestScoreImage != null) bestScoreImage.draw();
            isVisible = true;
        }
    }

    public void update(int score, int time, int bestScore) {
        clear();

        scoreImage = createTextImage("Score: " + score, 0, 0);
        timeImage = createTextImage("Time: " + time, 0, 40);
        bestScoreImage = createTextImage("Best: " + bestScore, 600, 0);

        show();
    }

    public void clear() {
        if (isVisible) {
            if (scoreImage != null) scoreImage.delete();
            if (timeImage != null) timeImage.delete();
            if (bestScoreImage != null) bestScoreImage.delete();
            isVisible = false;
        }
    }
}