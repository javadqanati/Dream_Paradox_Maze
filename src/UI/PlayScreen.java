package UI;

import Game.GameEntities.Entity;
import Game.GameEntities.MemoryFragment;
import Game.GameEntities.PlayerLife;
import Launcher.GamePanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class PlayScreen extends Screen {
    private final BufferedImage memoryFragmentImg;
    private final BufferedImage full_heart, half_heart, heart_blank;

    private final Font arial_40 = new Font("Arial", Font.PLAIN, 40);
    private final Font arial_80B = new Font("Arial", Font.BOLD, 40);
    private final DecimalFormat decimalFormat = new DecimalFormat("#0.00");

    private double playTime = 0;
    private boolean gameFinished = false;
    private boolean messageOn = false;
    private String message = "";
    private int messageCounter = 0;

    public PlayScreen(GamePanel gp) {
        super(gp);

        Entity memoryFragment = new MemoryFragment(gp);
        Entity playerLife = new PlayerLife(gp);

        memoryFragmentImg = memoryFragment.getCurrentSprite(Entity.Direction.DOWN, 0);

        BufferedImage[] heartFrames = playerLife.getSprites().get(Entity.Direction.DOWN);
        full_heart = heartFrames[0];  // Full heart is at index 0
        half_heart = heartFrames[1];  // Half heart is at index 1
        heart_blank = heartFrames[2];
    }

    public void setGameFinished(boolean finished) {
        this.gameFinished = finished;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void drawPlayerLife(Graphics2D g2) {
        int maxLife = getGp().getPlayer().getMaxHealth();
        int currentLife = getGp().getPlayer().getHealth();
        int tileSize = getGp().getTileSize();

        int totalHearts = maxLife / 2;
        int centerX = getGp().getScreenWidth() / 2;
        int totalWidth = totalHearts * tileSize;
        int startX = centerX - (totalWidth / 2);
        int y = tileSize / 2;

        for (int i = 0; i < totalHearts; i++) {
            BufferedImage heart;

            if (currentLife >= 2) {
                heart = full_heart;
                currentLife -= 2;
            } else if (currentLife == 1) {
                heart = half_heart;
                currentLife -= 1;
            } else {
                heart = heart_blank;
            }

            g2.drawImage(heart, startX + (i * tileSize), y, tileSize, tileSize, null);
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        if (gameFinished) {
            g2.setFont(arial_80B);
            g2.setColor(Color.blue);
            String text = "You won this mind trap!";
            int textLength = g2.getFontMetrics().stringWidth(text);
            int x = getGp().getScreenWidth() / 2 - textLength / 2;
            int y = getGp().getScreenHeight() / 2 + (getGp().getTileSize() * 3);
            g2.drawString(text, x, y);
            getGp().setGameThread(null);
        } else {
            g2.setFont(arial_40);
            g2.setColor(Color.white);

            g2.drawImage(memoryFragmentImg, getGp().getTileSize() / 2,
                    getGp().getTileSize() / 2, getGp().getTileSize(), getGp().getTileSize(), null);
            g2.drawString("x " + getGp().getPlayer().getCollectedFragments(), 74, 65);

            playTime += 1.0 / 60;
            g2.drawString("Time: " + decimalFormat.format(playTime) + "s", getGp().getTileSize() * 11, 65);

            drawPlayerLife(g2);

            if (messageOn) {
                g2.setFont(g2.getFont().deriveFont(30f));
                g2.drawString(message, getGp().getTileSize() / 2, getGp().getTileSize() * 5);
                messageCounter++;
                if (messageCounter > 120) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
    }
}
