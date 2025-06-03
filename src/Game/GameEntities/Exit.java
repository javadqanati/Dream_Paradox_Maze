package Game.GameEntities;

import Launcher.GamePanel;
import UI.GameFinishable;
import UI.Screen;

import java.awt.image.BufferedImage;

public final class Exit extends Entity implements Interactable{

    public Exit(GamePanel gp) {
        super(gp);
        setName("Exit");
        setPassable(false);
        getImages();
    }

    @Override
    public void getImages() {
        BufferedImage exitSprite = getMaker().objectImageSetup("/Object/doorS");

        if (exitSprite != null) {
            setSpriteFrames(getDirection(), exitSprite);
        }
    }

    @Override
    public void onPlayerInteract(Player player) {
        Screen screen = getGp().getScreenManager().all().get("PLAY");

        if (screen instanceof GameFinishable finishable) {
            finishable.setGameFinished(true);
        }
    }
}
