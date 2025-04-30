package Trade;

import Game.GameEntities.Powerup.ExtraLife;
import Game.GameEntities.Powerup.PowerUp;
import Game.GameEntities.Powerup.SpeedBoost;
import Game.GameEntities.Powerup.TimeFreeze;
import Launcher.GamePanel;

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
