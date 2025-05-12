package Launcher;

import Game.GameEntities.Enemy;
import Game.GameEntities.Entity;
import UI.HUDRenderer;

import java.awt.Graphics2D;

public class GameRenderer {
    private final GamePanel gp;

    public GameRenderer(GamePanel gp) {
        this.gp = gp;
    }

    public void render(Graphics2D g2) {
        int screenWidth = gp.getWidth();
        int screenHeight = gp.getHeight();
        double scaleX = (double) screenWidth / gp.getOriginalScreenWidth();
        double scaleY = (double) screenHeight / gp.getOriginalScreenHeight();
        g2.scale(scaleX, scaleY);

        if (gp.getGameStateManager().isPlaying() || gp.getGameStateManager().isStory()) {
            gp.getMaze().draw(g2);
            for (Entity e : gp.getEntitySetter().getEntities()) e.draw(g2);
            for (Enemy en : gp.getEntitySetter().getEnemies()) en.draw(g2);
            gp.getPlayer().draw(g2);
        }

        gp.getHudRenderer().draw(g2);

        g2.dispose();
    }
}