package Game.GameEntities;

import Launcher.GamePanel;
import graphicals.SpriteMaker;
import java.awt.image.BufferedImage;

public class Exit extends Entity {
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
}
