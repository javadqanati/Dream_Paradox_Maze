package Audio;

public class MusicTrack implements Playable{
    private String name;
    private String filePath;
    private boolean loop;

    public MusicTrack(String filePath, boolean loop, String name) {
        this.filePath = filePath;
        this.loop = loop;
        this.name = name;
    }

    @Override
    public void play(){

    }

    public void stop(){

    }
}
