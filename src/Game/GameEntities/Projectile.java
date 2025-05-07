package Game.GameEntities;

import Launcher.GamePanel;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public abstract class Projectile extends Enemy {
    private int speed;
    private int dx, dy;
    private boolean alive;

    public Projectile(GamePanel gp, int speed) {
        super(gp);
        this.speed = speed;
        this.alive = false;
    }

    public void fire(int startX, int startY, int dx, int dy) {
        setWorldX(startX);
        setWorldY(startY);
        this.dx = dx;
        this.dy = dy;
        this.alive = true;
        setDirection(computeCardinalDirection(dx, dy));
        setHealth(getMaxHealth());
    }

    private Direction computeCardinalDirection(int dx, int dy) {
        if (Math.abs(dx) > Math.abs(dy)) {
            return dx < 0 ? Direction.LEFT : Direction.RIGHT;
        } else {
            return dy < 0 ? Direction.UP : Direction.DOWN;
        }
    }

    @Override
    public void update() {
        if (!alive) return;

        // move
        setWorldX(getWorldX() + dx * speed);
        setWorldY(getWorldY() + dy * speed);

        // check tile collision
        getGp().getCollisionChecker().checkTile(this);
        if (isCollisionOn()) {
            alive = false;
            return;
        }

        // animation
        animate(8);

        // check player collision
        boolean hit = getGp().getCollisionChecker().checkPlayer(this);
        if (hit && !getGp().getPlayer().isInvincible()) {
            attack();
            alive = false;
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        if (!alive || isOnScreen()) return;
        BufferedImage img = getCurrentSprite(getDirection(), getSpriteNum());
        if (img != null) {
            int tileSize = getGp().getTileSize();
            int px = getGp().getPlayer().getWorldX();
            int py = getGp().getPlayer().getWorldY();
            int sx = getGp().getPlayer().getScreenX();
            int sy = getGp().getPlayer().getScreenY();
            int screenX = getWorldX() - px + sx;
            int screenY = getWorldY() - py + sy;
            g2.drawImage(img, screenX, screenY, null);
        }
    }
}