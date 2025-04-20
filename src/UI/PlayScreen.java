package UI;

import Game.GameEntities.GameEntities;
import Game.GameEntities.MemoryFragment;
import Launcher.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class PlayScreen extends Screen {
    private final BufferedImage memoryFragmentImg;
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
        GameEntities memoryFragment = new MemoryFragment(gp);
        memoryFragmentImg = memoryFragment.getImage();
    }

    public void setGameFinished(boolean finished) {
        this.gameFinished = finished;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    @Override
    public void draw(Graphics2D g2) {
        if (gameFinished) {
            g2.setFont(arial_80B);
            g2.setColor(Color.yellow);
            String text = "You won this mind trap!";
            int textLength = g2.getFontMetrics().stringWidth(text);
            int x = getGp().getScreenWidth() / 2 - textLength / 2;
            int y = getGp().getScreenHeight() / 2 + (getGp().getTileSize() * 3);
            g2.drawString(text, x, y);
            getGp().setGameThread(null);
        } else {
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            g2.drawImage(memoryFragmentImg, getGp().getTileSize() / 2, getGp().getTileSize() / 2, getGp().getTileSize(), getGp().getTileSize(), null);
            g2.drawString("x " + getGp().getPlayer().getCollectedFragments(), 74, 65);
            playTime += 1.0 / 60;
            g2.drawString("Time: " + decimalFormat.format(playTime) + "s", getGp().getTileSize() * 11, 65);

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
