package Game.GameEntities;

import Launcher.GamePanel;
import graphicals.SpriteMaker;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Character extends Entity {
    private int health;
    private int maxHealth;
    private int speed;
    private boolean collisionOn = false;
    private boolean alive = true;

    public Character(GamePanel gp) {
        super(gp);
        setSolidArea(new Rectangle(8, 16, 32, 32));
        setSolidAreaDefaultX(getSolidArea().x);
        setSolidAreaDefaultY(getSolidArea().y);
    }

    public abstract void update();
    protected void animate(int frameThreshold) {
        super.animate(frameThreshold);
    }

    protected void loadSprites(String pathUp1, String pathUp2, String pathLeft1, String pathLeft2,
                               String pathRight1, String pathRight2, String pathDown1,  String pathDown2) {
        BufferedImage up1 = getMaker().characterSkinSetup(pathUp1);
        BufferedImage up2 = getMaker().characterSkinSetup(pathUp2);
        BufferedImage left1 = getMaker().characterSkinSetup(pathLeft1);
        BufferedImage left2 = getMaker().characterSkinSetup(pathLeft2);
        BufferedImage right1 = getMaker().characterSkinSetup(pathRight1);
        BufferedImage right2 = getMaker().characterSkinSetup(pathRight2);
        BufferedImage down1 = getMaker().characterSkinSetup(pathDown1);
        BufferedImage down2 = getMaker().characterSkinSetup(pathDown2);

        setSpriteFrames(Direction.UP, up1, up2);
        setSpriteFrames(Direction.DOWN, down1, down2);
        setSpriteFrames(Direction.LEFT, left1, left2);
        setSpriteFrames(Direction.RIGHT, right1, right2);
    }

    protected BufferedImage getCurrentSprite() {
        return super.getCurrentSprite(getDirection(), getSpriteNum());
    }
    public boolean isCollisionOn()             { return collisionOn; }
    public void setCollisionOn(boolean collisionOn) { this.collisionOn = collisionOn; }
    public int getHealth()                     { return health; }
    public void setHealth(int health)          { this.health = health; }
    public int getMaxHealth()                  { return maxHealth; }
    public void setMaxHealth(int maxHealth)    { this.maxHealth = maxHealth; }
    public int getSpeed()                      { return speed; }
    public void setSpeed(int speed)            { this.speed = speed; }
    public boolean isAlive()                   { return alive; }
    public void setAlive(boolean alive)        { this.alive = alive; }
}