package Game.GameEntities.Powerup;

import Launcher.GamePanel;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

public class PowerUpFactory {
    private static final Map<String, Function<GamePanel, ? extends PowerUp>> powerUps = new LinkedHashMap<>();

    static {
        register("ExtraLife", ExtraLife::new);
        register("SpeedBoost", SpeedBoost::new);
        register("TimeFreeze", TimeFreeze::new);
    }

    public static <T extends PowerUp> void register(String name, Function<GamePanel, T> constructor) {
        powerUps.put(name, constructor);
    }

    @SuppressWarnings("unchecked")
    public static <T extends PowerUp> T create(String type, GamePanel gp) {
        Function<GamePanel, ? extends PowerUp> ctor = powerUps.get(type);
        if (ctor == null) {
            throw new IllegalArgumentException("Unknown PowerUp type: " + type);
        }
        return (T) ctor.apply(gp);
    }

    public static Map<String, Function<GamePanel, ? extends PowerUp>> getAvailablePowerUpNames() {
        return powerUps;
    }
}

