package Game.GameEntities.Powerup;

import Launcher.GamePanel;
import graphicals.SpriteMaker;
import java.awt.image.BufferedImage;

public class SpeedBoost extends PowerUp implements TimedPowerUp {
    private static final String SPRITE_PATH = "/Object/boots";
    private static final int DURATION = 10_000;

    public SpeedBoost(GamePanel gp) {
        super(gp);
        setName("Speed Boost");
        PowerUpFactory.register(getName(), SpeedBoost::new);
        SpriteMaker spriteMaker = new SpriteMaker(gp);
        BufferedImage sprite = spriteMaker.objectImageSetup(SPRITE_PATH);

        if (sprite != null) {
            setSpriteFrames(Direction.DOWN, sprite);
        }

        initialize("SpeedBoost", 3);
    }

    @Override
    public void apply() {
        applyTimed();
    }

    @Override
    public PowerUp createNewInstance() {
        return new SpeedBoost(getGp());
    }

    @Override
    public int getDurationMs() {
        return DURATION;
    }

    @Override
    public void onStart() {
        getGp().getPlayer().setSpeed(getGp().getPlayer().getSpeed() + 2);
    }

    @Override
    public void onExpire() {
        getGp().getPlayer().setSpeed(getGp().getPlayer().getSpeed() - 2);
    }
}
