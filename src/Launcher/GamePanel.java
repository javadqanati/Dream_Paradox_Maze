package Launcher;

import Audio.AudioManager;
import Data.FilePersistenceService;
import Data.PersistenceService;
import Game.GameEntities.*;
import Game.GameStates.GameStateManager;
import Input.InputManager;
import Input.KeyboardInputHandler;
import Input.PlayerInputHandler;
import Trade.PowerUpShop;
import UI.*;
import Utils.LevelManager;
import graphicals.CollisionChecker;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class GamePanel extends JPanel {
    private final PlayerManager playerManager;
    private final Maze maze;
    private final Entity[] gameEntities;
    private final Enemy[] enemies = new Enemy[10];
    private final Map<String, Screen> screens = new HashMap<>();
    private final EntitySetter entitySetter;
    private final int originalTileSize = 16;
    private final int scale = 3;
    private int maxScreenCol = 16;
    private int maxScreenRow = 12;
    private static int screenWidth;
    private static int screenHeight;
    private final int tileSize = originalTileSize * scale;
    private final AudioManager audioManager = new AudioManager();
    private final CollisionChecker collisionChecker = new CollisionChecker(this);
    private final int maxWorldCol = 70;
    private final int maxWorldRow = 70;
    private final GameStateManager gameStateManager = new GameStateManager();
    private final HUD hud;
    private final PowerUpShop powerUpShop = new PowerUpShop(this);
    private final KeyboardInputHandler keyboardInputHandler = new KeyboardInputHandler();
    private final PlayerInputHandler playerHandler =  new PlayerInputHandler(keyboardInputHandler);
    private final LevelManager lvlMgr;
    private final GameRenderer renderer = new GameRenderer(this);
    private final FilePersistenceService persistence;
    public GamePanel() {
        screenWidth = tileSize * maxScreenCol;
        screenHeight = tileSize * maxScreenRow;
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        playerManager = new PlayerManager(this);
        maze = new Maze(this);
        gameEntities = new Entity[15];
        entitySetter = new EntitySetter(this);

        List<String> levelFiles = List.of("level1.json", "level2.json", "level3.json", "level4.json", "level5.json");
        lvlMgr = new LevelManager(this, levelFiles);
        lvlMgr.loadCurrentLevel();


        persistence = new FilePersistenceService(this);
        persistence.loadConfig();


        screens.put("MENU",      new MainScreen(this));
        screens.put("PLAY",      new PlayScreen(this));
        screens.put("PAUSE",     new PauseScreen(this));
        screens.put("SETTINGS",  new SettingScreen(this));
        screens.put("INVENTORY", new InventoryScreen(this));
        screens.put("MARKET",    new MarketScreen(this));
        screens.put("GAMEOVER",  new GameOverScreen(this));
        screens.put("LOAD",  new LoadScreen(this));
        screens.put("STORY", screens.get("PLAY"));

        hud = new HUD(this, gameStateManager, screens);
        InputManager inputManager = new InputManager(this, gameStateManager, keyboardInputHandler, playerHandler, screens);
    }

    private PlayScreen getPlayScreen() {
        return (PlayScreen) screens.get("PLAY");
    }

    public void restartGame() {
        playerManager.resetPlayer();
        ((PlayScreen)screens.get("PLAY")).resetTimer();
        lvlMgr.loadCurrentLevel();
        gameStateManager.setPlay();
    }

    public void setUpGame() {
        playerManager.resetPlayer();
        ((PlayScreen)screens.get("PLAY")).resetTimer();
        lvlMgr.loadCurrentLevel();
        gameStateManager.setMenu();
        if (!AudioManager.isMusicMuted()) {
            audioManager.playMusic(0);
        }
    }

    public void onLevelComplete() {
        lvlMgr.nextLevel();
        playerManager.resetPlayer();
        PlayScreen ps = getPlayScreen();
        ps.resetTimer();
        String storyPath = "/stories/level" + lvlMgr.getCurrentIndex() + ".txt";
        ps.loadStory(storyPath);
        gameStateManager.setLoad();
    }

    public void startNewGameFromMenu() {
        lvlMgr.startNewGame();
        playerManager.resetPlayer();
        PlayScreen ps = getPlayScreen();
        ps.resetTimer();
        ps.loadStory("/stories/level1.txt");
        gameStateManager.setLoad();
    }

    public void loadGame() {
        lvlMgr.loadProgressAndBegin();
        playerManager.resetPlayer();
        PlayScreen ps = getPlayScreen();
        ps.resetTimer();
        ps.loadStory("/stories/level" + (lvlMgr.getCurrentIndex() + 1) + ".txt");
        gameStateManager.setLoad();
    }

    private final GameLoop loop = new GameLoop(
            new GameLoop.LoopListener() {
                @Override public void update() { GamePanel.this.update(); }
                @Override public void render() { GamePanel.this.repaint(); }
            },
            60
    );

    public void update() {
        if (gameStateManager.isPlaying()) {
            playerManager.getPlayer().update();
            for (Enemy enemy : enemies) {
                if (enemy != null) {
                    enemy.update();
                }
            }
        }
        if (gameStateManager.isPaused()) {
            gameStateManager.setPause();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        renderer.render(g2);       // <— delegate all drawing
        g2.dispose();
    }


    public AudioManager getAudioManager() {
        return audioManager;
    }
    public HUD getHud() {
        return hud;
    }
    public int getScreenWidth() {
        return screenWidth;
    }
    public int getScreenHeight() {
        return screenHeight;
    }
    public int getMaxWorldCol() {
        return maxWorldCol;
    }
    public int getMaxWorldRow() {
        return maxWorldRow;
    }
    public Player getPlayer() {
        return playerManager.getPlayer();
    }
    public int getTileSize() {
        return tileSize;
    }
    public CollisionChecker getCollisionChecker() {
        return collisionChecker;
    }
    public Maze getMaze() {
        return maze;
    }
    public Entity[] getGameEntities() {
        return gameEntities;
    }
    public GameStateManager getGameStateManager() {
        return gameStateManager;
    }
    public Enemy[] getEnemies() {
        return enemies;
    }
    public PowerUpShop getPowerUpShop() {
        return powerUpShop;
    }
    public boolean fullScreenOn() {
        return false;}
    public PlayerInputHandler getPlayerInputHandler() {
        return playerHandler;
    }
    public PlayerManager getPlayerManager() {
        return playerManager;
    }
    public Map<String, Screen> getScreens() {
        return screens;
    }
    public EntitySetter getEntitySetter() {
        return entitySetter;
    }
    public GameLoop getLoop() {
        return loop;
    }
    public FilePersistenceService getPersistence() {
        return persistence;
    }
}