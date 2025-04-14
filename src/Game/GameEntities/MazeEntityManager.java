package Game.GameEntities;

public class MazeEntityManager {
    private PlayerManager playerManager;
    private EnemyManager enemyManager;
    private EntranceManager entranceManager;
    private ExitManager exitManager;
    private MazeManager mazeManager;

    public MazeEntityManager(PlayerManager playerManager, EnemyManager enemyManager,
                             EntranceManager entranceManager, ExitManager exitManager, MazeManager mazeManager) {
        this.playerManager = playerManager;
        this.enemyManager = enemyManager;
        this.entranceManager = entranceManager;
        this.exitManager = exitManager;
        this.mazeManager = mazeManager;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public EnemyManager getEnemyManager() {
        return enemyManager;
    }

    public EntranceManager getEntranceManager() {
        return entranceManager;
    }

    public ExitManager getExitManager() {
        return exitManager;
    }

    public MazeManager getMazeManager() {
        return mazeManager;
    }
}
