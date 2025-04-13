package GameStates;

import java.awt.*;

public class PausedState implements GameStateHandler {
    @Override
    public void update() {
        // Logic for PAUSED state (maybe nothing or show pause menu interactions)
    }

    @Override
    public void render(Graphics g) {
        // Render the paused screen overlay
        g.setColor(new Color(0, 0, 0, 150));
        g.fillRect(0, 0, 800, 600); // Example fullscreen transparent overlay
        g.setColor(Color.WHITE);
        g.drawString("Game Paused", 350, 300);
    }
}
