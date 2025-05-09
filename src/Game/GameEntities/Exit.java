package Game.GameEntities;

import Launcher.GamePanel;
import UI.PlayScreen;
import java.awt.image.BufferedImage;

public class Exit extends Entity implements Interactable{

    public Exit(GamePanel gp) {
        super(gp);
        setName("Exit");
        setPassable(false);
        getImages();
    }

    @Override
    public void getImages() {
        BufferedImage exitSprite = getMaker().objectImageSetup("/Object/door");

        if (exitSprite != null) {
            setSpriteFrames(getDirection(), exitSprite);
        }
    }

    @Override
    public void onPlayerInteract(Player player) {
        PlayScreen ps = (PlayScreen) (getGp().getScreenManager().all().get("PLAY"));
        ps.setGameFinished(true);
    }
}
