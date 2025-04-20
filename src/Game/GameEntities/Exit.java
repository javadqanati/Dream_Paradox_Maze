package Game.GameEntities;

import Launcher.GamePanel;
import graphicals.SpriteMaker;

public class Exit extends GameEntities{
    public Exit(GamePanel gp) {
        super(gp);
        setName("Exit");
        setPassable(true);
        setExitImage();
    }

    public void setExitImage(){
        SpriteMaker spriteMaker = new SpriteMaker(getGp());
        setImage(spriteMaker.characterSkinSetup("/Object/door"));
    }
}
