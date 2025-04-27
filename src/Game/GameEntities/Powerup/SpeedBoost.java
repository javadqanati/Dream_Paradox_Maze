package Game.GameEntities.Powerup;

import Launcher.GamePanel;
import graphicals.SpriteMaker;
import java.awt.image.BufferedImage;

public class SpeedBoost extends PowerUp {
    private static final String SPRITE_PATH = "/Object/boots";

    public SpeedBoost(GamePanel gp) {
        super(gp);
        setName("Speed Boost");
        PowerUpFactory.register(getName(), SpeedBoost::new);
        SpriteMaker spriteMaker = new SpriteMaker(gp);
        BufferedImage sprite = spriteMaker.objectImageSetup(SPRITE_PATH);

        if (sprite != null) {
            setSpriteFrames(Direction.DOWN, sprite);
        }

        initialize("SpeedBoost", 3);
    }

    @Override
    public void apply() {
        getGp().getPlayer().setSpeed(getGp().getPlayer().getSpeed() + 2);
    }

    @Override
    public PowerUp createNewInstance() {
        return new SpeedBoost(getGp());
    }
}
