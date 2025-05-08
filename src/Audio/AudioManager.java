package Audio;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

public class AudioManager {
    private final static BackGroundSound music = new BackGroundSound();
    private final static SoundEffect     sfx   = new SoundEffect();
    private static boolean musicMuted = false;
    private static boolean sfxMuted   = false;
    private int currentTrackIndex = 0;
    private LineListener trackEndListener;

    public void toggleMusicMute() {
        musicMuted = !musicMuted;
        System.out.println("musicMuted = " + musicMuted);

        Clip clip = music.getClip();
        if (musicMuted) {
            if (clip != null) clip.stop();
            System.out.println("Music Muted");
        } else {
            playCurrentTrack();
            System.out.println("Music played");
        }
    }

    public void playCurrentTrack() {
        if (music.clipCount() == 0 || musicMuted) return;

        music.setFile(currentTrackIndex);
        Clip clip = music.getClip();
        if (clip == null) return;

        if (trackEndListener != null) {
            try {
                clip.removeLineListener(trackEndListener);
            } catch (Exception ignored) {}
        }

        trackEndListener = event -> {
            if (event.getType() == LineEvent.Type.STOP && !musicMuted) {
                event.getLine().close();
                nextTrack();
            }
        };

        clip.addLineListener(trackEndListener);

        clip.start();
    }

    private void nextTrack() {
        currentTrackIndex = (currentTrackIndex + 1) % music.clipCount();
        playCurrentTrack();
    }

    public void toggleSfxMute() {
        sfxMuted = !sfxMuted;
    }

    public void playSE(String name) {
        if (!sfxMuted) {
            sfx.setEffect(name);
            sfx.play();
        }
    }

    public static boolean isMusicMuted() { return musicMuted; }
    public static boolean isSfxMuted()   { return sfxMuted;   }
    public static void   setMusicMuted(boolean m) { musicMuted = m; }
    public static void   setSfxMuted(boolean s)   { sfxMuted   = s; }
}
