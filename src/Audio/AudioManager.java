package Audio;

public class AudioManager {
    private final static Sound music = new BackGroundSound();
    private final static Sound sfx   = new SoundEffect();

    private static boolean musicMuted = false;
    private static boolean sfxMuted   = false;

    // ← add this field
    private static int currentMusicIndex = 0;

    public void toggleMusicMute() {
        musicMuted = !musicMuted;

        if (musicMuted) {
            music.stop();
        } else {
            // resume whatever index was last played
            playMusic(currentMusicIndex);
        }
    }

    public void toggleSfxMute() {
        sfxMuted = !sfxMuted;
    }

    public void playMusic(int i) {
        // remember the track so toggleMusicMute can restart it
        currentMusicIndex = i;

        music.setFile(i);
        if (!musicMuted) {
            music.play();
            music.loop();
        }
    }

    public void stopMusic(int i) {
        music.setFile(i);
        music.stop();
    }

    public void playSE(int i) {
        if (!sfxMuted) {
            sfx.setFile(i);
            sfx.play();
        }
    }


    public static boolean isMusicMuted() {
        return musicMuted;
    }

    public static void setMusicMuted(boolean musicMuted) {
        AudioManager.musicMuted = musicMuted;
    }

    public static boolean isSfxMuted() {
        return sfxMuted;
    }

    public static void setSfxMuted(boolean sfxMuted) {
        AudioManager.sfxMuted = sfxMuted;
    }
}
