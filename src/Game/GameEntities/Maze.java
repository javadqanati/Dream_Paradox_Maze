package Game.GameEntities;

import Launcher.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class Maze{
    GamePanel gamePanel;
    Tile[] tile;
    int mapTileNum[][];

    public Maze(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tile = new Tile[10];
        mapTileNum = new int[gamePanel.getMaxWorldCol()][gamePanel.getMaxWorldRow()];
        getTileImage();
        loadMaze("/maps/world01.txt");
    }

    public void loadMaze(String filepath){
        try{
            InputStream is = getClass().getResourceAsStream(filepath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row= 0;

            while(col < gamePanel.getMaxWorldCol() && row < gamePanel.getMaxWorldRow()){
                String line = br.readLine();
                while(col < gamePanel.getMaxWorldCol()) {
                    String numbeers[] = line.split(" ");
                    int num = Integer.parseInt(numbeers[col]);
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
        try{
            tile[0] = new Tile();
            tile[0].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png")));

            tile[1] = new Tile();
            tile[1].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png")));
            tile[1].setPassable(true);

            tile[2] = new Tile();
            tile[2].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/water.png")));
            tile[2].setPassable(true);


            tile[3] = new Tile();
            tile[3].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png")));

            tile[4] = new Tile();
            tile[4].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png")));
            tile[5] = new Tile();
            tile[5].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png")));

        }catch (IOException e) {
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
            int screenX = worldX - gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().screenX;
            int screenY = worldY - gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().screenY;

            if(worldX + gamePanel.getTileSize() > gamePanel.getPlayer().getWorldX() - gamePanel.getPlayer().screenX &&
                    worldX - gamePanel.getTileSize() < gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().screenX &&
                    worldY + gamePanel.getTileSize() > gamePanel.getPlayer().getWorldY() - gamePanel.getPlayer().screenY &&
                    worldY - gamePanel.getTileSize() < gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().screenY){
                g2.drawImage(tile[tileNum].getImage(), screenX, screenY, gamePanel.getTileSize(), gamePanel.getTileSize(), null);
            }

            worldCol++;

            if(worldCol == gamePanel.getMaxWorldCol()){
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
