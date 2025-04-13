package Launcher;


import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class GameWindow {
    private JFrame window;
    private JPanel cardPanel;
    private CardLayout cardLayout;

    private final Map<String, JPanel> screens = new HashMap<>();

    /**
     * Initializes the main game window with a CardLayout and registers the GamePanel.
     */
    public void createWindow(@NotNull GamePanel gamePanel) {
        window = new JFrame("Maze Paradox");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        cardPanel.setPreferredSize(gamePanel.getPreferredSize());

        // Add all registered screens to the card panel
        for (Map.Entry<String, JPanel> entry : screens.entrySet()) {
            cardPanel.add(entry.getValue(), entry.getKey());
        }

        window.setContentPane(cardPanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    /**
     * Register a new screen (menu or game) with a unique name.
     */
    public void registerScreen(String name, JPanel panel) {
        screens.put(name, panel);
    }

    /**
     * Switch to the screen with the given name.
     */
    public void showScreen(String name) {
        if (cardLayout != null && cardPanel != null) {
            cardLayout.show(cardPanel, name);
            update();
        }
    }

    /**
     * Repaints and revalidates the window.
     */
    public void update() {
        if (window != null) {
            window.repaint();
            window.revalidate();
        }
    }

    /**
     * Closes the window.
     */
    public void closeWindow() {
        if (window != null) {
            window.dispose();
        }
    }

    public JFrame getWindow() {
        return window;
    }

    public JPanel getCardPanel() {
        return cardPanel;
    }
}
