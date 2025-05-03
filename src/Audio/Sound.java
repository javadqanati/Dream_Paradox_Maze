package Audio;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class Sound {
    private Clip clip;
    private final List<URL> clipUrls = new ArrayList<>();

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
                for (Path file : stream) {
                    clipUrls.add(file.toUri().toURL());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setFile(int i) {
        try {
            if (i < 0 || i >= clipUrls.size()) {
                throw new IndexOutOfBoundsException("Invalid sound index: " + i);
            }
            URL url = clipUrls.get(i);
            AudioInputStream ais = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
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
        return clipUrls.size();
    }

    public List<URL> getClipUrls() {
        return clipUrls;
    }

    public Clip getClip() {
        return clip;
    }
}
