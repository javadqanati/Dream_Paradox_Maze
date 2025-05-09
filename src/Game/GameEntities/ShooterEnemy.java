package Game.GameEntities;

import Launcher.GamePanel;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
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
        setSpeed(2);
        setMaxHealth(4);
        setHealth(getMaxHealth());
        getEnemyImage();
        setDirection(Direction.DOWN);
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
        switch (getDirection()) {
            case UP    -> setWorldY(getWorldY() - getSpeed());
            case DOWN  -> setWorldY(getWorldY() + getSpeed());
            case LEFT  -> setWorldX(getWorldX() - getSpeed());
            case RIGHT -> setWorldX(getWorldX() + getSpeed());
        }
    }

    private void flipDirection() {
        setDirection(switch (getDirection()) {
            case UP    -> Direction.DOWN;
            case DOWN  -> Direction.UP;
            case LEFT  -> Direction.RIGHT;
            case RIGHT -> Direction.LEFT;
        });
    }

    private void handleShooting() {
        shootCooldown--;
        if (shootCooldown <= 0) {
            spawnProjectile();
            shootCooldown = SHOOT_INTERVAL;
        }
    }

    private void spawnProjectile() {
        FireBall ball = new FireBall(getGp());
        int tile = getGp().getTileSize();
        int startX = getWorldX() + getSolidArea().width/2 - tile/2;
        int startY = getWorldY() + getSolidArea().height/2 - tile/2;
        int pdx = getGp().getPlayer().getWorldX() - startX;
        int pdy = getGp().getPlayer().getWorldY() - startY;
        int dx = Math.abs(pdx) > Math.abs(pdy) ? (pdx < 0 ? -1 : 1) : 0;
        int dy = Math.abs(pdy) >= Math.abs(pdx) ? (pdy < 0 ? -1 : 1) : 0;
        ball.fire(startX, startY, dx, dy);
        projectiles.add(ball);
    }

    @Override
    public void attack() {
        if (getGp().getPlayer().isInvincible()) {
            getGp().getPlayer().setHealth(getGp().getPlayer().getHealth() - 1);
            getGp().getPlayer().setInvincible(true);
        }
    }

    @Override
    public void getEnemyImage() {
        loadSprites();
    }

    private void loadSprites() {
        BufferedImage up1 = getMaker().characterSkinSetup("/Monster/dalli_up1");
        BufferedImage up2 = getMaker().characterSkinSetup("/Monster/dalli_up2");
        BufferedImage left1 = getMaker().characterSkinSetup("/Monster/dalli_left1");
        BufferedImage left2 = getMaker().characterSkinSetup("/Monster/dalli_left2");
        BufferedImage right1 = getMaker().characterSkinSetup("/Monster/dalli_right1");
        BufferedImage right2 = getMaker().characterSkinSetup("/Monster/dalli_right2");
        BufferedImage down1 = getMaker().characterSkinSetup("/Monster/dalli_down1");
        BufferedImage down2 = getMaker().characterSkinSetup("/Monster/dalli_down2");

        setSpriteFrames(Direction.UP, up1, up2);
        setSpriteFrames(Direction.DOWN, down1, down2);
        setSpriteFrames(Direction.LEFT, left1, left2);
        setSpriteFrames(Direction.RIGHT, right1, right2);
    }

    @Override
    public void setAction() {
        setActionLockCounter(getActionLockCounter() + 1);
        if (getActionLockCounter() >= 30) {
            setDirection(randomDirection());
            setActionLockCounter(0);
        }
    }

    private Direction randomDirection() {
        Direction[] dirs = Direction.values();
        return dirs[(int)(Math.random() * dirs.length)];
    }

    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);
        for (Projectile p : projectiles) p.draw(g2);
    }
}