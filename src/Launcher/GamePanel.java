package Launcher;

import Audio.AudioManager;
import Data.Config;
import Data.DataSaver;
import Game.GameEntities.*;
import Game.GameStates.GameStateManager;
import Input.InputHandler;
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

public class GamePanel extends JPanel implements Runnable {
    private final PlayerManager playerManager;
    private final DataSaver dataSaver;
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
    private int FPS = 60;
    private final CollisionChecker collisionChecker = new CollisionChecker(this);
    private final int maxWorldCol = 70;
    private final int maxWorldRow = 70;
    private Thread gameThread;
    private final GameStateManager gameStateManager = new GameStateManager();
    private final HUD hud;
    private final PowerUpShop powerUpShop = new PowerUpShop(this);
    private final KeyboardInputHandler keyboardInputHandler = new KeyboardInputHandler();
    private final PlayerInputHandler playerHandler =  new PlayerInputHandler(keyboardInputHandler);
    private Config config = new Config(this);
    private final LevelManager lvlMgr;

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
        dataSaver = new DataSaver(this);
        List<String> levelFiles = List.of("level1.json", "level2.json", "level3.json", "level4.json", "level5.json");
        lvlMgr = new LevelManager(this, levelFiles);
        lvlMgr.loadCurrentLevel();

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
        InputHandler inputHandler = new InputHandler(this, gameStateManager, keyboardInputHandler, playerHandler, screens);
        this.addKeyListener(inputHandler);
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
        config.loadConfig();
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

    public void startGameThread() {
        gameThread = new Thread(this);
        getGameThread().start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        long drawCount = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount += 1;
            }
            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

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

        if (gameStateManager.isPlaying() || gameStateManager.isStory()) {
            maze.draw(g2);
            for (Entity e : gameEntities) if (e != null) e.draw(g2);
            for (Enemy en : enemies)    if (en != null) en.draw(g2);
            playerManager.getPlayer().draw(g2);
        }
        hud.draw(g2);
        g2.dispose();
    }

    public void setGameThread(Thread gameThread) {
        this.gameThread = gameThread;
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
    public Thread getGameThread() {
        return gameThread;
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
    public Config getConfig() {
        return config;
    }
    public void setConfig(Config config) {
        this.config = config;
    }
    public PlayerInputHandler getPlayerInputHandler() {
        return playerHandler;
    }
    public PlayerManager getPlayerManager() {
        return playerManager;
    }
    public DataSaver getDataSaver() {
        return dataSaver;
    }
    public Map<String, Screen> getScreens() {
        return screens;
    }
    public EntitySetter getEntitySetter() {
        return entitySetter;
    }
}