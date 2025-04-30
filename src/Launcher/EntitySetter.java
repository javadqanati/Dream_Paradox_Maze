package Launcher;

import Game.GameEntities.*;
import Utils.EntityConfig;
import java.util.ArrayList;
import java.util.List;

public class EntitySetter {
    private final GamePanel gp;
    private final List<Entity> entities = new ArrayList<>();
    private final List<Enemy>  enemies  = new ArrayList<>();

    public EntitySetter(GamePanel gp) {
        this.gp = gp;
    }

    public void loadEntities(List<EntityConfig> ents, List<EntityConfig> enms) {
        entities.clear();
        enemies.clear();

        for (EntityConfig cfg : ents) {
            Entity e = EntityFactory.create(cfg.getType(), gp);
            e.setWorldX(cfg.getX() * gp.getTileSize());
            e.setWorldY(cfg.getY() * gp.getTileSize());
            entities.add(e);
        }
        for (EntityConfig cfg : enms) {
            Enemy e = (Enemy) EntityFactory.create(cfg.getType(), gp);
            e.setWorldX(cfg.getX() * gp.getTileSize());
            e.setWorldY(cfg.getY() * gp.getTileSize());
            enemies.add(e);
        }
    }

    public List<Entity> getEntities() {
        return entities;
    }
    public List<Enemy> getEnemies() {
        return enemies;
    }
}
