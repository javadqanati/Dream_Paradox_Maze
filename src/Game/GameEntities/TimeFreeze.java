package Game.GameEntities;

import Launcher.GamePanel;

public class TimeFreeze extends PowerUp{
    public TimeFreeze(GamePanel gp) {
        super(gp);
        setName("Time Freeze");
        addImage("/Object/boots");
        initialize("TimeFreeze", 3);
    }

    @Override
    public void apply() {

    }
}
