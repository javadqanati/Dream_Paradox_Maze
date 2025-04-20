package Game.GameEntities;

import Launcher.GamePanel;
import graphicals.SpriteMaker;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
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
        loadMaze("/maps/maze01.txt");
    }

    public void loadMaze(String filepath){
        try{
            InputStream is = getClass().getResourceAsStream(filepath);
            assert is != null;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row= 0;

            while(col < gamePanel.getMaxWorldCol() && row < gamePanel.getMaxWorldRow()){
                String line = br.readLine();
                while(col < gamePanel.getMaxWorldCol()) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gamePanel.getMaxWorldCol()){
                    col = 0;
                    row++;
                }
            }
            br.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void getTileImage() {
            setUpTile(0, "grass", false);
            setUpTile(1, "wall", true);
            setUpTile(2, "water", true);
            setUpTile(3, "earth", false);
            setUpTile(4, "tree", true);
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
