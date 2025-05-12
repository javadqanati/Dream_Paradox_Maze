package Audio;

import java.net.URL;
import java.util.logging.Logger;

public final class BackGroundSound extends Sound {
    private static final Logger LOGGER = Logger.getLogger(BackGroundSound.class.getName());

    public BackGroundSound() {
        super("res/sound/music");
    }

    public void setFile(int trackIndex) {
        URL url = getClipUrls().get("track" + trackIndex);
        if (url != null) {
            loadClip(url);
        }
    }
}
