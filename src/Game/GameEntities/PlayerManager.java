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
        initPlayer();
    }

    protected void initPlayer() {
        player.setSpeed(4);
        player.setSpriteNum(1);
        player.setDefaultPosition();
        player.restoreLife();
        player.getImages();
        player.setDirection(Entity.DOWN());
    }

    public Player getPlayer() {
        return player;
    }

    public void resetPlayer() {
        player.setDefaultPosition();
        player.restoreLife();
        player.getPowerUps().clear();
        Player.setCollectedFragments(9);
        dataManager.loadConfig();
    }

    public void savePlayer() {
        dataManager.saveConfig();
    }
}
