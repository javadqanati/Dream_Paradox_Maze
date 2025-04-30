package Game.GameEntities.Powerup;

import Launcher.GamePanel;
import UI.PlayScreen;
import UI.Screen;
import graphicals.SpriteMaker;
import java.awt.image.BufferedImage;

public class TimeFreeze extends PowerUp implements TimedPowerUp {
    private static final String SPRITE_PATH = "/Object/boots";
    private static final int DURATION = 10_000;

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
        applyTimed();
    }


    @Override
    public PowerUp createNewInstance() {
        return new TimeFreeze(getGp());
    }

    @Override
    public int getDurationMs() {
        return DURATION;
    }

    @Override
    public void onStart() {
        Screen s = getGp().getScreens().get("PLAY");
        if (s instanceof PlayScreen ps)
            ps.getTimer().freezeTime();
    }

    @Override
    public void onExpire() {
        Screen s = getGp().getScreens().get("PLAY");
        if (s instanceof PlayScreen ps)
            ps.getTimer().unfreezeTime();
    }
}
