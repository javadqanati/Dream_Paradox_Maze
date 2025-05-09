package Trade;

import Game.GameEntities.Player;
import Game.GameEntities.Powerup.PowerUp;

public class TransactionHandler {

    public void processPurchase(Player player, PowerUp powerUp){
        Player.setCollectedFragments(player.getCollectedFragments() - powerUp.getCost());
        player.addPowerUp(powerUp.createNewInstance());
    }

    public boolean canPurchase(Player player, PowerUp powerUp){
        return player.getCollectedFragments() >= powerUp.getCost();
    }
}
