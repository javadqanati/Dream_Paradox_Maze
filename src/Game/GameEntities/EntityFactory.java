package Game.GameEntities;

import Game.GameEntities.Powerup.*;
import Launcher.GamePanel;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class EntityFactory {
    private static final Map<String, Function<GamePanel, Entity>> registry = new HashMap<>();

    static {
        register("Entrance", Entrance::new);
        register("Exit", Exit::new);
        register("MemoryFragment", MemoryFragment::new);
        register("SpeedBoost", SpeedBoost::new);
        register("ExtraLife", ExtraLife::new);
        register("TimeFreeze", TimeFreeze::new);
        register("ChaserEnemy", ChaserEnemy::new);
        register("ShooterEnemy", ShooterEnemy::new);
    }

    private static void register(String type, Function<GamePanel, Entity> creator) {
        registry.put(type, creator);
    }

    public static Entity create(String type, GamePanel gp) {
        Function<GamePanel, Entity> creator = registry.get(type);
        if (creator == null) {
            throw new IllegalArgumentException("Unknown entity type: " + type);
        }
        return creator.apply(gp);
    }
}
