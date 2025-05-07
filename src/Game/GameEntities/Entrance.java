package Game.GameEntities;

import Audio.SoundEffect;
import Launcher.GamePanel;
import graphicals.SpriteMaker;
import java.awt.image.BufferedImage;

public class Entrance extends Entity implements Interactable {
    private static final String SPRITE_PATH = "/Object/door";

    public Entrance(GamePanel gp) {
        super(gp);
        setName("Entrance");
        setPassable(false);

        SpriteMaker spriteMaker = new SpriteMaker(gp);
        BufferedImage entranceSprite = spriteMaker.objectImageSetup(SPRITE_PATH);

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
