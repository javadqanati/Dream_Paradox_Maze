package Game.GameEntities;

import java.util.List;

public class EnemyManager {
    private List<Enemy> enemies;

    public EnemyManager(List<Enemy> enemies) {
        this.enemies = enemies;
    }

    public void addEnemy(Enemy enemy){

    }

    public void removeEnemy(Enemy enemy){

    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public void updateEnemies(){}
}
