package Game.GameEntities;

import Launcher.GamePanel;
import graphicals.SpriteMaker;

public class ExtraLife extends PowerUp{
    public ExtraLife(GamePanel gp) {
        super(gp);
        setName("Speed Boost");
        setSpeedBoostImage();
    }

    public void setSpeedBoostImage(){
        SpriteMaker spriteMaker = new SpriteMaker(getGp());
        setImage(spriteMaker.characterSkinSetup("/Object/boot"));
    }

    @Override
    public void apply(){

    }
}
