package graphicals;

import org.jetbrains.annotations.NotNull;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.util.Map;

class Renderer {
    private Graphics2D graphicsContext;
    private Map<String, Sprite> spriteMap; // Optional: used if drawing by name

    public Renderer(Graphics2D graphicsContext, Map<String, Sprite> spriteMap) {
        this.graphicsContext = graphicsContext;
        this.spriteMap = spriteMap;
    }

    public void drawSprite(@NotNull Sprite sprite, int x, int y) {
        BufferedImage image = sprite.getImage();
        graphicsContext.drawImage(image, x, y, null);
    }

    public void drawSprite(String name, int x, int y) {
        Sprite sprite = spriteMap.get(name);
        if (sprite != null) {
            drawSprite(sprite, x, y);
        }
    }

    public void drawRectangle(int x, int y, int width, int height) {
        graphicsContext.setColor(Color.RED); // You can customize this
        graphicsContext.drawRect(x, y, width, height);
    }

    public void setGraphicsContext(Graphics2D graphicsContext) {
        this.graphicsContext = graphicsContext;
    }

    public void setSpriteMap(Map<String, Sprite> spriteMap) {
        this.spriteMap = spriteMap;
    }
}
