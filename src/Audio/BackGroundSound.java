package Audio;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class BackGroundSound extends Sound {

    public BackGroundSound() {
        super("res/sound/music");
    }

    public void setFile(int i) {
        try {
            if (i < 0 || i >= getClipUrls().size()) {
                throw new IndexOutOfBoundsException("Invalid sound index: " + i);
            }
            URL url = getClipUrls().get("track" + i);
            AudioInputStream ais = AudioSystem.getAudioInputStream(url);

            if (getClip() != null && getClip().isOpen()) {
                getClip().close();
            }
            Clip newClip = AudioSystem.getClip();
            newClip.open(ais);
            setClip(newClip);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
