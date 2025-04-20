package Audio;

import javax.sound.sampled.Clip;

public class AudioManager {
    private final static Sound sound = new BackGroundSound();
    private final static Sound effect = new SoundEffect();

    public void setVolume(float level){}

    public void mute(){}

    public void unmute(){}

    public void playMusic(int i) {
        sound.setFile(i);
        sound.play();
        sound.loop();
    }

    public void stopMusic(){
        sound.stop();
    }

    public void playSE(int i){
        effect.setFile(i);
        effect.play();
    }

}
