package Data;
public class Setting {
    private int soundVolume;
    private int musicVolume;
    private boolean setFullScreen;

    public Setting(int musicVolume, boolean setFullScreen, int soundVolume) {
        this.musicVolume = musicVolume;
        this.setFullScreen = setFullScreen;
        this.soundVolume = soundVolume;
    }

    public int getMusicVolume() {
        return musicVolume;
    }

    public void setMusicVolume(int musicVolume) {
        this.musicVolume = musicVolume;
    }

    public boolean isSetFullScreen() {
        return setFullScreen;
    }

    public void setSetFullScreen(boolean setFullScreen) {
        this.setFullScreen = setFullScreen;
    }

    public int getSoundVolume() {
        return soundVolume;
    }

    public void setSoundVolume(int soundVolume) {
        this.soundVolume = soundVolume;
    }
}
