package Game.GameEntities.Powerup;

import Audio.SoundEffect;
import Game.GameEntities.Interactable;
import Game.GameEntities.Player;
import Launcher.GamePanel;
import graphicals.SpriteMaker;
import java.awt.image.BufferedImage;

public class SpeedBoost extends PowerUp implements TimedPowerUp, Interactable {
    private static final String SPRITE_PATH = "/Object/speed";
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
    public void onPlayerInteract(Player player) {
        player.addPowerUp(createNewInstance());
        getGp().getAudioManager().playSE(SoundEffect.MEMORY_FRAGMENT);
        player.getGp().getEntitySetter().getEntities().remove(this);
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
