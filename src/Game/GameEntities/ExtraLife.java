package Game.GameEntities;

import Launcher.GamePanel;

public class ExtraLife extends PowerUp{
    public ExtraLife(GamePanel gp) {
        super(gp);
        setName("Speed Boost");
        addImage("/Object/boot");
    }
}
