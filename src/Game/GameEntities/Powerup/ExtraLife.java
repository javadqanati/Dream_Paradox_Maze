package Game.GameEntities.Powerup;

import Audio.SoundEffect;
import Game.GameEntities.Interactable;
import Game.GameEntities.Player;
import Launcher.GamePanel;
import graphicals.SpriteMaker;
import java.awt.image.BufferedImage;

public class ExtraLife extends PowerUp implements Interactable {
    private static final String SPRITE_PATH = "/Object/heart_full";

    public ExtraLife(GamePanel gp) {
        super(gp);
        setName("Extra Life");
        PowerUpFactory.register(getName(), ExtraLife::new);
        SpriteMaker spriteMaker = new SpriteMaker(gp);
        BufferedImage sprite = spriteMaker.objectImageSetup(SPRITE_PATH);

        if (sprite != null) {
            setSpriteFrames(Direction.DOWN, sprite);
        }

        initialize("ExtraLife", 3);
    }

    @Override
    public void apply() {
        Player p = getGp().getPlayer();
        if (p.getHealth() == p.getMaxHealth()) {
            p.setMaxHealth(p.getMaxHealth() + 2);
        }
        if (p.getHealth() < p.getMaxHealth()) {
            p.setHealth(p.getHealth() + 2);
        }
    }

    @Override
    public void onPlayerInteract(Player player) {
        player.addPowerUp(createNewInstance());
        getGp().getAudioManager().playSE(SoundEffect.MEMORY_FRAGMENT);
        player.getGp().getEntitySetter().getEntities().remove(this);
    }

    @Override
    public PowerUp createNewInstance() {
        return new ExtraLife(getGp());
    }
}
