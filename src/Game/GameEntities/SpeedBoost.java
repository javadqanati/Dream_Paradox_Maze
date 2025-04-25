package Game.GameEntities;

import Launcher.GamePanel;

public class SpeedBoost extends PowerUp{
    public SpeedBoost(GamePanel gp) {
        super(gp);
        setName("Speed Boost");
        addImage("/Object/boots");
        initialize("SpeedBoost", 3);
    }

    @Override
    public void apply() {

    }

    @Override
    public PowerUp createNewInstance() {
        return new SpeedBoost(getGp());
    }
}
