package Game.GameEntities;

import Launcher.GamePanel;

public class Exit extends GameEntities{
    public Exit(GamePanel gp) {
        super(gp);
        setName("Exit");
        setPassable(true);
        addImage("/Object/door");
    }
}