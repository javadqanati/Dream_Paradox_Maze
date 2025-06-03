package Game.GameEntities;

import Launcher.GamePanel;
import java.awt.image.BufferedImage;

public final class FakeDoor extends Entity implements Interactable {

    public FakeDoor(GamePanel gp) {
        super(gp);
        setName("FakeDoor");
        setPassable(false);
        getImages();
    }

    @Override
    public void getImages() {
        BufferedImage entranceSprite = getMaker().objectImageSetup("/Object/doorS");

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
