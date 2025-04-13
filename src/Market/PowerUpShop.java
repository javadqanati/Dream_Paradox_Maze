package Market;

import GameEntities.PowerUp;

import java.util.HashMap;
import java.util.Map;

public class PowerUpShop {
    private Map<String, PowerUp> availablePowerUps = new HashMap<>();

    public PowerUpShop(Map<String, PowerUp> availablePowerUps) {
        this.availablePowerUps = availablePowerUps;
    }

    public Map<String, PowerUp> getAvailablePowerUps() {
        return availablePowerUps;
    }

    public void addPowerUp(PowerUp powerUp) {
        availablePowerUps.put(powerUp.getClass().getName(), powerUp);
    }
}
