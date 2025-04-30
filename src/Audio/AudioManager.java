package Audio;

public class AudioManager {
    private final static Sound music = new BackGroundSound();
    private final static Sound sfx = new SoundEffect();

    private static boolean musicMuted = false;
    private static boolean sfxMuted = false;

    public void toggleMusicMute() {
        musicMuted = !musicMuted;
    }

    public void toggleSfxMute() {
        sfxMuted = !sfxMuted;
    }

    public void playMusic(int i) {
        music.setFile(i);
        if (!musicMuted) {
            music.play();
            music.loop();
        } else {
            music.stop();
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
