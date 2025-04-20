package Audio;

public class AudioManager {
    private final static Sound music = new BackGroundSound();
    private final static Sound sfx = new SoundEffect();

    private static boolean musicMuted = false;
    private static boolean sfxMuted = false;

    public void toggleMusicMute() {
        musicMuted = !musicMuted;
        if (musicMuted) {
            music.stop();
        } else {
            music.play();
            music.loop();
        }
    }

    public void toggleSfxMute() {
        sfxMuted = !sfxMuted;
    }

    public void playMusic(int i) {
        if (!musicMuted) {
            music.setFile(i);
            music.play();
            music.loop();
        }
    }

    public void stopMusic() {
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
