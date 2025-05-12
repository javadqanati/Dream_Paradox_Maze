package Launcher;

import Game.GameEntities.Enemy;
import Game.GameEntities.Entity;
import javax.swing.*;
import java.awt.*;

public class GameRenderer extends JPanel {
    private static GamePanel gp;

    public GameRenderer(GamePanel gp) {
        GameRenderer.gp = gp;
    }

    public void render(Graphics2D g2) {
        int screenWidth = gp.getWidth();
        int screenHeight = gp.getHeight();
        double scaleX = (double) screenWidth / gp.getOriginalScreenWidth();
        double scaleY = (double) screenHeight / gp.getOriginalScreenHeight();
        g2.scale(scaleX, scaleY);

        if (gp.getGameStateManager().isPlaying() || gp.getGameStateManager().isStory()) {
            gp.getMaze().draw(g2);
            for (Entity e : gp.getEntityManager().getEntities()) e.draw(g2);
            for (Enemy en : gp.getEntityManager().getEnemies()) en.draw(g2);
            gp.getPlayer().draw(g2);
        }

        gp.getHudRenderer().draw(g2);
    }

    public static void update() {
        if (gp.getGameStateManager().isPlaying()) {
            gp.getPlayerManager().getPlayer().update();
            for (Enemy e : gp.getEntityManager().getEnemies()) {
                e.update();
            }
        }
        if (gp.getGameStateManager().isPaused()) {
            gp.getGameStateManager().setPause();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render((Graphics2D) g);
    }
}