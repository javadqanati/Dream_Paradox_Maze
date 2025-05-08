package Game.GameEntities;

import Game.GameEntities.Powerup.PowerUp;
import Input.PlayerInputHandler;
import Launcher.GamePanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import graphicals.SpriteMaker;

public class Player extends Character {
    private int collectedFragments;
    private final List<PowerUp> powerUps = new ArrayList<>();
    private final PlayerInputHandler playerInputHandler;
    private final LinkedList<Point> trail = new LinkedList<>();
    private boolean invincible = false;
    private int invincibleCounter = 0;
    private final int screenX;
    private final int screenY;

    public Player(GamePanel gp, PlayerInputHandler handler) {
        super(gp);
        this.playerInputHandler = handler;
        this.screenX = gp.getScreenWidth() / 2 - (gp.getTileSize() / 2);
        this.screenY = gp.getScreenHeight() / 2 - (gp.getTileSize() / 2);

        initPlayer(gp);
    }

    private void initPlayer(GamePanel gp) {
        setSpeed(4);
        setSpriteNum(1);
        setDefaultPosition();
        restoreLife();

        SpriteMaker maker = new SpriteMaker(gp);
        setSpriteFrames(Direction.UP,
                maker.characterSkinSetup("/Player/boy_up_1"),
                maker.characterSkinSetup("/Player/boy_up_2"));
        setSpriteFrames(Direction.DOWN,
                maker.characterSkinSetup("/Player/boy_down_1"),
                maker.characterSkinSetup("/Player/boy_down_2"));
        setSpriteFrames(Direction.LEFT,
                maker.characterSkinSetup("/Player/boy_left_1"),
                maker.characterSkinSetup("/Player/boy_left_2"));
        setSpriteFrames(Direction.RIGHT,
                maker.characterSkinSetup("/Player/boy_right_1"),
                maker.characterSkinSetup("/Player/boy_right_2"));

        setDirection(Direction.DOWN);
    }

    public void setDefaultPosition() {
        int tileSize = getGp().getTileSize();
        setWorldX(12 * tileSize);
        setWorldY(12 * tileSize);
    }

    public void restoreLife() {
        setMaxHealth(6);
        setHealth(getMaxHealth());
        invincible = false;
        invincibleCounter = 0;
    }

    @Override
    public void update() {
        checkGameOver();
        handleInput();
        updateInvincibility();
        updateTrail();
    }

    @Override
    public void draw(Graphics2D g2) {
        BufferedImage img = getCurrentSprite();
        g2.drawImage(img, screenX, screenY, null);
    }

    @Override
    protected Point calculateScreenPosition() {
        return new Point(screenX, screenY);
    }

    @Override
    protected boolean isOnScreen() {
        return true;
    }

    private void handleInput() {
        if (!playerInputHandler.isMovementKeyPressed()) return;
        if (playerInputHandler.isUpPressed())    setDirection(Direction.UP);
        else if (playerInputHandler.isDownPressed())  setDirection(Direction.DOWN);
        else if (playerInputHandler.isLeftPressed())  setDirection(Direction.LEFT);
        else if (playerInputHandler.isRightPressed()) setDirection(Direction.RIGHT);

        animate(10);
        movePlayer();
    }

    private void movePlayer() {
        setCollisionOn(false);
        getGp().getCollisionChecker().checkTile(this);
        getGp().getCollisionChecker().checkObject(this, true);

        List<Enemy> enemies = gp.getEntitySetter().getEnemies();
        getGp().getCollisionChecker().checkEntity(this, enemies);

        if (!isCollisionOn()) {
            switch (getDirection()) {
                case UP    -> setWorldY(getWorldY() - getSpeed());
                case DOWN  -> setWorldY(getWorldY() + getSpeed());
                case LEFT  -> setWorldX(getWorldX() - getSpeed());
                case RIGHT -> setWorldX(getWorldX() + getSpeed());
            }
        }
    }

    private void updateInvincibility() {
        if (!invincible) return;
        if (++invincibleCounter > 60) {
            invincible = false;
            invincibleCounter = 0;
        }
    }

    private void updateTrail() {
        Point pos = new Point(getWorldX(), getWorldY());
        if (trail.isEmpty() || !trail.getLast().equals(pos)) {
            trail.add(pos);
            if (trail.size() > 50) trail.removeFirst();
        }
    }

    private void checkGameOver() {
        if (getHealth() > 0) return;
        getGp().getHud().setGameFinished(true);
        getGp().getGameStateManager().setGameOver();
        getGp().getAudioManager().playSE("Game Over");
    }

    // ─── Getters / PowerUp API ───────────────────────────────────────────────────

    public void addPowerUp(PowerUp pu)    { powerUps.add(pu); }
    public List<PowerUp> getPowerUps()    { return powerUps; }
    public LinkedList<Point> getTrail()   { return trail; }
    public int getCollectedFragments()    { return collectedFragments; }
    public boolean usePowerUp(String type) {
        return powerUps.removeIf(pu -> {
            if (pu.getType().equals(type)) {
                pu.apply();
                return true;
            }
            return false;
        });
    }
    public boolean isInvincible()         { return !invincible; }
    public void setInvincible(boolean inv){ this.invincible = inv; }
    public int getScreenX()               { return screenX; }
    public int getScreenY()               { return screenY; }

    public void setCollectedFragments(int collectedFragments) {
        this.collectedFragments = collectedFragments;
    }
}