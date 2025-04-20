package Game.GameEntities;

import Launcher.GamePanel;
import graphicals.SpriteMaker;

public class TimeFreeze extends PowerUp{
    public TimeFreeze(GamePanel gp) {
        super(gp);
        setName("Time Freeze");
        setTimeFreezeImage();
    }

    public void setTimeFreezeImage(){
        SpriteMaker spriteMaker = new SpriteMaker(getGp());
        setImage(spriteMaker.characterSkinSetup("/Object/boot"));
    }
    @Override
    public void apply(){

    }

}
