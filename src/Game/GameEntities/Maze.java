package Game.GameEntities;

import Launcher.GamePanel;
import graphicals.SpriteMaker;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Maze{
    private final static int maxWorldCol = 70;
    private final static int maxWorldRow = 70;
    private final GamePanel gamePanel;
    private final Tile[] tile;
    private final int[][] mapTileNum;
    private static final Logger LOGGER = Logger.getLogger(Maze.class.getName());

    public Maze(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tile = new Tile[10];
        mapTileNum = new int[maxWorldCol][maxWorldRow];
        getTileImage();
    }

    public void loadMaze(String filepath){
        try {
            InputStream is = getClass().getResourceAsStream(filepath);
            if (is == null) {
                throw new IllegalArgumentException(
                        "Could not find map file on classpath: " + filepath);
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                int col, row = 0;
                while (row < maxWorldRow) {
                    String line = br.readLine();
                    if (line == null) {
                        throw new IOException("Unexpected end of file in " + filepath);
                    }
                    String[] numbers = line.split(" ");
                    for (col = 0; col < numbers.length && col < maxWorldCol; col++) {
                        mapTileNum[col][row] = Integer.parseInt(numbers[col]);
                    }
                    row++;
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error loading maze '" + filepath + "': " + e);
        }
    }


    public void getTileImage() {
            setUpTile(1, "kaf", false);
            setUpTile(0, "sang", true);
    }

    public void setUpTile(int index, String imagePath, boolean passable) {
        SpriteMaker spriteMaker = new SpriteMaker(gamePanel);
        try{
            tile[index] = new Tile();
            tile[index].setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/" + imagePath + ".png"))));
            tile[index].setImage(spriteMaker.makeSprite(tile[index].getImage(), gamePanel.getTileSize(), gamePanel.getTileSize()));
            tile[index].setPassable(passable);
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, "Error setting up tiles", e);
        }
    }

    public void draw(Graphics2D g2){
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < maxWorldCol && worldRow < maxWorldRow) {
            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gamePanel.getTileSize();
            int worldY = worldRow * gamePanel.getTileSize();
            int screenX = worldX - gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getScreenX();
            int screenY = worldY - gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getScreenY();

            if(worldX + gamePanel.getTileSize() > gamePanel.getPlayer().getWorldX() - gamePanel.getPlayer().getScreenX() &&
                    worldX - gamePanel.getTileSize() < gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getScreenX() &&
                    worldY + gamePanel.getTileSize() > gamePanel.getPlayer().getWorldY() - gamePanel.getPlayer().getScreenY() &&
                    worldY - gamePanel.getTileSize() < gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getScreenY()){
                g2.drawImage(tile[tileNum].getImage(), screenX, screenY, null);
            }

            worldCol++;

            if(worldCol == maxWorldCol){
                worldCol = 0;
                worldRow++;
            }
        }
    }

    public Tile[] getTile() {
        return tile;
    }

    public int[][] getMapTileNum() {
        return mapTileNum;
    }
}
