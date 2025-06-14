package UI;


import Game.GameEntities.Player;
import Game.GameEntities.Powerup.PowerUp;
import Game.GameEntities.Powerup.PowerUpFactory;
import Launcher.GamePanel;

import java.awt.*;
import java.util.Map;
import java.util.function.Function;

public class MarketScreen extends Screen {

    public MarketScreen(GamePanel gp) {
        super(gp, "MARKET");
    }

    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);
        Player player = getGp().getPlayer();
        int tileSize = getGp().getTileSize();

        g2.setFont(getScreenfont().deriveFont(Font.BOLD, 50f));
        g2.setColor(Color.white);
        String text = "MARKET";
        int x = getXforCenteredText(text, g2);
        int y = tileSize * 2;
        g2.drawString(text, x, y);


        g2.setFont(getScreenfont().deriveFont(Font.BOLD, 37f));
        text = "BACK";
        int backX = tileSize * 2;
        y += tileSize;
        g2.drawString(text, backX, y);
        if (getCommandNum() == 0) {
            g2.drawString(">", backX - tileSize, y);
        }

        int startY = y + tileSize;
        Map<String, Function<GamePanel, ? extends PowerUp>> powerUps = PowerUpFactory.getAvailablePowerUpNames();
        int powerUpSpacing = tileSize + 10;
        int powerUpsCount = powerUps.size();
        int totalPowerUpHeight = powerUpSpacing * powerUpsCount;
        int i = 0;

        for (Map.Entry<String, Function<GamePanel, ? extends PowerUp>> entry : powerUps.entrySet()) {
            PowerUp powerUp = entry.getValue().apply(getGp());
            String powerUpName = powerUp.getName();
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