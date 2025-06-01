package Game.Level;

import Utils.EntityConfig;

import java.util.List;

public record Level(String mapFile, List<EntityConfig> entities, List<EntityConfig> enemies) {
}