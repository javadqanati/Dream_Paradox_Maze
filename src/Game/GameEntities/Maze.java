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

public class Maze{
    private final GamePanel gamePanel;
    private final Tile[] tile;
    private final int[][] mapTileNum;

    public Maze(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tile = new Tile[10];
        mapTileNum = new int[gamePanel.getMaxWorldCol()][gamePanel.getMaxWorldRow()];
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
                int col = 0, row = 0;
                while (col < gamePanel.getMaxWorldCol() && row < gamePanel.getMaxWorldRow()) {
                    String line = br.readLine();
                    if (line == null) {
                        throw new IOException("Unexpected end of file in " + filepath);
                    }
                    String[] numbers = line.split(" ");
                    for (col = 0; col < numbers.length && col < gamePanel.getMaxWorldCol(); col++) {
                        mapTileNum[col][row] = Integer.parseInt(numbers[col]);
                    }
                    col = 0;
                    row++;
                }
            }
        } catch (Exception e) {

            System.err.println("Error loading maze '" + filepath + "': " + e.getMessage());
            e.printStackTrace();
        }
    }


    public void getTileImage() {
            setUpTile(1, "grass", false);
            setUpTile(4, "wall", true);
            setUpTile(2, "water", true);
            setUpTile(3, "earth", false);
            setUpTile(0, "tree", true);
            setUpTile(5, "sand", false);
    }

    public void setUpTile(int index, String imagePath, boolean passable) {
        SpriteMaker spriteMaker = new SpriteMaker(gamePanel);
        try{
            tile[index] = new Tile();
            tile[index].setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/" + imagePath + ".png"))));
            tile[index].setImage(spriteMaker.makeSprite(tile[index].getImage(), gamePanel.getTileSize(), gamePanel.getTileSize()));
            tile[index].setPassable(passable);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gamePanel.getMaxWorldCol() && worldRow < gamePanel.getMaxWorldRow()) {
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

            if(worldCol == gamePanel.getMaxWorldCol()){
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
