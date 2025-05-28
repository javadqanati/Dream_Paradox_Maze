package graphicals;

import Launcher.GamePanel;
import org.jetbrains.annotations.Nullable;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SpriteMaker {
    private final GamePanel gp;
    private static final Logger LOGGER = Logger.getLogger(SpriteMaker.class.getName());

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
        return getBufferedImage(filePath);
    }

    public BufferedImage objectImageSetup(String filePath) {
        return getBufferedImage(filePath);
    }

    @Nullable
    private BufferedImage getBufferedImage(String filePath) {
        BufferedImage image = null;

        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(filePath + ".png")));
            image = makeSprite(image, gp.getTileSize(), gp.getTileSize());
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, "Failed getting the buffered image ", e);
        }
        return image;
    }
}

