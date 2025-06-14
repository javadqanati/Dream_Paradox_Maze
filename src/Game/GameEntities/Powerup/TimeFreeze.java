package Game.GameEntities.Powerup;

import Game.GameEntities.Interactable;
import Game.GameEntities.Player;
import Launcher.GamePanel;
import UI.PlayScreen;
import UI.Screen;
import java.awt.image.BufferedImage;

public final class TimeFreeze extends PowerUp implements TimedPowerUp, Interactable {
    private static final int DURATION = 10_000;

    public TimeFreeze(GamePanel gp) {
        super(gp);
        setName("Time Freeze");
        getImages();
        initialize("TimeFreeze", 3);
        setDescription("Freezes the time for a few seconds.");
    }

    @Override
    public void getImages() {
        BufferedImage sprite = getMaker().objectImageSetup("/Object/time");

        if (sprite != null) {
            setSpriteFrames(getDirection(), sprite);
        }
    }

    @Override
    public void apply() {
        applyTimed();
        System.out.println("apply function");
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

    @Override
    public void onPlayerInteract(Player player) {
        player.addPowerUp(createNewInstance());
        getGp().getAudioManager().playSE("Memory Fragment");
        player.getGp().getEntityManager().getEntities().remove(this);
        apply();
    }
}
