package Launcher;

import Game.GameEntities.Entity;
import Game.GameEntities.Enemy;

import java.util.ArrayList;
import java.util.List;

public class EntityManager {
    private final List<Entity> entities = new ArrayList<>();
    private final List<Enemy> enemies = new ArrayList<>();

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public void clearEntities() {
        entities.clear();
    }

    public void clearEnemies() {
        enemies.clear();
    }
}