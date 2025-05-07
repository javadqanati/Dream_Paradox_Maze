
package Game.GameEntities;

import Launcher.GamePanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public final class ChaserEnemy extends Enemy {
    private int currentTargetIndex = 0;

    public ChaserEnemy(GamePanel gp) {
        super(gp);
        initializeChaser();
    }

    private void initializeChaser() {
        setName("Chaser");
        setSpeed(3);
        setMaxHealth(4);
        setHealth(getMaxHealth());
        getEnemyImage();
        setDirection(Direction.DOWN);
    }

    public void followPlayerTrail() {
        LinkedList<Point> trail = getGp().getPlayer().getTrail();
        Point target = (currentTargetIndex < trail.size())
                ? trail.get(currentTargetIndex)
                : new Point(getGp().getPlayer().getWorldX(), getGp().getPlayer().getWorldY());

        int dx = target.x - getWorldX();
        int dy = target.y - getWorldY();
        if (Math.abs(dx) < 2 && Math.abs(dy) < 2) {
            currentTargetIndex++;
        } else {
            setDirection((Math.abs(dx) > Math.abs(dy))
                    ? (dx < 0 ? Direction.LEFT : Direction.RIGHT)
                    : (dy < 0 ? Direction.UP : Direction.DOWN));
        }
    }

    @Override
    public void update() {
        super.update();
        chaseLogic();
    }

    private void chaseLogic() {
        if (!isCollisionOn()) {
            moveInDirection();
        } else {
            flipDirection();
        }
        detectPlayerAndAttack();
    }

    private void moveInDirection() {
        switch (getDirection()) {
            case UP -> setWorldY(getWorldY() - getSpeed());
            case DOWN -> setWorldY(getWorldY() + getSpeed());
            case LEFT -> setWorldX(getWorldX() - getSpeed());
            case RIGHT -> setWorldX(getWorldX() + getSpeed());
        }
    }

    private void flipDirection() {
        setDirection(switch (getDirection()) {
            case UP -> Direction.DOWN;
            case DOWN -> Direction.UP;
            case LEFT -> Direction.RIGHT;
            case RIGHT -> Direction.LEFT;
        });
    }

    private void detectPlayerAndAttack() {
        setCollisionOn(false);
        getGp().getCollisionChecker().checkTile(this);
        getGp().getCollisionChecker().checkObject(this, false);
        boolean contact = getGp().getCollisionChecker().checkPlayer(this);
        if (contact) {
            attack();
            flipDirection();
            moveInDirection();
            flipDirection();
        }
    }

    @Override
    public void attack() {
        if (!getGp().getPlayer().isInvincible()) {
            getGp().getPlayer().setHealth(getGp().getPlayer().getHealth() - 1);
            getGp().getPlayer().setInvincible(true);
        }
    }

    @Override
    public void getEnemyImage() {
        loadSprites();
    }

    private void loadSprites() {
        BufferedImage f1 = getMaker().characterSkinSetup("/Monster/greenslime_down_1");
        BufferedImage f2 = getMaker().characterSkinSetup("/Monster/greenslime_down_2");
        setSpriteFrames(Direction.UP, f1, f2);
        setSpriteFrames(Direction.DOWN, f1, f2);
        setSpriteFrames(Direction.LEFT, f1, f2);
        setSpriteFrames(Direction.RIGHT, f1, f2);
    }

    @Override
    public void setAction() {
        setActionLockCounter(getActionLockCounter() + 1);
        if (getActionLockCounter() >= 15) {
            followPlayerTrail();
            setActionLockCounter(0);
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        if (isOnScreen()) return;
        BufferedImage img = getCurrentSprite();
        if (img != null) {
            g2.drawImage(img, getScreenX(), getScreenY(), null);
        }
    }

    private int getScreenX() {
        return getWorldX() - getGp().getPlayer().getWorldX() + getGp().getPlayer().getScreenX();
    }

    private int getScreenY() {
        return getWorldY() - getGp().getPlayer().getWorldY() + getGp().getPlayer().getScreenY();
    }
}
