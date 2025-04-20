package Audio;

public class SoundEffect extends Sound{

    public SoundEffect() {
        getUrl()[1] = getClass().getResource("/sound/coin.wav");
        getUrl()[2] = getClass().getResource("/sound/powerup.wav");
        getUrl()[3] = getClass().getResource("/sound/unlock.wav");
        getUrl()[4] = getClass().getResource("/sound/fanfare.wav");
    }
}
