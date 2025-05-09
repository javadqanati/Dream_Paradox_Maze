package Audio;

import java.net.URL;

public final class SoundEffect extends Sound {

    public SoundEffect() {
        super();

        setSoundEffect("Memory Fragment", "memory_fragment");
        setSoundEffect("Cursor", "cursor");
        setSoundEffect("Next Level", "level_up");
        setSoundEffect("Game Over", "game_over");
    }

    public void setSoundEffect(String name, String path) {
        getClipUrls().put(name, getClass().getResource("/sound/sfx/" + path + ".wav"));
        System.out.println(getClipUrls().get(name)+ "has been set");
    }

    public void setEffect(String name) {
        URL url = getClipUrls().get(name);
        if (url != null) {
            loadClip(url);
        }
    }

    public void play() {
        if (getClip() != null) {
            getClip().stop();
            getClip().setFramePosition(0);
            getClip().start();
        }
    }
}
