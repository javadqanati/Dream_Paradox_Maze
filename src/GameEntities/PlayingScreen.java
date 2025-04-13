package GameEntities;

import Launcher.GamePanel;

import javax.swing.*;
import java.awt.*;

/**
 * PlayScreen wraps the GamePanel and serves as the view for the PLAYING state.
 */
public class PlayingScreen extends JPanel {
    private final GamePanel gamePanel;

    public PlayingScreen(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.setLayout(new BorderLayout());
        this.add(gamePanel, BorderLayout.CENTER);
    }


    public GamePanel getGamePanel() {
        return gamePanel;
    }
}
