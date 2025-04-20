package Game.GameEntities;

import Launcher.GamePanel;

public class Entrance extends GameEntities{
    public Entrance(GamePanel gp) {
        super(gp);
        setName("Entrance");
        setPassable(true);
        addImage("/Object/door");
    }
}