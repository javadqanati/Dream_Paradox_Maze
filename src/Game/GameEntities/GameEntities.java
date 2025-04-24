package Game.GameEntities;

import Launcher.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import graphicals.SpriteMaker;

public class GameEntities {
    private int worldX, worldY;
    private final List<BufferedImage> images = new ArrayList<>();
    private String name;
    private boolean passable = false;
    private Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    private int solidAreaX = 0;
    private int solidAreaY = 0;
    private final GamePanel gp;
    private final SpriteMaker spriteMaker;

    public GameEntities(GamePanel gp) {
        this.gp = gp;
        this.spriteMaker = new SpriteMaker(gp);
    }

    public void draw(Graphics2D g2, GamePanel gamePanel){
        int entityScreenX = worldX - gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getScreenX();
        int entityScreenY = worldY - gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getScreenY();

        if(worldX + gamePanel.getTileSize() > gamePanel.getPlayer().getWorldX() - gamePanel.getPlayer().getScreenX() &&
                worldX - gamePanel.getTileSize() < gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getScreenX() &&
                worldY + gamePanel.getTileSize() > gamePanel.getPlayer().getWorldY() - gamePanel.getPlayer().getScreenY() &&
                worldY - gamePanel.getTileSize() < gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getScreenY()){
            if (!images.isEmpty()) {
                for (int i = 0; i < images.size(); i++) {
                    int offsetX = entityScreenX + i * gp.getTileSize(); // draw them horizontally
                    g2.drawImage(images.get(i), offsetX, entityScreenY, null);
                }
            }
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

    public void addImage(String imagePath) {
        BufferedImage image = spriteMaker.ObjectImageSetup(imagePath);
        if (image != null) {
            this.images.add(image);
        }
    }

    public BufferedImage getImage(int index) {
        if (index >= 0 && index < images.size()) {
            return images.get(index);
        }
        return null;
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

    public int getSolidAreaY() {
        return solidAreaY;
    }

    public GamePanel getGp() {
        return gp;
    }
}
