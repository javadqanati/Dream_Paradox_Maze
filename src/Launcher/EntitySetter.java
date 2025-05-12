package Launcher;

import Game.GameEntities.Entity;
import Game.GameEntities.Enemy;
import Game.GameEntities.EntityFactory;
import Utils.EntityConfig;

import java.util.List;

public class EntitySetter {
    private final GamePanel gp;
    private final EntityManager entityManager;

    public EntitySetter(GamePanel gp, EntityManager entityManager) {
        this.gp = gp;
        this.entityManager = entityManager;
    }

    public void loadEntities(List<EntityConfig> entities, List<EntityConfig> enemies) {
        entityManager.clearEntities();
        entityManager.clearEnemies();

        for (EntityConfig cfg : entities) {
            Entity e = EntityFactory.create(cfg.getType(), gp);
            e.setWorldX(cfg.getX() * gp.getTileSize());
            e.setWorldY(cfg.getY() * gp.getTileSize());
            entityManager.addEntity(e);
        }
        for (EntityConfig cfg : enemies) {
            Enemy e = (Enemy) EntityFactory.create(cfg.getType(), gp);
            e.setWorldX(cfg.getX() * gp.getTileSize());
            e.setWorldY(cfg.getY() * gp.getTileSize());
            entityManager.addEnemy(e);
        }
    }
}