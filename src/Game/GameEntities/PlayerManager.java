// Game.GameEntities.PlayerManager.java
package Game.GameEntities;

import Data.PlayerData;
import Launcher.GamePanel;

public class PlayerManager {
    private final Player player;
    private final PlayerData dataManager;

    public PlayerManager(GamePanel gp) {
        this.player = new Player(gp, gp.getPlayerInputHandler());
        this.dataManager = new PlayerData(player);
        dataManager.loadConfig();
    }

    public Player getPlayer() {
        return player;
    }

    public void resetPlayer() {
        player.setDefaultPosition();
        player.restoreLife();
        player.getPowerUps().clear();
        dataManager.loadConfig();
    }

    public void savePlayer() {
        dataManager.saveConfig();
    }
}
