package Audio;

import java.net.MalformedURLException;
import java.nio.file.Paths;

public class SoundEffect extends Sound {
    public static final int MEMORY_FRAGMENT = 0;

    public SoundEffect() {
        super();
        try {
            getClipUrls().add(Paths.get("res/sound/sfx/memory_fragment.wav")
                    .toUri().toURL());
            getClipUrls().add(Paths.get("res/sound/sfx/cursor.wav")
                    .toUri().toURL());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
