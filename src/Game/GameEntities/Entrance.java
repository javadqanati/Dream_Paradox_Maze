package Game.GameEntities;

import Launcher.GamePanel;
import java.awt.image.BufferedImage;

public class Entrance extends Entity implements Interactable {

    public Entrance(GamePanel gp) {
        super(gp);
        setName("Entrance");
        setPassable(false);

    }

    @Override
    public void getImages() {
        BufferedImage entranceSprite = getMaker().objectImageSetup("/Object/door");

        if (entranceSprite != null) {
            setSpriteFrames(Direction.DOWN, entranceSprite);
        }
    }

    @Override
    public void onPlayerInteract(Player player) {
        getGp().getAudioManager().playSE("Memory Fragment");
        player.getGp().getEntitySetter().getEntities().remove(this);
    }
}
