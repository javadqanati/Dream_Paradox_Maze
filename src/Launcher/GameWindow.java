package Launcher;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class GameWindow {
    private JFrame window;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private final Map<String, JPanel> screens = new HashMap<>();


    public void createWindow(Dimension preferredSize) {
        window = new JFrame("Maze Paradox");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setPreferredSize(preferredSize);

        for (Map.Entry<String, JPanel> entry : screens.entrySet()) {
            cardPanel.add(entry.getValue(), entry.getKey());
        }

        window.setContentPane(cardPanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    public void createWindow() {
        if (!screens.isEmpty()) {
            JPanel sample = screens.values().iterator().next();
            createWindow(sample.getPreferredSize());
        } else {
            createWindow(new Dimension(800, 600));
        }
    }

    public void registerScreen(String name, JPanel panel) {
        screens.put(name, panel);
        if (cardPanel != null && cardLayout != null) {
            cardPanel.add(panel, name);
            cardPanel.revalidate();
            cardPanel.repaint();
        }
    }

    public void showScreen(String name) {
        if (cardLayout != null && cardPanel != null) {
            cardLayout.show(cardPanel, name);
            update();
        }
    }

    public void update() {
        if (window != null) {
            window.repaint();
            window.revalidate();
        }
    }

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
