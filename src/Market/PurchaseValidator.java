package Market;

import Game.GameEntities.Player;
import Game.GameEntities.Powerup.PowerUp;

public class PurchaseValidator {

    public boolean canPurchase(Player player, PowerUp powerUp){
        return player.getCollectedFragments() >= powerUp.getCost();
    }
}
