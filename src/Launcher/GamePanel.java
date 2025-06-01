package Launcher;

import Audio.AudioManager;
import Data.*;
import Game.GameEntities.*;
import Game.Level.LevelLoader;
import Game.Level.LevelManager;
import Utils.GameStateManager;
import Input.*;
import UI.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class GamePanel extends JPanel {
    private final PlayerManager playerManager;
    private final Maze maze;
    private final EntitySetter entitySetter;
    private final EntityManager entityManager;
    private static final GameStateManager gameStateManager=new GameStateManager();
    private final GameController gameController;
    private static WindowManager windowManager;
    private final int originalTileSize=16;
    private final int scale=3;
    private final int tileSize=originalTileSize * scale;
    private static int screenWidth, screenHeight;
    private final AudioManager audioManager=new AudioManager();
    private final KeyboardInputHandler keyboardInputHandler=new KeyboardInputHandler();
    private final PlayerInputHandler playerHandler=new PlayerInputHandler(keyboardInputHandler);
    private final ScreenManager screenManager;
    private final PersistenceService persistence;
    private final HUDRenderer hudRenderer;
    private final GameRenderer renderer=new GameRenderer(this);
    private final GameLoop loop=new GameLoop(
            new GameLoop.LoopListener() {
                @Override public void update() { GameRenderer.update(); }
                @Override public void render() { GamePanel.this.repaint(); }
            },
            60
    );

    public GamePanel() {
        screenWidth  = tileSize * 20;
        screenHeight = tileSize * 12;
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setDoubleBuffered(true);
        setFocusable(true);
        setLayout(new BorderLayout());
        add(renderer, BorderLayout.CENTER);

        playerManager=new PlayerManager(this);
        maze=new Maze(this);
        entityManager=new EntityManager();
        entitySetter=new EntitySetter(this, entityManager);
        List<String> levelFiles=new LevelLoader("res/levels")
                .loadLevelFiles();
        LevelManager lvlMgr=new LevelManager(this, levelFiles);
        lvlMgr.loadCurrentLevel();
        gameController=new GameController(this, lvlMgr, gameStateManager);
        persistence=new FilePersistenceService(this);
        persistence.loadConfig();
        screenManager=new ScreenManager(this);
        hudRenderer=new HUDRenderer(gameStateManager, screenManager.all());
        InputManager inputManager=new InputManager(this,
                gameStateManager,
                keyboardInputHandler,
                playerHandler,
                screenManager.all());
        loop.start();
    }

    public int getOriginalScreenWidth() {
        return screenWidth;
    }
    public int getOriginalScreenHeight() {
        return screenHeight;
    }
    public int getTileSize(){ return tileSize; }
    public int getScreenWidth(){ return screenWidth; }
    public int getScreenHeight(){ return screenHeight; }
    public AudioManager getAudioManager(){ return audioManager; }
    public Player getPlayer(){ return playerManager.getPlayer(); }
    public Maze getMaze(){ return maze; }
    public GameStateManager getGameStateManager(){ return gameStateManager; }
    public PlayerInputHandler getPlayerInputHandler(){ return playerHandler; }
    public PlayerManager getPlayerManager(){ return playerManager; }
    public PersistenceService getPersistence(){ return persistence; }
    public Map<String,Screen> getScreens(){ return screenManager.all(); }
    public GameLoop getLoop(){ return loop; }
    public EntitySetter getEntitySetter() {
        return entitySetter;
    }
    public GameRenderer getRenderer() {
        return renderer;
    }
    public ScreenManager getScreenManager() {
        return screenManager;
    }
    public void setWindowManager(WindowManager wm) {
        windowManager = wm;
    }
    public static WindowManager getWindowManager() {
        return windowManager;
    }
    public HUDRenderer getHudRenderer() {
        return hudRenderer;
    }
    public EntityManager getEntityManager() {
        return entityManager;
    }
    public GameController getGameController() {
        return gameController;
    }
}