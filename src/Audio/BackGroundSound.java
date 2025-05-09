package Audio;

import java.io.IOException;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class BackGroundSound extends Sound {
    private static final Logger LOGGER = Logger.getLogger(BackGroundSound.class.getName());

    public BackGroundSound() {
        loadFromDirectory("res/sound/music");
    }

    public void setFile(int trackIndex) {
        URL url = getClipUrls().get("track" + trackIndex);
        if (url != null) {
            loadClip(url);
        }
    }

    public void loadFromDirectory(String directoryPath) {
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
}
