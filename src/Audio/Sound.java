package Audio;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;

public abstract class Sound {
    private Clip clip;
    private static final Map<String, URL> soundClips = new HashMap<>();

    public Sound(String directoryPath) {
        loadFromDirectory(directoryPath);
    }

    public Sound() {

    }

    public void loadFromDirectory(String directoryPath) {
        try {
            Path soundDir = Paths.get(directoryPath);
            if (!Files.exists(soundDir))
                throw new IllegalArgumentException("Missing folder: " + directoryPath);

            try (DirectoryStream<Path> stream =
                         Files.newDirectoryStream(soundDir, "*.{wav,mp3,aiff}")) {
                int i = 0;
                for (Path file : stream) {
                    soundClips.put("track" + i, file.toUri().toURL());
                    i++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if (clip != null) clip.start();
    }

    public void loop() {
        if (clip != null) clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        if (clip != null) clip.stop();
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
