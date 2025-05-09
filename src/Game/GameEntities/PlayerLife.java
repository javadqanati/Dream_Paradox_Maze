package Game.GameEntities;

import Launcher.GamePanel;
import java.awt.image.BufferedImage;

public class PlayerLife extends Entity {
    private static final String[] SPRITE_PATHS = {
            "/Object/heart_full",
            "/Object/heart_half",
            "/Object/heart_blank"
    };

    public PlayerLife(GamePanel gp) {
        super(gp);
        setName("Player Life");
        getImages();
    }

    @Override
    public void getImages() {
        BufferedImage[] frames = new BufferedImage[SPRITE_PATHS.length];

        for (int i = 0; i < SPRITE_PATHS.length; i++) {
            BufferedImage sprite = getMaker().objectImageSetup(SPRITE_PATHS[i]);
            if (sprite == null) {
                System.out.println("[ERROR] Failed to load sprite: " + SPRITE_PATHS[i]);
            }
            frames[i] = sprite;
        }

        setSpriteFrames(Direction.DOWN, frames);
    }


}
