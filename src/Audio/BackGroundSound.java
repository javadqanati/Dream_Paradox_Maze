package Audio;

import java.net.URL;

public final class BackGroundSound extends Sound {

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
