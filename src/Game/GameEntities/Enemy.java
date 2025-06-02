
package Game.GameEntities;
import Launcher.GamePanel;

import java.util.ArrayList;
import java.util.List;


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
        getChecker().checkTile(this);
        getChecker().checkObject(this, false);

        if (isCollisionOn()) {
            reverseDirection();
        }
    }

    private void reverseDirection() {
        DirectionType dir = getDirection();
        if      (dir == UP())    setWorldY(getWorldY() - getSpeed());
        else if (dir == DOWN())  setWorldY(getWorldY() + getSpeed());
        else if (dir == LEFT())  setWorldX(getWorldX() - getSpeed());
        else if (dir == RIGHT()) setWorldX(getWorldX() + getSpeed());
    }

    public void flipDirection() {
        setDirection(randomDirection());
    }

    public DirectionType randomDirection() {
        DirectionType current = getDirection();
        DirectionType[] dirs = new DirectionType[]{ UP(), DOWN(), LEFT(), RIGHT() };

        List<DirectionType> available = new ArrayList<>();
        for (DirectionType d : dirs) {
            if (!d.equals(current)) {
                available.add(d);
            }
        }

        int idx = (int)(Math.random() * available.size());
        return available.get(idx);
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
