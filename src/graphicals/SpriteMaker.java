package graphicals;

import Game.GameEntities.Tile;
import Launcher.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class SpriteMaker {
    private final GamePanel gp;

    public SpriteMaker(GamePanel gp){
        this.gp = gp;
    }

    public BufferedImage makeSprite(BufferedImage image, int width, int height) {

        BufferedImage scaledImage = new BufferedImage(width, height, image.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(image, 0, 0, width, height, null);
        image = scaledImage;
        return image;
    }

    public BufferedImage characterSkinSetup(String filePath) {
        SpriteMaker spriteMaker = new SpriteMaker(gp);
        BufferedImage image = null;

        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(filePath + ".png")));
            image = spriteMaker.makeSprite(image, gp.getTileSize(), gp.getTileSize());
        }catch(Exception e){
            e.printStackTrace();
        }
        return image;
    }

    public void mazeSkinSetup(Tile[] tile, String imagePath, boolean passable) {
//        SpriteMaker spriteMaker = new SpriteMaker();
//        try{
//            tile[index] = new Tile();
//            tile[index].setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/" + imagePath + ".png"))));
//            tile[index].setImage(spriteMaker.makeSprite(tile[index].getImage(), gamePanel.getTileSize(), gamePanel.getTileSize()));
//            tile[index].setPassable(passable);
//        }catch(Exception e){
//            e.printStackTrace();
//        }
    }
}

