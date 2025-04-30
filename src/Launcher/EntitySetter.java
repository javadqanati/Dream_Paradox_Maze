package Launcher;

import Game.GameEntities.*;
import Utils.EntityConfig;

import java.util.List;

public class EntitySetter {
    private final GamePanel gp;

    public EntitySetter(GamePanel gp) {
        this.gp = gp;
    }

    public void loadEntities(List<EntityConfig> ents, List<EntityConfig> enms) {

        for (int i = 0; i < ents.size(); i++) {
            EntityConfig cfg = ents.get(i);
            Entity e = EntityFactory.create(cfg.getType(), gp);
            e.setWorldX(cfg.getX() * gp.getTileSize());
            e.setWorldY(cfg.getY() * gp.getTileSize());
            gp.getGameEntities()[i] = e;
        }
        for (int i = 0; i < enms.size(); i++) {
            EntityConfig cfg = enms.get(i);
            Entity e = EntityFactory.create(cfg.getType(), gp);
            e.setWorldX(cfg.getX() * gp.getTileSize());
            e.setWorldY(cfg.getY() * gp.getTileSize());
            gp.getEnemies()[i] = (Enemy) e;
        }
    }
}
