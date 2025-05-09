package Game.GameEntities.Powerup;

import Game.GameEntities.Interactable;
import Game.GameEntities.Player;
import Launcher.GamePanel;
import java.awt.image.BufferedImage;

public final class ExtraLife extends PowerUp implements Interactable {

    public ExtraLife(GamePanel gp) {
        super(gp);
        setName("Extra Life");
        PowerUpFactory.register(getName(), ExtraLife::new);
        getImages();
        initialize("ExtraLife", 3);
    }

    @Override
    public void getImages() {
        BufferedImage sprite = getMaker().objectImageSetup("/Object/heart_full");

        if (sprite != null) {
            setSpriteFrames(getDirection(), sprite);
        }
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
        getGp().getAudioManager().playSE("Memory Fragment");
        player.getGp().getEntitySetter().getEntities().remove(this);
    }

    @Override
    public PowerUp createNewInstance() {
        return new ExtraLife(getGp());
    }
}
