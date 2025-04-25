package Game.GameEntities;

import Launcher.GamePanel;

public class ExtraLife extends PowerUp{
    public ExtraLife(GamePanel gp) {
        super(gp);
        setName("Extra Life");
        addImage("/Object/boots");
        initialize("ExtraLife", 3);
    }

    @Override
    public void apply() {

    }

    @Override
    public PowerUp createNewInstance() {
        return new ExtraLife(getGp());
    }
}
