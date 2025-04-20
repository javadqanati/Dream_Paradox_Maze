package Game.GameEntities;

import Launcher.GamePanel;

public class PlayerLife extends GameEntities{

    public PlayerLife(GamePanel gp) {
        super(gp);
        setName("Player Life");
        addImage("/Object/heart_full");
        addImage("/Object/heart_half");
        addImage("/Object/heart_blank");
    }
}
