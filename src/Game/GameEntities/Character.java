package Game.GameEntities;

import Launcher.GamePanel;

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
    public boolean isAlive() {
        return alive;
    }
    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}