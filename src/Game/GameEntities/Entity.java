package Game.GameEntities;

import Audio.SoundEffect;
import Launcher.GamePanel;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.EnumMap;

public abstract class Entity {
    private int worldX, worldY;
    private String name;
    private boolean passable = false;
    private Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    private int solidAreaDefaultX, solidAreaDefaultY;
    public enum Direction { UP, DOWN, LEFT, RIGHT }
    private Direction direction = Direction.DOWN;
    private final EnumMap<Direction, BufferedImage[]> sprites = new EnumMap<>(Direction.class);
    private int spriteCounter = 0;
    private int spriteNum = 1;

    protected final GamePanel gp;

    public Entity(GamePanel gp) {
        this.gp = gp;
        this.solidAreaDefaultX = solidArea.x;
        this.solidAreaDefaultY = solidArea.y;
    }

    public void draw(Graphics2D g2) {
        BufferedImage img = getCurrentSprite(direction, spriteNum);
        if (img == null) return;

        int tileSize = gp.getTileSize();
        int px = gp.getPlayer().getWorldX();
        int py = gp.getPlayer().getWorldY();
        int sx = gp.getPlayer().getScreenX();
        int sy = gp.getPlayer().getScreenY();

        int screenX = worldX - px + sx;
        int screenY = worldY - py + sy;

        if (worldX + tileSize > px - sx && worldX - tileSize < px + sx
                && worldY + tileSize > py - sy && worldY - tileSize < py + sy) {
            g2.drawImage(img, screenX, screenY, null);
        }
    }

    protected void setSpriteFrames(Direction dir, BufferedImage... frames) {
        sprites.put(dir, frames);
    }

    protected void animate(int frameThreshold) {
        spriteCounter++;
        if (spriteCounter > frameThreshold) {
            spriteNum = (spriteNum == 1) ? 2 : 1;
            spriteCounter = 0;
        }
    }

    public BufferedImage getCurrentSprite(Direction dir, int num) {
        BufferedImage[] frames = sprites.get(dir);
        if (frames == null || frames.length == 0) return null;
        int idx = Math.min(Math.max(num - 1, 0), frames.length - 1);
        return frames[idx];
    }

    public int getWorldX() { return worldX; }
    public void setWorldX(int worldX) { this.worldX = worldX; }

    public int getWorldY() { return worldY; }
    public void setWorldY(int worldY) { this.worldY = worldY; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public boolean isPassable() { return passable; }
    public void setPassable(boolean passable) { this.passable = passable; }

    public Rectangle getSolidArea() { return solidArea; }
    public void setSolidArea(Rectangle solidArea) {
        this.solidArea = solidArea;
        this.solidAreaDefaultX = solidArea.x;
        this.solidAreaDefaultY = solidArea.y;
    }
    public int getSolidAreaDefaultX() { return solidAreaDefaultX; }
    public int getSolidAreaDefaultY() { return solidAreaDefaultY; }

    public void setSolidAreaDefaultX(int solidAreaDefaultX) {
        this.solidAreaDefaultX = solidAreaDefaultX;
    }

    public void setSolidAreaDefaultY(int solidAreaDefaultY) {
        this.solidAreaDefaultY = solidAreaDefaultY;
    }

    public Direction getDirection() { return direction; }
    public void setDirection(Direction direction) { this.direction = direction; }

    public int getSpriteCounter() { return spriteCounter; }
    public void setSpriteCounter(int spriteCounter) { this.spriteCounter = spriteCounter; }

    public int getSpriteNum() { return spriteNum; }
    public void setSpriteNum(int spriteNum) { this.spriteNum = spriteNum; }

    protected GamePanel getGp() { return gp; }

    public EnumMap<Direction, BufferedImage[]> getSprites() {
        return sprites;
    }
}
