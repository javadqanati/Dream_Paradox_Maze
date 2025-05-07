package Audio;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.net.URL;

public class SoundEffect extends Sound {

    public SoundEffect() {
        super();

        setSoundEffect("Memory Fragment", "memory_fragment");
        setSoundEffect("Cursor", "cursor");
        setSoundEffect("Next Level", "levelup");
        setSoundEffect("Game Over", "gameover");
    }

    public void setSoundEffect(String name, String path) {
        getClipUrls().put(name, getClass().getResource("/sound/sfx/" + path + ".wav"));
        System.out.println(getClipUrls().get(name)+ "has been set");
    }

    public void setEffect(String name) {
        try {
            URL url = getClipUrls().get(name);
            if (url == null) throw new IllegalArgumentException("Sound not found: " + name);

            AudioInputStream ais = AudioSystem.getAudioInputStream(url);
            AudioSystem.getClip().open(ais);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
