package Game.GameEntities;

import java.awt.image.BufferedImage;

public class Tile{
    private boolean passable = false;
    private BufferedImage image;

    public boolean isPassable() {
        return passable;
    }

    public void setPassable(boolean passable) {
        this.passable = passable;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
