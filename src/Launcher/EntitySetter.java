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
        gp.getGameEntities()[0].setWorldX(23 * gp.getTileSize());
        gp.getGameEntities()[0].setWorldY(7 * gp.getTileSize());

        gp.getGameEntities()[1] = new Exit(gp);
        gp.getGameEntities()[1].setWorldX(14 * gp.getTileSize());
        gp.getGameEntities()[1].setWorldY(40  * gp.getTileSize());

        gp.getGameEntities()[2] = new MemoryFragment(gp);
        gp.getGameEntities()[2].setWorldX(37 * gp.getTileSize());
        gp.getGameEntities()[2].setWorldY(7  * gp.getTileSize());

        gp.getGameEntities()[3] = new MemoryFragment(gp);
        gp.getGameEntities()[3].setWorldX(10 * gp.getTileSize());
        gp.getGameEntities()[3].setWorldY(9  * gp.getTileSize());

        gp.getGameEntities()[4] = new MemoryFragment(gp);
        gp.getGameEntities()[4].setWorldX(8 * gp.getTileSize());
        gp.getGameEntities()[4].setWorldY(28  * gp.getTileSize());

        gp.getGameEntities()[5] = new MemoryFragment(gp);
        gp.getGameEntities()[5].setWorldX(12 * gp.getTileSize());
        gp.getGameEntities()[5].setWorldY(22  * gp.getTileSize());

        gp.getGameEntities()[6] = new SpeedBoost(gp);
        gp.getGameEntities()[6].setWorldX(37 * gp.getTileSize());
        gp.getGameEntities()[6].setWorldY(42  * gp.getTileSize());
    }
}
