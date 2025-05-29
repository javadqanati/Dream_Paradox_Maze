package Game.GameEntities.Powerup;

import Game.GameEntities.Interactable;
import Game.GameEntities.Player;
import Launcher.GamePanel;
import java.awt.image.BufferedImage;

public final class SpeedBoost extends PowerUp implements TimedPowerUp, Interactable {
    private static final int DURATION = 10_000;

    public SpeedBoost(GamePanel gp) {
        super(gp);
        setName("Speed Boost");
        PowerUpFactory.register(getName(), SpeedBoost::new);
        getImages();
        initialize("SpeedBoost", 2);
    }

    @Override
    public void getImages() {
        BufferedImage sprite = getMaker().objectImageSetup("/Object/speed");

        if (sprite != null) {
            setSpriteFrames(getDirection(), sprite);
        }
    }

    @Override
    public void onPlayerInteract(Player player) {
        player.addPowerUp(createNewInstance());
        getGp().getAudioManager().playSE("Memory Fragment");
        player.getGp().getEntityManager().getEntities().remove(this);
        apply();
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
