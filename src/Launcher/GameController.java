package Launcher;

import Utils.GameStateManager;
import Game.Level.LevelManager;

public class GameController {
    private final GamePanel gp;
    private final LevelManager lvlMgr;
    private final GameStateManager gameStateManager;

    public GameController(GamePanel gp, LevelManager lvlMgr, GameStateManager gameStateManager) {
        this.gp = gp;
        this.lvlMgr = lvlMgr;
        this.gameStateManager = gameStateManager;
    }

    public void onLevelComplete() {
        lvlMgr.nextLevel();
        gp.getEntitySetter().loadEntities(lvlMgr.getEntityConfigs(), lvlMgr.getEnemyConfigs());
        loadPlayScreenForLevel(lvlMgr.getCurrentIndex());
    }

    public void restartGame() {
        gp.getPlayerManager().resetPlayer();
        gp.getScreenManager().get("PLAY").resetTimer();
        lvlMgr.loadCurrentLevel();
        gameStateManager.setPlay();
    }

    public void setUpGame() {
        gp.getPlayerManager().resetPlayer();
        gp.getScreenManager().get("PLAY").resetTimer();
        lvlMgr.loadCurrentLevel();
        gameStateManager.setMenu();
    }

    private String getStoryPath(int levelIndex) {
        return String.format("/stories/level%d.txt", levelIndex);
    }

    public void loadPlayScreenForLevel(int levelIndex) {
        gp.getPlayerManager().resetPlayer();
        gp.getScreenManager().get("PLAY").resetTimer();
        gp.getScreenManager().get("PLAY").loadStory(getStoryPath(levelIndex));
        gameStateManager.setLoad();
    }

    public void startNewGameFromMenu() {
        lvlMgr.startNewGame();
        gp.getEntitySetter().loadEntities(
                lvlMgr.getEntityConfigs(),
                lvlMgr.getEnemyConfigs()
        );
        loadPlayScreenForLevel(1);
    }

    public void loadGame() {
        lvlMgr.loadProgressAndBegin();
        gp.getEntitySetter().loadEntities(
                lvlMgr.getEntityConfigs(),
                lvlMgr.getEnemyConfigs()
        );
        loadPlayScreenForLevel(lvlMgr.getCurrentIndex() + 1);
    }
}