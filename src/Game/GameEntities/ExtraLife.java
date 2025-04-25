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
        Player p = getGp().getPlayer();
        if(p.getHealth() == p.getMaxHealth()) {
            p.setMaxHealth(p.getMaxHealth() + 2);
        }
        if(p.getHealth() < p.getMaxHealth()) {
            p.setHealth(p.getHealth() + 2);
        }
    }

    @Override
    public PowerUp createNewInstance() {
        return new ExtraLife(getGp());
    }
}
