package graphicals;

import java.awt.image.BufferedImage;

class SpriteSheet {
    private BufferedImage image;
    private int spriteWidth;
    private int spriteHeight;

    public SpriteSheet() {}

    public SpriteSheet(BufferedImage image, int spriteWidth, int spriteHeight) {
        this.image = image;
        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;
    }

    public BufferedImage getSprite(int col, int row) {
        int x = col * spriteWidth;
        int y = row * spriteHeight;
        return image.getSubimage(x, y, spriteWidth, spriteHeight);
    }

    public BufferedImage getSprite(int index) {
        int cols = image.getWidth() / spriteWidth;
        int row = index / cols;
        int col = index % cols;
        return getSprite(col, row);
    }
}
