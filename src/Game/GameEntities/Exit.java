package Game.GameEntities;

import Audio.SoundEffect;
import Launcher.GamePanel;
import UI.PlayScreen;
import graphicals.SpriteMaker;
import java.awt.image.BufferedImage;

public class Exit extends Entity implements Interactable{
    private static final String SPRITE_PATH = "/Object/door";

    public Exit(GamePanel gp) {
        super(gp);
        setName("Exit");
        setPassable(false);

        SpriteMaker spriteMaker = new SpriteMaker(gp);
        BufferedImage exitSprite = spriteMaker.objectImageSetup(SPRITE_PATH);

        if (exitSprite != null) {
            setSpriteFrames(Direction.DOWN, exitSprite);
        }
    }

    @Override
    public void onPlayerInteract(Player player) {
        getGp().getAudioManager().playSE(SoundEffect.MEMORY_FRAGMENT);
        PlayScreen ps = (PlayScreen) (gp.getScreenManager().all().get("PLAY"));
        ps.setGameFinished(true);
    }
}
