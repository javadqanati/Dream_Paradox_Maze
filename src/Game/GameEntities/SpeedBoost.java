package Game.GameEntities;

import Launcher.GamePanel;

public class SpeedBoost extends PowerUp{
    public SpeedBoost(GamePanel gp) {
        super(gp);
        setName("Speed Boost");
        addImage("/Object/boots");
    }
}
