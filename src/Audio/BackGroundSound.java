package Audio;

public class BackGroundSound extends Sound{

    public BackGroundSound() {
        getUrl()[0] = getClass().getResource("/sound/BlueBoyAdventure.wav");
    }
}
