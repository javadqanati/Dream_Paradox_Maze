package UI;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class HUD extends UIComponent {
    private final JLabel livesLabel;
    private final JLabel memoryFragmentsLabel;
    private final JLabel powerUpStatusLabel;

    public HUD(String label, int x, int y, int width, int height) {
        super(x, y, width, height);

        // Initialize labels to display information on the HUD
        livesLabel = new JLabel();
        memoryFragmentsLabel = new JLabel();
        powerUpStatusLabel = new JLabel();

        // Optionally, set fonts, styles, or other properties here (e.g., set font for labels)
        livesLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        memoryFragmentsLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        powerUpStatusLabel.setFont(new Font("Arial", Font.PLAIN, 14));
    }

    // Update HUD labels with new information
    public void updateHud(int lives, int memoryFragments, String powerUpStatus) {
        String livesText = "Lives: " + lives;
        String memoryFragmentsText = "Fragments: " + memoryFragments;
        String powerUpStatusText = "Power-up: " + powerUpStatus;

        livesLabel.setText(livesText);
        memoryFragmentsLabel.setText(memoryFragmentsText);
        powerUpStatusLabel.setText(powerUpStatusText);
    }

    // Display lives label in the container
    public void displayLives(@NotNull Container container) {
        container.add(livesLabel, BorderLayout.NORTH);
    }

    // Display memory fragments label in the container
    public void displayMemoryFragments(@NotNull Container container) {
        container.add(memoryFragmentsLabel, BorderLayout.CENTER);
    }

    // Display power-up status label in the container
    public void displayPowerUpStatus(@NotNull Container container) {
        container.add(powerUpStatusLabel, BorderLayout.SOUTH);
    }

    // Set visibility of HUD components
    public void setVisible(boolean isVisible) {
        livesLabel.setVisible(isVisible);
        memoryFragmentsLabel.setVisible(isVisible);
        powerUpStatusLabel.setVisible(isVisible);
    }
}
