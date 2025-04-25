package Market;

import Game.GameEntities.Player;
import Game.GameEntities.PowerUp;

public class TransactionHandler {

    public void processPurchase(Player player, PowerUp powerUp){
        player.setCollectedFragments(player.getCollectedFragments() - powerUp.getCost());
        player.addPowerUp(powerUp.createNewInstance());
    }
}
