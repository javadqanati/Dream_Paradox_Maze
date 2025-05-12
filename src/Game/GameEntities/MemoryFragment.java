package Game.GameEntities;

import Launcher.GamePanel;
import java.awt.image.BufferedImage;

public final class MemoryFragment extends Entity implements Interactable{

    public MemoryFragment(GamePanel gp) {
        super(gp);
        setName("Memory Fragment");
        getImages();
    }

    @Override
    public void getImages() {
        BufferedImage sprite = getMaker().objectImageSetup("/Object/Magic");

        if (sprite != null) {
            setSpriteFrames(getDirection(), sprite);
        }
    }

    @Override
    public void onPlayerInteract(Player player) {
        getGp().getAudioManager().playSE("Memory Fragment");
        Player.setCollectedFragments(player.getCollectedFragments() + 1);
        player.getGp().getEntityManager().getEntities().remove(this);
    }
}
