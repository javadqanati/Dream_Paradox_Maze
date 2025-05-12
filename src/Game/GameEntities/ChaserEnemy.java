
package Game.GameEntities;

import Launcher.GamePanel;
import java.awt.*;
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
        getImages();
        setDirection(DOWN());
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
                    ? (dx < 0 ? LEFT() : RIGHT())
                    : (dy < 0 ? UP() : DOWN()));
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
            setDirection(randomDirection());
        }
        detectPlayerAndAttack();
    }

    private void moveInDirection() {
        DirectionType dir = getDirection();
        if      (dir == UP())    setWorldY(getWorldY() - getSpeed());
        else if (dir == DOWN())  setWorldY(getWorldY() + getSpeed());
        else if (dir == LEFT())  setWorldX(getWorldX() - getSpeed());
        else if (dir == RIGHT()) setWorldX(getWorldX() + getSpeed());
    }

    private void detectPlayerAndAttack() {
        setCollisionOn(false);
        getChecker().checkTile(this);
        getChecker().checkObject(this, false);
        boolean contact = getChecker().checkPlayer(this);
        if (contact) {
            attack();
            setDirection(randomDirection());
        }
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
        loadSprites("/Monster/greenslime_down_1", "/Monster/greenslime_down_2",
                "/Monster/greenslime_down_1" , "/Monster/greenslime_down_2",
                "/Monster/greenslime_down_1", "/Monster/greenslime_down_2",
                "/Monster/greenslime_down_1", "/Monster/greenslime_down_2");
    }

    @Override
    public void setAction() {
        setActionLockCounter(getActionLockCounter() + 1);
        if (getActionLockCounter() >= 30) {
            followPlayerTrail();
            setActionLockCounter(0);
        }
    }
}