package Graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.imageio.ImageIO;

class SpriteLoader {
    private final Map<String, SpriteSheet> spriteSheets;

    public SpriteLoader() {
        this.spriteSheets = new HashMap<>();
    }

    public void loadSpriteSheet(String name, String path, int spriteWidth, int spriteHeight) {
        try {
            BufferedImage sheetImage = ImageIO.read(Objects.requireNonNull(getClass().getResource(path)));
            SpriteSheet sheet = new SpriteSheet(sheetImage, spriteWidth, spriteHeight);
            spriteSheets.put(name, sheet);
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public SpriteSheet getSpriteSheet(String name) {
        return spriteSheets.get(name);
    }

    public void clear() {
        spriteSheets.clear();
    }
}
