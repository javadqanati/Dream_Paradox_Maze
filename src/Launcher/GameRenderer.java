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
        // Get the panel's current physical dimensions
        int screenWidth = gp.getWidth();
        int screenHeight = gp.getHeight();

        // Calculate scaling factors
        double scaleX = (double) screenWidth / gp.getOriginalScreenWidth();
        double scaleY = (double) screenHeight / gp.getOriginalScreenHeight();
        g2.scale(scaleX, scaleY);

        // Existing rendering logic (uses original tile/screen dimensions)
        if (gp.getGameStateManager().isPlaying() || gp.getGameStateManager().isStory()) {
            gp.getMaze().draw(g2);
            for (Entity e : gp.getEntitySetter().getEntities()) e.draw(g2);
            for (Enemy en : gp.getEntitySetter().getEnemies()) en.draw(g2);
            gp.getPlayer().draw(g2);
        }
        gp.getHud().draw(g2);

        // Reset scaling for future frames
        g2.dispose();
    }
}
