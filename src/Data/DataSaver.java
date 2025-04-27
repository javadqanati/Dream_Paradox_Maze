package Data;

import Launcher.GamePanel;

public class DataSaver {
    private final GamePanel gp;

    public DataSaver(GamePanel gp) {
        this.gp = gp;
    }
    public void saveData(){
        gp.getPlayerManager().savePlayer();
        gp.getConfig().saveConfig();
    }
}
