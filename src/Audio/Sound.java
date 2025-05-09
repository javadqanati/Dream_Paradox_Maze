package Audio;

import javax.sound.sampled.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Sound {
    private Clip clip;
    private final Map<String, URL> soundClips = new HashMap<>();
    private static final Logger LOGGER = Logger.getLogger(Sound.class.getName());

    protected void loadClip(URL url) {
        try {
            if (clip != null && clip.isRunning()) {
                clip.stop();
            }
            AudioInputStream ais = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to load audio clip from URL: " + url, e);
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
