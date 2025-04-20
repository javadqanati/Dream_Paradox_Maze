package Game.GameEntities;

import Launcher.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameEntities {
    private int worldX, worldY;
    private BufferedImage image;
    private String name;
    private boolean passable = false;
    private Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    private int solidAreaX = 0;
    private int solidAreaY = 0;
    private final GamePanel gp;

    public GameEntities(GamePanel gp) {
        this.gp = gp;
    }

    public void draw(Graphics2D g2, GamePanel gamePanel){
        int entityScreenX = worldX - gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getScreenX();
        int entityScreenY = worldY - gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getScreenY();

        if(worldX + gamePanel.getTileSize() > gamePanel.getPlayer().getWorldX() - gamePanel.getPlayer().getScreenX() &&
                worldX - gamePanel.getTileSize() < gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getScreenX() &&
                worldY + gamePanel.getTileSize() > gamePanel.getPlayer().getWorldY() - gamePanel.getPlayer().getScreenY() &&
                worldY - gamePanel.getTileSize() < gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getScreenY()){
            g2.drawImage(image, entityScreenX, entityScreenY, gamePanel.getTileSize(), gamePanel.getTileSize(), null);
        }
    }
    public int getWorldX() {
        return worldX;
    }

    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPassable() {
        return passable;
    }

    public void setPassable(boolean passable) {
        this.passable = passable;
    }

    public Rectangle getSolidArea() {
        return solidArea;
    }

    public void setSolidArea(Rectangle solidArea) {
        this.solidArea = solidArea;
    }

    public int getSolidAreaX() {
        return solidAreaX;
    }

    public void setSolidAreaX(int solidAreaX) {
        this.solidAreaX = solidAreaX;
    }

    public int getSolidAreaY() {
        return solidAreaY;
    }

    public void setSolidAreaY(int solidAreaY) {
        this.solidAreaY = solidAreaY;
    }

    public GamePanel getGp() {
        return gp;
    }
}
