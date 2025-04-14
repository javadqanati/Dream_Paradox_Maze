package Game.levels;

import Game.GameEntities.MazeEntityManager;

public interface Level {
    void load(); // Initializes maze and entities
    MazeEntityManager getMazeEntityManager(); // Returns all the managers
    int getLevelNumber(); // Useful for tracking current level
}
