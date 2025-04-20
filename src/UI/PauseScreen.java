package UI;

import Launcher.GamePanel;

import java.awt.*;

public class PauseScreen extends Screen {
    private final Font arial_80B = new Font("Arial", Font.BOLD, 40);

    public PauseScreen(GamePanel gp) {
        super(gp);
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setFont(arial_80B);
        g2.setColor(Color.white);
        String text = "Game Paused";
        int textLength = g2.getFontMetrics().stringWidth(text);
        int x = getGp().getScreenWidth() / 2 - textLength / 2;
        int y = getGp().getScreenHeight() / 2;
        g2.drawString(text, x, y);
    }
}
