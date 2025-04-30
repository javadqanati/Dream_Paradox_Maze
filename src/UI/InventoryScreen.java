package UI;

import Game.GameEntities.Player;
import Game.GameEntities.Powerup.PowerUp;
import Launcher.GamePanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryScreen extends Screen {

    public InventoryScreen(GamePanel gp) {
        super(gp);
        updateOptions();
    }

    private void updateOptions() {
        List<String> options = new ArrayList<>();
        options.add("Back");
        getGp().getPlayer().getPowerUps().forEach(pu -> options.add(pu.getName()));
        setOptions(options);
    }

    @Override
    public void draw(Graphics2D g2) {
        updateOptions();
        Player player = getGp().getPlayer();
        int tileSize = getGp().getTileSize();

        // Title
        g2.setFont(getScreenfont().deriveFont(Font.BOLD, 50f));
        g2.setColor(Color.white);
        String text = "INVENTORY";
        int titleX = getXforCenteredText(text, g2);
        int y = tileSize * 2;
        g2.drawString(text, titleX, y);

        g2.setFont(getScreenfont().deriveFont(Font.BOLD, 36f));
        text = "BACK";
        int backX = tileSize * 2;
        y += tileSize * 2;
        g2.drawString(text, backX, y);
        if (getCommandNum() == 0) {
            g2.drawString(">", backX - tileSize, y);
        }

        g2.setFont(getScreenfont().deriveFont(Font.PLAIN, 30F));
        java.util.List<PowerUp> ownedPowerUps = player.getPowerUps(); // Now using actual PowerUp objects
        int startY = y + tileSize;

        if (ownedPowerUps.isEmpty()) {
            g2.drawString("No power-ups owned.", tileSize * 2, startY);
        } else {
            int spacing = tileSize * 2;
            for (int i = 0; i < ownedPowerUps.size(); i++) {
                PowerUp powerUp = ownedPowerUps.get(i);
                String name = powerUp.getName();
                String description = getPowerUpDescription(name);
                int currentY = startY + (i * spacing);

                if (getCommandNum() == i + 1) {
                    g2.drawString(">", tileSize, currentY);
                }

                g2.setFont(getScreenfont().deriveFont(Font.PLAIN, 30F));
                g2.drawString(name, tileSize * 2, currentY);

                g2.setFont(getScreenfont().deriveFont(Font.ITALIC, 24F));
                g2.drawString(description, tileSize * 2 + 30, currentY + 28);
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
