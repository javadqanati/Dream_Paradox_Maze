package Game.GameEntities;

import Launcher.GamePanel;
import graphicals.SpriteMaker;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public final class ChaserEnemy extends Enemy {
    private int currentTargetIndex = 0;

    public ChaserEnemy(GamePanel gp) {
        super(gp);
        setName("Chaser");
        setSpeed(3);
        setMaxHealth(4);
        setHealth(getMaxHealth());
        setActionLockCounter(0);
        getSolidArea().x = 3;
        getSolidArea().y = 18;
        getSolidArea().width = 42;
        getSolidArea().height = 30;
        setSolidAreaDefaultX(getSolidArea().x);
        setSolidAreaDefaultY(getSolidArea().y);
        getEnemyImage();
        setDirection("down");
    }

    @Override
    public void update() {
        setAction(); // Sets direction based on AI logic

        if (!isCollisionOn()) {
            switch (getDirection()) {
                case "up" -> setWorldY(getWorldY() - getSpeed());
                case "down" -> setWorldY(getWorldY() + getSpeed());
                case "left" -> setWorldX(getWorldX() - getSpeed());
                case "right" -> setWorldX(getWorldX() + getSpeed());
            }
        } else {
            // Reverse direction on collision
            switch (getDirection()) {
                case "up" -> setDirection("down");
                case "down" -> setDirection("up");
                case "left" -> setDirection("right");
                case "right" -> setDirection("left");
            }
        }

        setSpriteCounter(getSpriteCounter() + 1);
        if (getSpriteCounter() > 12) {
            setSpriteNum(getSpriteNum() == 1 ? 2 : 1);
            setSpriteCounter(0);
        }
        setCollisionOn(false);
        getGp().getCollisionChecker().checkTile(this);
        getGp().getCollisionChecker().checkObject(this, false);
        boolean contactPlayer = getGp().getCollisionChecker().checkPlayer(this);

        if(contactPlayer){
            attack();
        }
    }

    @Override
    public void attack(){
        if(!getGp().getPlayer().isInvincible()){
            getGp().getPlayer().setHealth(getGp().getPlayer().getHealth() - 1);
            getGp().getPlayer().setInvincible(true);
        }
    }

    @Override
    public void getEnemyImage() {
        SpriteMaker spriteMaker = new SpriteMaker(getGp());

        setUp1(spriteMaker.characterSkinSetup("/Monster/greenslime_down_1"));
        setUp2(spriteMaker.characterSkinSetup("/Monster/greenslime_down_2"));
        setDown1(spriteMaker.characterSkinSetup("/Monster/greenslime_down_1"));
        setDown2(spriteMaker.characterSkinSetup("/Monster/greenslime_down_2"));
        setRight1(spriteMaker.characterSkinSetup("/Monster/greenslime_down_1"));
        setRight2(spriteMaker.characterSkinSetup("/Monster/greenslime_down_2"));
        setLeft1(spriteMaker.characterSkinSetup("/Monster/greenslime_down_1"));
        setLeft2(spriteMaker.characterSkinSetup("/Monster/greenslime_down_2"));
    }

    @Override
    public void setAction() {
        setActionLockCounter(getActionLockCounter() + 1);

        if (getActionLockCounter() >= 15) {
            LinkedList<Point> playerTrail = getGp().getPlayer().getTrail();

            Point target = currentTargetIndex >= playerTrail.size()
                    ? new Point(getGp().getPlayer().getWorldX(), getGp().getPlayer().getWorldY())
                    : playerTrail.get(currentTargetIndex);

            int dx = target.x - getWorldX();
            int dy = target.y - getWorldY();

            if (Math.abs(dx) < 2 && Math.abs(dy) < 2) {
                currentTargetIndex++;
            } else {
                setDirection(Math.abs(dx) > Math.abs(dy)
                        ? (dx < 0 ? "left" : "right")
                        : (dy < 0 ? "up" : "down"));
            }

            setActionLockCounter(0);
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        int screenX = getWorldX() - getGp().getPlayer().getWorldX() + getGp().getPlayer().getScreenX();
        int screenY = getWorldY() - getGp().getPlayer().getWorldY() + getGp().getPlayer().getScreenY();

        if (getWorldX() + getGp().getTileSize() > getGp().getPlayer().getWorldX() - getGp().getPlayer().getScreenX() &&
                getWorldX() - getGp().getTileSize() < getGp().getPlayer().getWorldX() + getGp().getPlayer().getScreenX() &&
                getWorldY() + getGp().getTileSize() > getGp().getPlayer().getWorldY() - getGp().getPlayer().getScreenY() &&
                getWorldY() - getGp().getTileSize() < getGp().getPlayer().getWorldY() + getGp().getPlayer().getScreenY()) {

            BufferedImage img = switch (getDirection()) {
                case "up" -> getSpriteNum() == 1 ? getUp1() : getUp2();
                case "down" -> getSpriteNum() == 1 ? getDown1() : getDown2();
                case "left" -> getSpriteNum() == 1 ? getLeft1() : getLeft2();
                case "right" -> getSpriteNum() == 1 ? getRight1() : getRight2();
                default -> null;
            };

            if (img != null) {
                g2.drawImage(img, screenX, screenY, null);
            }
        }
    }
}