package Audio;

public class AudioEvent {
    private String eventName;
    private String soundName;
    private float volume;

    public AudioEvent(String eventName, String soundName, float volume) {
        this.eventName = eventName;
        this.soundName = soundName;
        this.volume = volume;
    }

    public String getEventName() {
        return eventName;
    }

    public String getSoundName() {
        return soundName;
    }

    public float getVolume() {
        return volume;
    }
}
