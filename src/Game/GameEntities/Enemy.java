package Game.GameEntities;

import Launcher.GamePanel;

public abstract class Enemy extends Character{
    private int actionLockCounter = 0;

    public Enemy(GamePanel gp) {
        super(gp);
    }

    @Override
    public void update() {
        setAction();
        setCollisionOn(false);
        getGp().getCollisionChecker().checkTile(this);
        getGp().getCollisionChecker().checkObject(this, false);

        if(isCollisionOn()){
            switch (getDirection()) {
                case "up" -> setWorldY(getWorldY() - getSpeed());
                case "down" -> setWorldY(getWorldY() + getSpeed());
                case "left" -> setWorldX(getWorldX() - getSpeed());
                case "right" -> setWorldX(getWorldX() + getSpeed());
            }
        }
        setSolidAreaDefaultX(getSpriteCounter() + 1);
        if (getSpriteCounter() > 12) {
            setSpriteNum(getSpriteNum() == 1 ? 2 : 1);
            setSpriteCounter(0);
        }
        attack();
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
}
