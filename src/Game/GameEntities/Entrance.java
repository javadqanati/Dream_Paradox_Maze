package Game.GameEntities;

import Launcher.GamePanel;
import graphicals.SpriteMaker;

public class Entrance extends GameEntities{
    public Entrance(GamePanel gp) {
        super(gp);
        setName("Entrance");
        setPassable(true);
        setEntranceImage();
    }

    public void setEntranceImage(){
        SpriteMaker spriteMaker = new SpriteMaker(getGp());
        setImage(spriteMaker.characterSkinSetup("/Object/door"));
    }
}
