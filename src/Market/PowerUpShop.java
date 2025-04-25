package Market;

import Game.GameEntities.ExtraLife;
import Game.GameEntities.PowerUp;
import Game.GameEntities.SpeedBoost;
import Game.GameEntities.TimeFreeze;
import Launcher.GamePanel;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class PowerUpShop {
    private static final Map<String, PowerUp> availablePowerUps = new LinkedHashMap<>();

    public PowerUpShop(GamePanel gp) {
        availablePowerUps.put("SpeedBoost", new SpeedBoost(gp));
        availablePowerUps.put("TimeFreeze", new TimeFreeze(gp));
        availablePowerUps.put("ExtraLife", new ExtraLife(gp));
    }

    public static Map<String, PowerUp> getAvailablePowerUps() {
        return availablePowerUps;
    }

    public void addPowerUp(PowerUp powerUp) {
        availablePowerUps.put(powerUp.getClass().getSimpleName(), powerUp);
    }
}
