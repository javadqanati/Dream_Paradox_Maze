package Launcher;

import Game.GameEntities.Entrance;
import Game.GameEntities.Exit;
import Game.GameEntities.MemoryFragment;
import Game.GameEntities.SpeedBoost;

public class EntitySetter {
    private final GamePanel gp;

    public EntitySetter(GamePanel gp) {
        this.gp = gp;
    }

    public void loadEntities() {
        gp.getGameEntities()[0] = new Entrance(gp); // Subtyping
        gp.getGameEntities()[0].setWorldX(26 * gp.getTileSize());
        gp.getGameEntities()[0].setWorldY(11 * gp.getTileSize());

        gp.getGameEntities()[1] = new Exit(gp);
        gp.getGameEntities()[1].setWorldX(45 * gp.getTileSize());
        gp.getGameEntities()[1].setWorldY(51  * gp.getTileSize());

        gp.getGameEntities()[2] = new MemoryFragment(gp);
        gp.getGameEntities()[2].setWorldX(12 * gp.getTileSize());
        gp.getGameEntities()[2].setWorldY(16  * gp.getTileSize());

        gp.getGameEntities()[3] = new MemoryFragment(gp);
        gp.getGameEntities()[3].setWorldX(9 * gp.getTileSize());
        gp.getGameEntities()[3].setWorldY(12  * gp.getTileSize());

        gp.getGameEntities()[4] = new MemoryFragment(gp);
        gp.getGameEntities()[4].setWorldX(13 * gp.getTileSize());
        gp.getGameEntities()[4].setWorldY(27  * gp.getTileSize());

        gp.getGameEntities()[5] = new MemoryFragment(gp);
        gp.getGameEntities()[5].setWorldX(44 * gp.getTileSize());
        gp.getGameEntities()[5].setWorldY(47  * gp.getTileSize());

        gp.getGameEntities()[6] = new SpeedBoost(gp);
        gp.getGameEntities()[6].setWorldX(15 * gp.getTileSize());
        gp.getGameEntities()[6].setWorldY(44  * gp.getTileSize());
    }
}
