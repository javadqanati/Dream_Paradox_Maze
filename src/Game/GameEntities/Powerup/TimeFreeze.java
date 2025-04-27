package Game.GameEntities.Powerup;

import Launcher.GamePanel;
import graphicals.SpriteMaker;
import java.awt.image.BufferedImage;

public class TimeFreeze extends PowerUp {
    private static final String SPRITE_PATH = "/Object/boots";

    public TimeFreeze(GamePanel gp) {
        super(gp);
        setName("Time Freeze");
        PowerUpFactory.register(getName(), TimeFreeze::new);
        SpriteMaker spriteMaker = new SpriteMaker(gp);
        BufferedImage sprite = spriteMaker.objectImageSetup(SPRITE_PATH);

        if (sprite != null) {
            setSpriteFrames(Direction.DOWN, sprite);
        }

        initialize("TimeFreeze", 3);
    }

    @Override
    public void apply() {
        // No effect defined yet
    }

    @Override
    public PowerUp createNewInstance() {
        return new TimeFreeze(getGp());
    }
}
