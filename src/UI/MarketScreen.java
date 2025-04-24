package UI;


import Game.GameEntities.Player;
import Game.GameEntities.PowerUp;
import Launcher.GamePanel;
import Market.PowerUpShop;

import java.awt.*;
import java.util.Map;

public class MarketScreen extends Screen {
    private final Font arial_80B = new Font("Arial", Font.BOLD, 50);
    private final Font arial_40B = new Font("Arial", Font.BOLD, 30); // Memory fragment costs for each power-up

    public MarketScreen(GamePanel gp) {
        super(gp);
    }

    @Override
    public void draw(Graphics2D g2) {
        Player player = getGp().getPlayer();
        int tileSize = getGp().getTileSize();

        // Title
        g2.setFont(arial_80B);
        g2.setColor(Color.white);
        String text = "MARKET";
        int titleX = getXforCenteredText(text, g2);
        int y = tileSize * 2;
        g2.drawString(text, titleX, y);

        // Back button
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 36F));
        text = "BACK";
        int backX = tileSize * 2;
        y += tileSize;
        g2.drawString(text, backX, y);
        if (getCommandNum() == 0) {
            g2.drawString(">", backX - tileSize, y);  // Cursor for back button
        }

        // Display available power-ups and their costs
        g2.setFont(arial_40B);
        int startY = y + tileSize;  // Starting point for displaying power-ups

        // Ensure the PowerUpShop is initialized and available
        Map<String, PowerUp> powerUps = PowerUpShop.getAvailablePowerUps(); // Accessing the static map

        // Calculate dynamic space for power-ups based on number of items
        int powerUpSpacing = tileSize + 10;  // Space between power-ups
        int powerUpsCount = powerUps.size(); // Number of power-ups to display
        int totalPowerUpHeight = powerUpSpacing * powerUpsCount;

        // Loop to display each power-up
        int i = 0;
        for (Map.Entry<String, PowerUp> entry : powerUps.entrySet()) {
            String powerUpName = entry.getKey();
            PowerUp powerUp = entry.getValue();

            // Calculate Y position for current power-up
            int currentY = startY + (i * powerUpSpacing);

            // Draw power-up name and cost
            String powerUpText = powerUpName + " - Cost: " + powerUp.getCost(); // Assuming PowerUp has a getCost() method
            g2.drawString(powerUpText, tileSize * 2, currentY); // Spacing between power-ups

            // Add cursor for selected power-up
            if (getCommandNum() == i + 1) {
                g2.drawString(">", tileSize, currentY);  // Cursor for selected power-up
            }

            i++;
        }

        // Adjust Y position for memory fragments based on the number of power-ups displayed
        int fragmentTextY = startY + totalPowerUpHeight + tileSize;

        // Show player's memory fragments
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30F));
        String fragmentText = "Memory Fragments: " + player.getCollectedFragments();
        g2.drawString(fragmentText, tileSize * 2, fragmentTextY);  // Adjusted position based on power-ups
    }



}
