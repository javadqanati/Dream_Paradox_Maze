package UI;


import Game.GameEntities.Player;
import Game.GameEntities.Powerup.PowerUp;
import Launcher.GamePanel;
import Trade.PowerUpShop;

import java.awt.*;
import java.util.Map;

public class MarketScreen extends Screen {

    public MarketScreen(GamePanel gp) {
        super(gp, "MARKET");
    }

    @Override
    public void draw(Graphics2D g2) {
        Player player = getGp().getPlayer();
        int tileSize = getGp().getTileSize();

        g2.setFont(getScreenfont().deriveFont(Font.BOLD, 50f));
        g2.setColor(Color.white);
        String text = "MARKET";
        int titleX = getXforCenteredText(text, g2);
        int y = tileSize * 2;
        g2.drawString(text, titleX, y);


        g2.setFont(getScreenfont().deriveFont(Font.BOLD, 36f));
        text = "BACK";
        int backX = tileSize * 2;
        y += tileSize;
        g2.drawString(text, backX, y);
        if (getCommandNum() == 0) {
            g2.drawString(">", backX - tileSize, y);
        }

        int startY = y + tileSize;
        Map<String, PowerUp> powerUps = PowerUpShop.getAvailablePowerUps();
        int powerUpSpacing = tileSize + 10;
        int powerUpsCount = powerUps.size();
        int totalPowerUpHeight = powerUpSpacing * powerUpsCount;
        int i = 0;

        for (Map.Entry<String, PowerUp> entry : powerUps.entrySet()) {
            String powerUpName = entry.getKey();
            PowerUp powerUp = entry.getValue();
            int currentY = startY + (i * powerUpSpacing);

            String powerUpText = powerUpName + " - Cost: " + powerUp.getCost();
            g2.drawString(powerUpText, tileSize * 2, currentY);

            if (getCommandNum() == i + 1) {
                g2.drawString(">", tileSize, currentY);
            }

            i++;
        }

        int fragmentTextY = startY + totalPowerUpHeight + tileSize;

        g2.setFont(getScreenfont().deriveFont(Font.PLAIN, 30F));
        String fragmentText = "Memory Fragments: " + player.getCollectedFragments();
        g2.drawString(fragmentText, tileSize * 2, fragmentTextY);
    }
}