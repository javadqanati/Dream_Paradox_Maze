package GameStates;

import java.awt.*;

public class PlayingState implements GameStateHandler {
    @Override
    public void update() {
        // Update game logic when in PLAYING state (e.g., player movement, collisions)
    }

    @Override
    public void render(Graphics g) {
        // Draw game screen when in PLAYING state
        g.setColor(Color.GREEN);
        g.drawString("Playing...", 20, 20);
    }
}