package Audio;

import javax.sound.sampled.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public abstract class Sound {
    private Clip clip;
    private final Map<String, URL> soundClips = new HashMap<>();

    protected void loadClip(URL url) {
        try {
            if (clip != null && clip.isRunning()) {
                clip.stop();
            }
            AudioInputStream ais = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int clipCount() {
        return soundClips.size();
    }

    public Map<String, URL> getClipUrls() {
        return soundClips;
    }

    public Clip getClip() {
        return clip;
    }
}
