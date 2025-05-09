package Game.GameEntities;

import Game.GameEntities.Powerup.PowerUp;
import Input.PlayerInputHandler;
import Launcher.GamePanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public final class Player extends Character {
    private static final List<PowerUp> powerUps=new ArrayList<>();
    private static final LinkedList<Point> trail=new LinkedList<>();
    private static int collectedFragments;
    private static boolean invincible=false;
    private static int invincibleCounter = 0;
    private final  PlayerInputHandler playerInputHandler;
    private final  int screenX;
    private final  int screenY;

    public Player(GamePanel gp, PlayerInputHandler handler) {
        super(gp);
        this.playerInputHandler = handler;
        this.screenX=gp.getScreenWidth() / 2 - (gp.getTileSize() / 2);
        this.screenY=gp.getScreenHeight() / 2 - (gp.getTileSize() / 2);
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
    public void getImages() {
        loadSprites("/Player/boy_up_1", "/Player/boy_up_2",
                "/Player/boy_left_1", "/Player/boy_left_2",
                "/Player/boy_right_1", "/Player/boy_right_2",
                "/Player/boy_down_1", "/Player/boy_down_2");
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
        if (playerInputHandler.isUpPressed())    setDirection(UP());
        else if (playerInputHandler.isDownPressed())  setDirection(DOWN());
        else if (playerInputHandler.isLeftPressed())  setDirection(LEFT());
        else if (playerInputHandler.isRightPressed()) setDirection(RIGHT());

        animate(10);
        movePlayer();
    }

    private void movePlayer() {
        setCollisionOn(false);
        getGp().getCollisionChecker().checkTile(this);
        getGp().getCollisionChecker().checkObject(this, true);

        List<Enemy> enemies = getGp().getEntitySetter().getEnemies();
        getGp().getCollisionChecker().checkEntity(this, enemies);

        if (!isCollisionOn()) {
            DirectionType dir = getDirection();
            if      (dir == UP())    setWorldY(getWorldY() - getSpeed());
            else if (dir == DOWN())  setWorldY(getWorldY() + getSpeed());
            else if (dir == LEFT())  setWorldX(getWorldX() - getSpeed());
            else if (dir == RIGHT()) setWorldX(getWorldX() + getSpeed());
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
    public static void setInvincible(boolean invincible) {
        Player.invincible = invincible;
    }
    public int getScreenX()               { return screenX; }
    public int getScreenY()               { return screenY; }
    public static void setCollectedFragments(int collectedFragments) {
        Player.collectedFragments = collectedFragments;
    }
}