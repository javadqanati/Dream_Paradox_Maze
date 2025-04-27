package Game.GameEntities;

import Launcher.GamePanel;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public abstract class Character extends Entity {
    private int health;
    private int maxHealth;
    private int speed;
    private boolean collisionOn = false;
    private boolean alive = true;

    public Character(GamePanel gp) {
        super(gp);
    }

    public abstract void update();

    @Override
    public abstract void draw(Graphics2D g2);

    protected void animate(int frameThreshold) {
        super.animate(frameThreshold);
    }

    protected BufferedImage getCurrentSprite() {
        return super.getCurrentSprite(getDirection(), getSpriteNum());
    }

    public boolean isCollisionOn() { return collisionOn; }
    public void setCollisionOn(boolean collisionOn) { this.collisionOn = collisionOn; }

    public int getHealth() { return health; }
    public void setHealth(int health) { this.health = health; }

    public int getMaxHealth() { return maxHealth; }
    public void setMaxHealth(int maxHealth) { this.maxHealth = maxHealth; }

    public int getSpeed() { return speed; }
    public void setSpeed(int speed) { this.speed = speed; }

    public int getSpriteCounter() { return super.getSpriteCounter(); }
    public void setSpriteCounter(int spriteCounter) { super.setSpriteCounter(spriteCounter); }

    public int getSpriteNum() { return super.getSpriteNum(); }
    public void setSpriteNum(int spriteNum) { super.setSpriteNum(spriteNum); }

    public GamePanel getGp() { return super.getGp(); }

    public boolean isAlive() {
        return alive;
    }
    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}