package Game.GameEntities;

import Audio.AudioManager;
import Input.PlayerInputHandler;
import Launcher.GamePanel;
import Market.PowerUpShop;
import graphicals.SpriteMaker;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Player extends Character {
    private int collectedFragments = 9;
    private List<PowerUp> powerUps = new ArrayList<>();
    private final PlayerInputHandler playerInputHandler;
    private final int screenX;
    private final int screenY;
    private final AudioManager audio = new AudioManager();
    private final LinkedList<Point> trail = new LinkedList<>();
    private final int MAX_TRAIL_SIZE = 50;
    private boolean invincible = false;
    private int invincibleCounter = 0;

    public int getScreenY() {
        return screenY;
    }

    public int getScreenX() {
        return screenX;
    }

    public Player(GamePanel gp, PlayerInputHandler playerInputHandler) {
        super(gp);
        this.playerInputHandler = playerInputHandler; // Updated initialization
        setDefaultValues();
        getPlayerImage();
        this.setDirection("down");
        screenX = gp.getScreenWidth() / 2 - (gp.getTileSize() / 2);
        screenY = gp.getScreenHeight() / 2 - (gp.getTileSize() / 2);

        setSolidArea(new Rectangle());
        getSolidArea().x = 2;
        getSolidArea().y = 3;
        getSolidArea().width = 30;
        getSolidArea().height = 30;
        setSolidAreaDefaultX(getSolidArea().x);
        setSolidAreaDefaultY(getSolidArea().y);
    }

    public void setDefaultValues() {
        setWorldX(9 * getGp().getTileSize());
        setWorldY(9 * getGp().getTileSize());
        setSpeed(4);
        setSpriteNum(1);
        setDirection("down");
        setMaxHealth(6);
        setHealth(getMaxHealth());
    }

    public void getPlayerImage() {
        SpriteMaker spriteMaker = new SpriteMaker(getGp());

        setUp1(spriteMaker.characterSkinSetup("/Player/boy_up_1"));
        setUp2(spriteMaker.characterSkinSetup("/Player/boy_up_2"));
        setDown1(spriteMaker.characterSkinSetup("/Player/boy_down_1"));
        setDown2(spriteMaker.characterSkinSetup("/Player/boy_down_2"));
        setRight1(spriteMaker.characterSkinSetup("/Player/boy_right_1"));
        setRight2(spriteMaker.characterSkinSetup("/Player/boy_right_2"));
        setLeft1(spriteMaker.characterSkinSetup("/Player/boy_left_1"));
        setLeft2(spriteMaker.characterSkinSetup("/Player/boy_left_2"));
    }

    public void update() {
        if (playerInputHandler.isUpPressed() || playerInputHandler.isDownPressed() || playerInputHandler.isLeftPressed() || playerInputHandler.isRightPressed()) {
            if (playerInputHandler.isUpPressed()) {
                setDirection("up");
            } else if (playerInputHandler.isDownPressed()) {
                setDirection("down");
            } else if (playerInputHandler.isLeftPressed()) {
                setDirection("left");
            } else if (playerInputHandler.isRightPressed()) {
                setDirection("right");
            }
            setSpriteCounter(getSpriteCounter() + 1);
            if (getSpriteCounter() > 10) {
                if (getSpriteNum() == 1) {
                    setSpriteNum(2);
                } else if (getSpriteNum() == 2) {
                    setSpriteNum(1);
                }
                setSpriteCounter(0);
            }
            setCollisionOn(false);
            getGp().getCollisionChecker().checkTile(this);
            int entityIndex = getGp().getCollisionChecker().checkObject(this, true);
            collectFragments(entityIndex);
            getGp().getCollisionChecker().checkEntity(this, getGp().getEnemies());

            if (!isCollisionOn()) {
                switch (getDirection()) {
                    case "up":
                        setWorldY(getWorldY() - getSpeed());
                        break;
                    case "down":
                        setWorldY(getWorldY() + getSpeed());
                        break;
                    case "left":
                        setWorldX(getWorldX() - getSpeed());
                        break;
                    case "right":
                        setWorldX(getWorldX() + getSpeed());
                        break;
                }
            }
        }
        if (invincible) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
        updateTrail();
    }

    public boolean usePowerUp(String powerUpType) {
        for (PowerUp powerUp : powerUps) {
            if (powerUp.getType().equals(powerUpType)) {
                powerUp.apply();
                powerUps.remove(powerUp);
                return true;
            }
        }
        return false;
    }

    public void updateTrail() {
        Point currentPosition = new Point(getWorldX(), getWorldY());
        // Only add if moved from last recorded position
        if (trail.isEmpty() || !trail.getLast().equals(currentPosition)) {
            trail.add(currentPosition);
            if (trail.size() > MAX_TRAIL_SIZE) {
                trail.removeFirst();
            }
        }
    }

    public LinkedList<Point> getTrail() {
        return trail;
    }

    public boolean usePowerUp(PowerUp powerUp) {
        if (powerUps.contains(powerUp)) {
            powerUp.apply();  // Assuming activate() method exists in PowerUp
            powerUps.remove(powerUp);  // Optional: if one-time use
            return true;
        }
        return false;
    }

    @Override
    public void draw(Graphics2D g2) {
        BufferedImage img = null;

        switch (getDirection()) {
            case "up":
                if (getSpriteNum() == 1) {
                    img = getUp1();
                }
                if (getSpriteNum() == 2) {
                    img = getUp2();
                }
                break;
            case "down":
                if (getSpriteNum() == 1) {
                    img = getDown1();
                }
                if (getSpriteNum() == 2) {
                    img = getDown2();
                }
                break;
            case "left":
                if (getSpriteNum() == 1) {
                    img = getLeft1();
                }
                if (getSpriteNum() == 2) {
                    img = getLeft2();
                }
                break;
            case "right":
                if (getSpriteNum() == 1) {
                    img = getRight1();
                }
                if (getSpriteNum() == 2) {
                    img = getRight2();
                }
                break;
        }
        g2.drawImage(img, screenX, screenY, null);
    }

    public void collectFragments(int i) {
        if (i != 999) {
            String name = getGp().getGameEntities()[i].getName();
            switch (name) {
                case "Memory Fragment":
                    collectedFragments++;
                    getGp().getGameEntities()[i] = null;
                    System.out.println(collectedFragments);
                    audio.playSE(1);
                    break;
                case "Entrance":
                    audio.playSE(3);
                    break;
                case "Exit":
                    getGp().getHud().setGameFinished(true);
                    getGp().getAudioManager().playSE(4);
                    getGp().getAudioManager().stopMusic();
                    break;
                case "Speed Boost":
                    setSpeed(getSpeed() + 2);
                    getGp().getGameEntities()[i] = null;
                    audio.playSE(2);
                    getGp().getHud().showMessage("Speed Up!");
                    break;
            }
        }
    }

    public int getCollectedFragments() {
        return collectedFragments;
    }

    public List<PowerUp> getPowerUps() {
        return powerUps;
    }

    public boolean isInvincible() {
        return invincible;
    }

    public void setInvincible(boolean invincible) {
        this.invincible = invincible;
    }

    public void addPowerUp(PowerUp powerUp) {
        powerUps.add(powerUp);
    }

    public void setCollectedFragments(int collectedFragments) {
        this.collectedFragments = collectedFragments;
    }

    public void addFragments(int amount) {
        collectedFragments += amount;
    }


}
