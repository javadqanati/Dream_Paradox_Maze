package Game.GameEntities;

import Launcher.GamePanel;
import graphicals.SpriteMaker;

import javax.imageio.ImageIO;
import java.util.Objects;

public class SpeedBoost extends PowerUp{

    public SpeedBoost(GamePanel gp) {
        super(gp);
        setName("Speed Boost");
        setSpeedBoostImage();
    }

    public void setSpeedBoostImage(){
        SpriteMaker spriteMaker = new SpriteMaker(getGp());
        setImage(spriteMaker.characterSkinSetup("/Object/boots"));
    }
    @Override
    public void apply(){

    }
}
