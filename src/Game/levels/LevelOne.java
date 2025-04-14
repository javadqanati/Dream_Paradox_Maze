package Game.levels;

import Game.GameEntities.*;

import java.util.ArrayList;
import java.util.List;

public class LevelOne implements Level {
    private MazeEntityManager mazeEntityManager;
    private final int levelNumber = 1;

    @Override
    public void load() {
        int width = 5, height = 5;
        Tile[][] grid = new Tile[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[y][x] = new Tile(true, "floor", x, y); // Default all tiles to passable floors
            }
        }

        // Add some walls (example, modify as per level design)
        grid[1][1] = new Tile(false, "wall", 1, 1);
        grid[3][3] = new Tile(false, "wall", 3, 3);

        Maze maze = new Maze(width, grid, height); // Create Maze with grid

        // Create Entrance (starting position at (0,0))
        Entrance entrance = new Entrance();
        EntranceManager entranceManager = new EntranceManager(entrance);
        entranceManager.setEntrancePostition(0, 0); // Set entrance position

        // Create Exit (at (4,4) in this case)
        Exit exit = new Exit();
        ExitManager exitManager = new ExitManager(exit);
        exitManager.setExitPostion(4, 4); // Set exit position

        // Create Player (placed at the entrance)
        Player player = new Player(0, 1, new ArrayList<>()); // No fragments, 1 life
        player.setX(0); // Set player starting position at entrance
        player.setY(0);
        PlayerManager playerManager = new PlayerManager(player);

        // Create List of Enemies (example with 2 enemies)
        List<Enemy> enemies = new ArrayList<>();
        ShooterEnemy shooterEnemy = new ShooterEnemy(); // Shooter with ranged attack
        ChaserEnemy chaserEnemy = new ChaserEnemy();   // Chaser that follows player
        enemies.add(shooterEnemy);
        enemies.add(chaserEnemy);

        // Enemy manager to manage enemies
        EnemyManager enemyManager = new EnemyManager(enemies);

        // Maze manager to manage maze-related logic
        MazeManager mazeManager = new MazeManager(maze);

        // Combine everything in MazeEntityManager
        this.mazeEntityManager = new MazeEntityManager(
                playerManager,
                enemyManager,
                entranceManager,
                exitManager,
                mazeManager
        );
    }

    @Override
    public MazeEntityManager getMazeEntityManager() {
        return mazeEntityManager;
    }

    @Override
    public int getLevelNumber() {
        return levelNumber;
    }
}
