
package Game.GameEntities;

import Launcher.GamePanel;
import graphicals.SpriteMaker;

public abstract class Enemy extends Character {
    private int actionLockCounter = 0;
    private final SpriteMaker maker = new SpriteMaker(getGp());

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

    public abstract void getEnemyImage();
    public abstract void setAction();
    public abstract void attack();

    public int getActionLockCounter() {
        return actionLockCounter;
    }

    public void setActionLockCounter(int actionLockCounter) {
        this.actionLockCounter = actionLockCounter;
    }

    public boolean isOnScreen() {
        int tile = getGp().getTileSize();
        int px = getGp().getPlayer().getWorldX();
        int py = getGp().getPlayer().getWorldY();
        int sx = getGp().getPlayer().getScreenX();
        int sy = getGp().getPlayer().getScreenY();
        int wx = getWorldX();
        int wy = getWorldY();
        return wx + tile <= px - sx || wx - tile >= px + sx
                || wy + tile <= py - sy || wy - tile >= py + sy;
    }

    public SpriteMaker getMaker() {
        return maker;
    }
}
