package Data;

import java.util.List;

public class SaveManager {
    private List<SaveFile> saveFiles;
    private SaveFile currentSave;

    public void saveGame(){}
    public SaveFile loadGame(){return (SaveFile) List.of();
    }

    public void deleteSave(String name){

    }

    public List<SaveFile> getSaveFiles() {
        return saveFiles;
    }

    public SaveFile getCurrentSave() {
        return currentSave;
    }

    public void setCurrentSave(SaveFile currentSave) {
        this.currentSave = currentSave;
    }
}
