package Graphics;

import java.awt.*;
import java.awt.image.BufferedImage;

class Sprite {
    private BufferedImage image;
    private int x;
    private int y;
    private final int width;
    private final int height;

    public Sprite(BufferedImage image, int x, int y, int width, int height) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public BufferedImage getImage() {
        return image.getSubimage(x, y, width, height);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}

