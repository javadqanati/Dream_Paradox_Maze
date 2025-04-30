package Launcher;

import java.awt.Graphics2D;
import Game.GameEntities.Entity;
import Game.GameEntities.Enemy;

public class GameRenderer {
    private final GamePanel gp;

    public GameRenderer(GamePanel gp) {
        this.gp = gp;
    }

    public void render(Graphics2D g2) {
        if (gp.getGameStateManager().isPlaying() || gp.getGameStateManager().isStory()) {
            gp.getMaze().draw(g2);
            for (Entity e : gp.getGameEntities()) {
                if (e != null) e.draw(g2);
            }
            for (Enemy en : gp.getEnemies()) {
                if (en != null) en.draw(g2);
            }
            gp.getPlayer().draw(g2);
        }

        gp.getHud().draw(g2);
    }
}
