
package Game.GameEntities;
import Launcher.GamePanel;


public abstract class Enemy extends Character {
    private int actionLockCounter = 0;

    public Enemy(GamePanel gp) {
        super(gp);
    }

    @Override
    public void update() {
        setAction();
        performMovement();
        animate(12);
    }

    private void performMovement() {
        setCollisionOn(false);
        getGp().getCollisionChecker().checkTile(this);
        getGp().getCollisionChecker().checkObject(this, false);

        if (isCollisionOn()) {
            reverseDirection();
        }
    }

    private void reverseDirection() {
        switch (getDirection()) {
            case UP    -> setWorldY(getWorldY() - getSpeed());
            case DOWN  -> setWorldY(getWorldY() + getSpeed());
            case LEFT  -> setWorldX(getWorldX() - getSpeed());
            case RIGHT -> setWorldX(getWorldX() + getSpeed());
        }
    }

    public abstract void setAction();
    public abstract void attack();
    public int getActionLockCounter() {
        return actionLockCounter;
    }
    public void setActionLockCounter(int actionLockCounter) {
        this.actionLockCounter = actionLockCounter;
    }
}
