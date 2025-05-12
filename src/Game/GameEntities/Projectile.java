package Game.GameEntities;

import Launcher.GamePanel;
import java.awt.Graphics2D;

public abstract class Projectile extends Enemy {
    private final int speed;
    private int dx, dy;

    public Projectile(GamePanel gp, int speed) {
        super(gp);
        this.speed = speed;
        setAlive(false);
    }

    public void fire(int startX, int startY, int dx, int dy) {
        setWorldX(startX);
        setWorldY(startY);
        this.dx = dx;
        this.dy = dy;
        setAlive(true);
        setDirection(computeCardinalDirection(dx, dy));
        setHealth(getMaxHealth());
    }

    private DirectionType computeCardinalDirection(int dx, int dy) {
        if (Math.abs(dx) > Math.abs(dy)) {
            return dx < 0 ? LEFT() : RIGHT();
        } else {
            return dy < 0 ? UP() : DOWN();
        }
    }

    @Override
    public void update() {
        if (!isAlive()) return;

        setWorldX(getWorldX() + dx * speed);
        setWorldY(getWorldY() + dy * speed);

        getChecker().checkTile(this);
        if (isCollisionOn()) {
            setAlive(false);
            return;
        }

        animate(8);

        boolean hit = getChecker().checkPlayer(this);
        if (hit && getGp().getPlayer().isInvincible()) {
            attack();
            setAlive(false);
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        if (!isAlive()) return;
        super.draw(g2);
    }
}