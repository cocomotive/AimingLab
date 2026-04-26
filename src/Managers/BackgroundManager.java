package Managers;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class BackgroundManager {

    private Picture current;

    public void setBackground(String path) {
        if (current != null) {
            current.delete();
        }
        current = new Picture(0, 0, path);
        current.draw();
    }
}