package Game.GameEntities;


import Launcher.GamePanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.EnumMap;

public abstract class Entity {
    private int worldX, worldY;
    private String name;
    private boolean passable = false;
    private int solidAreaDefaultX = 0, solidAreaDefaultY = 0;
    public enum Direction { UP, DOWN, LEFT, RIGHT }
    private Direction direction = Direction.DOWN;
    private final EnumMap<Direction, BufferedImage[]> sprites = new EnumMap<>(Direction.class);
    private int spriteCounter = 0;
    private int spriteNum = 1;
    protected final GamePanel gp;
    private Rectangle solidArea = new Rectangle(solidAreaDefaultX, solidAreaDefaultY, 48, 48);

    public Entity(GamePanel gp) {
        this.gp = gp;
        this.solidAreaDefaultX = solidArea.x;
        this.solidAreaDefaultY = solidArea.y;
    }

    public void draw(Graphics2D g2) {
        if (!isOnScreen()) return;

        BufferedImage img = getCurrentSprite(getDirection(), getSpriteNum());
        if (img == null) return;

        Point screenPos = calculateScreenPosition();
        g2.drawImage(img, screenPos.x, screenPos.y, null);
    }

    protected Point calculateScreenPosition() {
        int px = getGp().getPlayer().getWorldX();
        int py = getGp().getPlayer().getWorldY();
        int sx = getGp().getPlayer().getScreenX();
        int sy = getGp().getPlayer().getScreenY();

        return new Point(
                getWorldX() - px + sx,
                getWorldY() - py + sy
        );
    }

    protected boolean isOnScreen() {
        int tile = getGp().getTileSize();
        int px = getGp().getPlayer().getWorldX();
        int py = getGp().getPlayer().getWorldY();
        int sx = getGp().getPlayer().getScreenX();
        int sy = getGp().getPlayer().getScreenY();

        return getWorldX() + tile > px - sx &&
                getWorldX() - tile < px + sx &&
                getWorldY() + tile > py - sy &&
                getWorldY() - tile < py + sy;
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

    public Rectangle getSolidArea() { return solidArea; }
    public void setSolidArea(Rectangle solidArea) {
        this.solidArea = solidArea;
        this.solidAreaDefaultX = solidArea.x;
        this.solidAreaDefaultY = solidArea.y;
    }

    public void setSolidAreaDefaultX(int solidAreaDefaultX) {
        this.solidAreaDefaultX = solidAreaDefaultX;
    }
    public void setSolidAreaDefaultY(int solidAreaDefaultY) {
        this.solidAreaDefaultY = solidAreaDefaultY;
    }
    public Direction getDirection()                         { return direction; }
    public void setDirection(Direction direction)           { this.direction = direction; }
    public int getSpriteCounter()                           { return spriteCounter; }
    public void setSpriteCounter(int spriteCounter)         { this.spriteCounter = spriteCounter; }
    public int getSpriteNum()                               { return spriteNum; }
    public void setSpriteNum(int spriteNum)                 { this.spriteNum = spriteNum; }
    public GamePanel getGp()                                { return gp; }
    public EnumMap<Direction, BufferedImage[]> getSprites() {
        return sprites;
    }
    public int getWorldX()                                  { return worldX; }
    public void setWorldX(int worldX)                       { this.worldX = worldX; }
    public int getWorldY()                                  { return worldY; }
    public void setWorldY(int worldY)                       { this.worldY = worldY; }
    public String getName()                                 { return name; }
    public void setName(String name)                        { this.name = name; }
    public boolean isPassable()                             { return passable; }
    public void setPassable(boolean passable)               { this.passable = passable; }
}
