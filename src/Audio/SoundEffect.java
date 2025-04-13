package Audio;

public class SoundEffect implements Playable{
    private String name;
    private String filePath;

    public SoundEffect(String filePath, String name) {
        this.filePath = filePath;
        this.name = name;
    }

    @Override
    public void play(){

    }
}
