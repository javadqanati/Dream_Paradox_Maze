package Launcher;

import Audio.AudioManager;
import Data.*;
import Game.GameEntities.*;
import Utils.GameStateManager;
import Input.*;
import Trade.PowerUpShop;
import UI.*;
import Utils.*;
import graphicals.CollisionChecker;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class GamePanel extends JPanel {
    // ─── WORLD & LOGIC ───────────────────────────────────────────────────────────
    private final PlayerManager       playerManager;
    private final Maze                maze;
    private final EntitySetter        entitySetter;
    private final EntityManager       entityManager;
    private final LevelManager        lvlMgr;
    private static final GameStateManager gameStateManager = new GameStateManager();
    private final CollisionChecker    collisionChecker = new CollisionChecker(this);
    private static boolean isFullScreen = false;
    private static WindowManager windowManager;

    // ─── RENDERING & LOOP ────────────────────────────────────────────────────────
    private final int originalTileSize = 16;
    private final int scale            = 3;
    private final int tileSize         = originalTileSize * scale;
    private static int screenWidth, screenHeight;
    private final HUDRenderer hudRenderer;
    private final GameRenderer renderer = new GameRenderer(this);
    private final GameLoop     loop     = new GameLoop(
            new GameLoop.LoopListener() {
                @Override public void update() { GamePanel.this.update(); }
                @Override public void render() { GamePanel.this.repaint(); }
            },
            60
    );

    // ─── AUDIO & SHOP ─────────────────────────────────────────────────────────────
    private final AudioManager     audioManager        = new AudioManager();
    private final PowerUpShop      powerUpShop         = new PowerUpShop(this);

    // ─── INPUT & UI STATES ───────────────────────────────────────────────────────
    private final KeyboardInputHandler keyboardInputHandler = new KeyboardInputHandler();
    private final PlayerInputHandler   playerHandler         = new PlayerInputHandler(keyboardInputHandler);
    private final ScreenManager        screenManager;
    private final HUD                  hud;

    // ─── PERSISTENCE ─────────────────────────────────────────────────────────────
    private final PersistenceService persistence;

    public GamePanel() {
        // compute dimensions
        screenWidth  = tileSize * 20;
        screenHeight = tileSize * 12;
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setBackground(Color.black);
        setDoubleBuffered(true);
        setFocusable(true);

        // world setup
        playerManager = new PlayerManager(this);
        maze          = new Maze(this);
        entityManager = new EntityManager();
        entitySetter  = new EntitySetter(this, entityManager);

        // levels
        List<String> levelFiles = new LevelLoader("res/levels")
                .loadLevelFiles();
        lvlMgr = new LevelManager(this, levelFiles);
        lvlMgr.loadCurrentLevel();

        entitySetter.loadEntities(
                lvlMgr.getEntityConfigs(),
                lvlMgr.getEnemyConfigs()
        );

        // persistence & config
        persistence = new FilePersistenceService(this);
        persistence.loadConfig();

        // UI state registry
        screenManager = new ScreenManager(this);

        // HUD & input wiring
        hud           = new HUD(screenManager.all());
        hudRenderer   = new HUDRenderer(gameStateManager, screenManager.all());
        InputManager inputManager = new InputManager(this,
                gameStateManager,
                keyboardInputHandler,
                playerHandler,
                screenManager.all());
        loop.start();
    }

    public PlayScreen getPlayScreen() {
        return (PlayScreen) screenManager.get("PLAY");
    }

    // ─── GAME CONTROLS ────────────────────────────────────────────────────────────

    private String getStoryPath(int levelIndex) {
        return String.format("/stories/level%d.txt", levelIndex);
    }

    public void loadPlayScreenForLevel(int levelIndex) {
        playerManager.resetPlayer();
        PlayScreen ps = getPlayScreen();
        ps.resetTimer();
        ps.loadStory(getStoryPath(levelIndex));

        gameStateManager.setLoad();
    }

    public void restartGame() {
        playerManager.resetPlayer();
        getPlayScreen().resetTimer();
        lvlMgr.loadCurrentLevel();
        gameStateManager.setPlay();
    }

    public void setUpGame() {
        playerManager.resetPlayer();
        getPlayScreen().resetTimer();
        lvlMgr.loadCurrentLevel();
        gameStateManager.setMenu();
        if (!AudioManager.isMusicMuted()) {
            audioManager.playCurrentTrack();
        }
    }

    public void onLevelComplete() {
        lvlMgr.nextLevel();
        entitySetter.loadEntities(
                lvlMgr.getEntityConfigs(),
                lvlMgr.getEnemyConfigs()
        );
        loadPlayScreenForLevel(lvlMgr.getCurrentIndex());
    }

    public void startNewGameFromMenu() {
        lvlMgr.startNewGame();
        entitySetter.loadEntities(
                lvlMgr.getEntityConfigs(),
                lvlMgr.getEnemyConfigs()
        );
        loadPlayScreenForLevel(1);
    }

    public void loadGame() {
        lvlMgr.loadProgressAndBegin();
        entitySetter.loadEntities(
                lvlMgr.getEntityConfigs(),
                lvlMgr.getEnemyConfigs()
        );
        loadPlayScreenForLevel(lvlMgr.getCurrentIndex() + 1);
    }

    // ─── MAIN LOOP ────────────────────────────────────────────────────────────────

    public void update() {
        if (gameStateManager.isPlaying()) {
            playerManager.getPlayer().update();
            for (Enemy e : entityManager.getEnemies()) {
                e.update();
            }
        }
        if (gameStateManager.isPaused()) {
            gameStateManager.setPause();
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        renderer.render((Graphics2D) g);
    }

    // ─── GETTERS ──────────────────────────────────────────────────────────────────

    public int getOriginalScreenWidth() {
        return screenWidth;
    }
    public int getOriginalScreenHeight() {
        return screenHeight;
    }
    public int getTileSize()        { return tileSize; }
    public int getScreenWidth()     { return screenWidth; }
    public int getScreenHeight()    { return screenHeight; }
    public int getMaxWorldCol()     { return 70; }
    public int getMaxWorldRow()     { return 70; }
    public AudioManager getAudioManager()       { return audioManager; }
    public CollisionChecker getCollisionChecker() { return collisionChecker; }
    public Player getPlayer()       { return playerManager.getPlayer(); }
    public Maze getMaze()           { return maze; }
    public GameStateManager getGameStateManager() { return gameStateManager; }
    public PlayerInputHandler getPlayerInputHandler() { return playerHandler; }
    public PlayerManager getPlayerManager()     { return playerManager; }
    public PersistenceService getPersistence()  { return persistence; }
    public Map<String,Screen> getScreens()      { return screenManager.all(); }
    public GameLoop getLoop()                  { return loop; }
    public boolean fullScreenOn() {
        return isFullScreen;
    }
    public void setFullScreenOn(boolean fullScreenOn) {
        isFullScreen = fullScreenOn;
    }
    public EntitySetter getEntitySetter() {
        return entitySetter;
    }
    public HUD getHud() {
        return hud;
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
    public void toggleFullScreen() {
        if (windowManager != null) {
            windowManager.toggleFullscreen();
            setFullScreenOn(!fullScreenOn());
        }
    }
    public HUDRenderer getHudRenderer() {
        return hudRenderer;
    }
    public EntityManager getEntityManager() {
        return entityManager;
    }
}