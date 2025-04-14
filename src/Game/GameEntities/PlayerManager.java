package Game.GameEntities;

import Data.PlayerData;

public class PlayerManager {
    private Player player;

    public PlayerManager(Player player) {
        this.player = player;
    }

    public boolean usePowerUp(PowerUp powerUp) {
        return player.usePowerUp(powerUp);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void loadPlayerData(PlayerData data){}

    public PlayerData generatePlayerData(){
        PlayerData newData = new PlayerData();
        return newData;
    }

    public void respawnPlayer(){}

    public void resetPlayer(){}



}
