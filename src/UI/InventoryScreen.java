package UI;

import Game.GameEntities.Player;
import Launcher.GamePanel;

import java.awt.*;

public class InventoryScreen extends Screen {
    private final Font arial_80B = new Font("Arial", Font.BOLD, 50);

    public InventoryScreen(GamePanel gp) {
        super(gp);
    }

    @Override
    public void draw(Graphics2D g2) {
        Player player = getGp().getPlayer();
        int tileSize = getGp().getTileSize();

        // Title
        g2.setFont(arial_80B);
        g2.setColor(Color.white);
        String text = "INVENTORY";
        int titleX = getXforCenteredText(text, g2);
        int y = tileSize * 2;
        g2.drawString(text, titleX, y);

        // Back button
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 36F));
        text = "BACK";
        int backX = tileSize * 2;
        y += tileSize * 2;
        g2.drawString(text, backX, y);
        if (getCommandNum() == 0) {
            g2.drawString(">", backX - tileSize, y);
        }

        // Items and descriptions
        String[] powerUpNames = {"Speed Boost", "Time Freeze", "Extra Life"};
        Font itemFont = g2.getFont().deriveFont(Font.PLAIN, 30F);
        g2.setFont(itemFont);
        int index = 1;
        y += tileSize;

        String selectedDescription = "";
        String selectedItemName = null;
        int leftX = tileSize * 2;

        for (String powerUpName : powerUpNames) {
            int count = (int) player.getPowerUps().stream()
                    .filter(p -> p.getClass().getSimpleName().equals(powerUpName))
                    .count();

            text = powerUpName + " x" + count;
            g2.drawString(text, leftX, y);

            if (getCommandNum() == index) {
                g2.drawString(">", leftX - tileSize, y);
                selectedDescription = getPowerUpDescription(powerUpName);
                selectedItemName = powerUpName;
            }

            y += tileSize + 10;
            index++;
        }

        // Draw contextual info and feedback message
        if (selectedItemName != null) {
            int msgX = getGp().getScreenWidth() / 2 + tileSize;
            int msgY = tileSize * 5;

            // Draw selected name
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
            g2.setColor(Color.YELLOW);
            g2.drawString("Selected: " + selectedItemName, msgX, msgY);

            // Draw description
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 24F));
            g2.setColor(Color.WHITE);
            msgY += tileSize;
            g2.drawString(selectedDescription, msgX, msgY);

            // Draw feedback message
            String message = getSelectionMessage();
            if (message != null && !message.isEmpty()) {
                msgY += tileSize + 10;
                g2.setFont(g2.getFont().deriveFont(Font.ITALIC, 22F));
                g2.setColor(Color.GREEN);
                g2.drawString(message, msgX, msgY);
                System.out.println("message written");
            }else{
                System.out.println("message is null or empty");
            }
        }
    }


    private String getPowerUpDescription(String powerUpName) {
        return switch (powerUpName) {
            case "Speed Boost" -> "Temporarily makes you faster!";
            case "Time Freeze" -> "Freezes all enemies for a few seconds.";
            case "Extra Life" -> "Gives you one additional life.";
            default -> "";
        };
    }
}
