package Launcher;

import javax.swing.*;
import java.awt.*;

public class WindowManager {
    private final JFrame window;
    private final GamePanel gamePanel;
    private boolean isFullscreen = false;
    private Dimension originalWindowSize;

    public WindowManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.window = new JFrame();
        configureWindow();
    }

    private void configureWindow() {
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Maze Paradox Game");
        window.add(gamePanel);
        window.pack();
        window.setVisible(true);
        window.setLocationRelativeTo(null);
        originalWindowSize = window.getSize();
    }

    public void toggleFullscreen() {
        GraphicsDevice device = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice();

        isFullscreen = !isFullscreen;
        window.dispose();

        if (isFullscreen) {
            window.setUndecorated(true);
            device.setFullScreenWindow(window);
        } else {
            window.setUndecorated(false);
            device.setFullScreenWindow(null);
            window.setSize(originalWindowSize);
            window.setLocationRelativeTo(null);

        }
        gamePanel.getPersistence().setFullScreenOn(isFullscreen);
        window.setVisible(true);
    }

    public JFrame getWindow() {
        return window;
    }
}