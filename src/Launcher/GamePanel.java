package Launcher;

import Game.GameEntities.Maze;
import Game.GameEntities.Player;
import Game.GameStates.*;
import Input.InputHandler;
import graphicals.CollisionChecker;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class GamePanel extends JPanel implements Runnable{
    private Player player;
    private Maze maze;

    private final Map<GameState, GameStateHandler> stateHandlers = new HashMap<>();
    private int originalTileSize = 16;
    private int scale = 3;
    private int maxScreenCol = 16;
    private int maxScreenRow = 12;
    private static int screenWidth;
    private static int screenHeight;
    private int tileSize = originalTileSize * scale;
    private InputHandler inputHandler=new InputHandler();


    private int FPS = 60;
    private final Thread gameThread;
    private CollisionChecker collisionChecker = new CollisionChecker(this);
    private final int maxWorldCol = 50;
    private final int maxWorldRow = 50;
//    public final int worldWidth = tileSize*maxWorldCol;
//    public final int worldHeight = tileSize*maxWorldRow;

    public GamePanel() {
        this.addKeyListener(inputHandler);
        this.gameThread = new Thread(this);
        screenWidth = tileSize * maxScreenCol;
        screenHeight = tileSize * maxScreenRow;
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        player = new Player(this, inputHandler);
        maze = new Maze(this);
    }

    @Override
    public void run() {
        double drawInterval=1000000000/FPS;
        double delta=0;
        long lastTime=System.nanoTime();
        long currentTime;
        long timer=0;
        long drawCount=0;

        while(gameThread != null){
            currentTime=System.nanoTime();
            delta += (currentTime-lastTime)/drawInterval;
            timer += (currentTime-lastTime);
            lastTime=currentTime;

            if(delta>= 1){
                update();
                repaint();
                delta--;
                drawCount+=1;
            }
            if(timer>=1000000000){
                System.out.println("FPS: "+ drawCount);
                drawCount=0;
                timer=0;
            }
        }
    }

    public void update() {
        player.update();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2= (Graphics2D) g;
        maze.draw(g2);
        player.draw(g2);

        g2.dispose();
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

    public void setOriginalTileSize(int originalTileSize) {
        this.originalTileSize = originalTileSize;
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

    public void setCollisionChecker(CollisionChecker collisionChecker) {
        this.collisionChecker = collisionChecker;
    }

    public void setTileSize(int tileSize) {
        this.tileSize = tileSize;
    }
}
