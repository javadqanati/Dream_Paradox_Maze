package Game.GameEntities;


import Launcher.GamePanel;
import graphicals.CollisionChecker;
import graphicals.SpriteMaker;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.EnumMap;

public abstract class Entity implements DirectionType{
    private int worldX, worldY;
    private String name;
    private boolean passable = false;
    private int solidAreaDefaultX, solidAreaDefaultY;
    private enum Direction implements DirectionType {
        UP, DOWN, LEFT, RIGHT
    }
    private Direction direction = Direction.DOWN;
    private final EnumMap<Direction, BufferedImage[]> sprites = new EnumMap<>(Direction.class);
    private int spriteCounter = 0;
    private int spriteNum = 1;
    private final GamePanel gp;
    private Rectangle solidArea = new Rectangle(solidAreaDefaultX, solidAreaDefaultY, 48, 48);
    private final SpriteMaker maker;
    private final CollisionChecker checker;

    public Entity(GamePanel gp) {
        this.gp = gp;
        maker = new SpriteMaker(gp);
        checker = new CollisionChecker(gp);
        this.solidAreaDefaultX = solidArea.x;
        this.solidAreaDefaultY = solidArea.y;
    }

    public abstract void getImages();

    public void draw(Graphics2D g2) {
        if (!isOnScreen()) return;

        BufferedImage img = getCurrentSprite(direction, getSpriteNum());
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

    protected void animate(int frameThreshold) {
        spriteCounter++;
        if (spriteCounter > frameThreshold) {
            spriteNum = (spriteNum == 1) ? 2 : 1;
            spriteCounter = 0;
        }
    }

    public BufferedImage getCurrentSprite(DirectionType dirType, int num) {
        if (!(dirType instanceof Direction dir)) {
            throw new IllegalArgumentException("Invalid direction: " + dirType);
        }
        BufferedImage[] frames = sprites.get(dir);
        if (frames == null || frames.length == 0) {
            return null;
        }
        int idx = Math.min(Math.max(num - 1, 0), frames.length - 1);
        return frames[idx];
    }

    public BufferedImage getCurrentSprite() {
        return getCurrentSprite(direction, getSpriteNum());
    }

    public void setDirection(DirectionType dirType) {
        if (!(dirType instanceof Direction)) {
            throw new IllegalArgumentException("Invalid direction: " + dirType);
        }
        this.direction = (Direction) dirType;
    }

    public void setSpriteFrames(DirectionType dirType, BufferedImage... frames) {
        if (!(dirType instanceof Direction)) {
            throw new IllegalArgumentException("Invalid direction: " + dirType);
        }
        sprites.put((Direction) dirType, frames);
    }

    public DirectionType getDirection() {
        return direction;
    }
    public static DirectionType UP()    { return Direction.UP; }
    public static DirectionType DOWN()  { return Direction.DOWN; }
    public static DirectionType LEFT()  { return Direction.LEFT; }
    public static DirectionType RIGHT() { return Direction.RIGHT; }

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
    public SpriteMaker getMaker()                           { return maker; }
    public CollisionChecker getChecker() {
        return checker;
    }
}
