package Game.GameEntities;

import Launcher.GamePanel;
import java.awt.image.BufferedImage;

public final class Entrance extends Entity implements Interactable {

    public Entrance(GamePanel gp) {
        super(gp);
        setName("Entrance");
        setPassable(false);
        getImages();
    }

    @Override
    public void getImages() {
        BufferedImage entranceSprite = getMaker().objectImageSetup("/Object/door");

        if (entranceSprite != null) {
            setSpriteFrames(getDirection(), entranceSprite);
        }
    }

    @Override
    public void onPlayerInteract(Player player) {
        getGp().getAudioManager().playSE("Memory Fragment");
        player.getGp().getEntityManager().getEntities().remove(this);
    }
}
