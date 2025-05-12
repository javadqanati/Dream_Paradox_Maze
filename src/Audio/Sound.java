package Audio;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Sound {
    private Clip clip;
    private final Map<String, URL> soundClips = new HashMap<>();
    private static final Logger LOGGER = Logger.getLogger(Sound.class.getName());

    public Sound() {
        
    }

    public Sound(String directoryPath) {
        try {
            Path soundDir = Paths.get(directoryPath);
            if (!Files.exists(soundDir))
                throw new IllegalArgumentException("Missing folder: " + directoryPath);

            try (DirectoryStream<Path> stream = Files.newDirectoryStream(soundDir, "*.{wav,mp3,aiff}")) {
                int i = 0;
                for (Path file : stream) {
                    getClipUrls().put("track" + i, file.toUri().toURL());
                    i++;
                }
                System.out.println("Loaded " + i + " music tracks");
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed loading audio from " + directoryPath, e);
        }
    }

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
