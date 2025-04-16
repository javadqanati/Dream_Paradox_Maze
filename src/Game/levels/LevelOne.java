//package Game.levels;
//
//import Game.GameEntities.*;
//import Input.InputHandler;
//import Launcher.GamePanel;
//
//import java.awt.Color;
//import java.awt.Graphics;
//import java.awt.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class LevelOne {
//    private MazeEntityManager mazeEntityManager;
//    private final int cols;
//    private final int rows;
//    private final int tileSize;
//    private final GamePanel gamePanel;
//    private final InputHandler input;
//
//    public LevelOne(int cols, int rows, int tileSize, GamePanel gamePanel, InputHandler input) {
//        this.cols = cols;
//        this.rows = rows;
//        this.tileSize=tileSize;
//        this.gamePanel = gamePanel;
//        this.input = input;
//    }
//
//
//    private Entrance createEntrance() {
//        Entrance entrance = new Entrance(450, 450);
//        return entrance;
//    }
//
//    private Exit createExit() {
//        Exit exit = new Exit(Math.min(4, cols - 1), Math.min(4, rows - 1));
//        return exit;
//    }
//
//    private Player createPlayer() {
//        return new Player(gamePanel, input);
//    }
//
//    private EnemyManager createEnemies() {
//        List<Enemy> enemies = new ArrayList<>();
////        enemies.add(new ShooterEnemy());
////        enemies.add(new ChaserEnemy());
//        return new EnemyManager(enemies);
//    }

//}
