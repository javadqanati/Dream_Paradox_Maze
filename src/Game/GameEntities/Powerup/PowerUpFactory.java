package Game.GameEntities.Powerup;

import Launcher.GamePanel;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class PowerUpFactory {
    private static final Map<String, Function<GamePanel, PowerUp>> registry = new HashMap<>();

    public static void register(String type, Function<GamePanel, PowerUp> ctor) {
        registry.put(type, ctor);
    }

    public static PowerUp create(Object type, GamePanel gp) {
        String key = (type instanceof Enum) ? ((Enum<?>) type).name() : type.toString();
        Function<GamePanel, PowerUp> ctor = registry.get(key);
        if (ctor == null) {
            throw new IllegalArgumentException("Unknown PowerUp type: " + key);
        }
        return ctor.apply(gp);
    }
}
