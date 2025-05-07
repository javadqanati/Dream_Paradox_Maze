package Game.GameEntities;

import Audio.SoundEffect;
import Launcher.GamePanel;
import graphicals.SpriteMaker;
import java.awt.image.BufferedImage;

public class MemoryFragment extends Entity implements Interactable{
    private static final String SPRITE_PATH = "/Object/Magic";

    public MemoryFragment(GamePanel gp) {
        super(gp);
        setName("Memory Fragment");

        SpriteMaker spriteMaker = new SpriteMaker(gp);
        BufferedImage sprite = spriteMaker.objectImageSetup(SPRITE_PATH);

        if (sprite != null) {
            setSpriteFrames(Direction.DOWN, sprite);
        }
    }

    @Override
    public void onPlayerInteract(Player player) {
        getGp().getAudioManager().playSE("Memory Fragment");
        player.setCollectedFragments(player.getCollectedFragments() + 1);
        player.getGp().getEntitySetter().getEntities().remove(this);
    }
}
