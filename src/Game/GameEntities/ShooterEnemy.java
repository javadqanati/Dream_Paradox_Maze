package Game.GameEntities;

import Launcher.GamePanel;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public final class ShooterEnemy extends Enemy {
    private int shootCooldown;
    private static final int SHOOT_INTERVAL = 60; // frames
    private final List<Projectile> projectiles = new ArrayList<>();

    public ShooterEnemy(GamePanel gp) {
        super(gp);
        initializeShooter();
    }

    private void initializeShooter() {
        setName("Shooter");
        setSpeed(1);
        setMaxHealth(4);
        setHealth(getMaxHealth());
        getImages();
        setDirection(DOWN());
        shootCooldown = SHOOT_INTERVAL;
    }

    @Override
    public void update() {
        super.update();
        handleMovement();
        handleShooting();
        projectiles.removeIf(p -> { p.update(); return !p.isAlive(); });
    }

    private void handleMovement() {
        setCollisionOn(false);
        getGp().getCollisionChecker().checkTile(this);
        getGp().getCollisionChecker().checkObject(this,false);
        if (!isCollisionOn()) {
            moveInDirection();
        } else {
            flipDirection();
        }
    }

    private void moveInDirection() {
        DirectionType dir = getDirection();
        if (dir == UP()) setWorldY(getWorldY() - getSpeed());
        else if (dir == DOWN()) setWorldY(getWorldY() + getSpeed());
        else if (dir == LEFT()) setWorldX(getWorldX() - getSpeed());
        else if (dir == RIGHT()) setWorldX(getWorldX() + getSpeed());
    }

    private void handleShooting() {
        shootCooldown--;
        if (shootCooldown <= 0) {
            spawnProjectile();
            shootCooldown = SHOOT_INTERVAL;
        }
    }

    private void spawnProjectile() {
        Chiz ball = new Chiz(getGp());
        int tile = getGp().getTileSize();
        int startX = getWorldX() + getSolidArea().width/2 - tile/2;
        int startY = getWorldY() + getSolidArea().height/2;

        int pdx = getGp().getPlayer().getWorldX() - startX;
        int pdy = getGp().getPlayer().getWorldY() - startY;

        int dx = Math.abs(pdx) > Math.abs(pdy) ? (pdx < 0 ? -1 : 1) : 0;
        int dy = Math.abs(pdy) >= Math.abs(pdx) ? (pdy < 0 ? -1 : 1) : 0;

        DirectionType fireDir = (Math.abs(pdx) > Math.abs(pdy))
                ? (dx < 0 ? LEFT() : RIGHT())
                : (dy < 0 ? UP() : DOWN());

        if (fireDir != getDirection()) {
            return;
        }

        ball.fire(startX, startY, dx, dy);
        projectiles.add(ball);
    }

    @Override
    public void attack() {
        if (getGp().getPlayer().isInvincible()) {
            getGp().getPlayer().setHealth(getGp().getPlayer().getHealth() - 1);
            Player.setInvincible(true);
        }
    }

    @Override
    public void getImages() {
        loadSprites("/Monster/dalli_up1", "/Monster/dalli_up2",
                "/Monster/dalli_left1", "/Monster/dalli_left2",
                "/Monster/dalli_right1", "/Monster/dalli_right2",
                "/Monster/dalli_down1", "/Monster/dalli_down2");
    }

    @Override
    public void setAction() {
        setActionLockCounter(getActionLockCounter() + 1);
        if (getActionLockCounter() >= 120) {
            setDirection(randomDirection());
            setActionLockCounter(0);
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);
        for (Projectile p : projectiles) p.draw(g2);
    }
}