package Data;

import java.util.List;

public class DataSerializer {
    public String serialize(SaveFile saveFile){return "";}

    public SaveFile deserialize(String data){return (SaveFile) List.of();}

    public void saveToFile(String path, SaveFile saveFile){}

    public SaveFile loadFromFile(String path){return (SaveFile) List.of();}
}
