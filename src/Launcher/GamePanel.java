package Launcher;

import Audio.AudioManager;
import Game.GameEntities.*;
import Game.GameStates.GameStateManager;
import Input.InputHandler;
import Input.KeyboardInputHandler;
import Input.PlayerInputHandler;
import Market.PowerUpShop;
import UI.*;
import graphicals.CollisionChecker;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class GamePanel extends JPanel implements Runnable {
    private final Player player;
    private final Maze maze;
    private final GameEntities[] gameEntities;
    private final Enemy[] enemies = new Enemy[4];
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
    private final int maxWorldCol = 53;
    private final int maxWorldRow = 59;
    private Thread gameThread;
    private final GameStateManager gameStateManager = new GameStateManager();
    private final HUD hud;
    private final PowerUpShop powerUpShop = new PowerUpShop(this);
    private final KeyboardInputHandler keyboardInputHandler = new KeyboardInputHandler();
    private final PlayerInputHandler playerHandler =  new PlayerInputHandler(keyboardInputHandler);
    private final InputHandler inputHandler;

    public GamePanel() {
        screenWidth = tileSize * maxScreenCol;
        screenHeight = tileSize * maxScreenRow;
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        player = new Player(this, playerHandler);
        maze = new Maze(this);
        gameEntities = new GameEntities[10];
        entitySetter = new EntitySetter(this);

        screens.put("MENU",      new MainScreen(this));
        screens.put("PLAY",      new PlayScreen(this));
        screens.put("PAUSE",     new PauseScreen(this));
        screens.put("SETTINGS",  new SettingScreen(this));
        screens.put("INVENTORY", new InventoryScreen(this));
        screens.put("MARKET",    new MarketScreen(this));
        screens.put("GAMEOVER",  new GameOverScreen(this));

        hud = new HUD(this, gameStateManager, screens);
        inputHandler = new InputHandler(this, gameStateManager, keyboardInputHandler, playerHandler, screens);
        this.addKeyListener(inputHandler);
    }

    public void setUpGame() {
        entitySetter.loadEntities();
        entitySetter.setEnemy();
        audioManager.playMusic(0);
        gameStateManager.setMenu();
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
            player.update();
            for (Enemy enemy : enemies) {
                if (enemy != null) {
                    enemy.update();
                }
            }
        }
        if (gameStateManager.isPaused()) {
            // nothing rn
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (gameStateManager.isPlaying()) {
            maze.draw(g2);

            for (GameEntities gameEntity : gameEntities) {
                if (gameEntity != null) {
                    gameEntity.draw(g2, this);
                }
            }
            for (Enemy enemy: enemies) {
                if (enemy != null) {
                    enemy.draw(g2);
                }
            }

            player.draw(g2);
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
        return player;
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

    public GameEntities[] getGameEntities() {
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
}
