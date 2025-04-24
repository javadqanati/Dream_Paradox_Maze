package UI;

import Audio.AudioManager;
import Launcher.GamePanel;

import java.awt.*;

public class PauseScreen extends Screen {
    private final Font arial_80B = new Font("Arial", Font.BOLD, 50);

    public PauseScreen(GamePanel gp) {
        super(gp);
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setFont(arial_80B);
        g2.setColor(Color.white);
        String text = "Game Paused";
        int x = getXforCenteredText(text, g2);
        int y = getGp().getTileSize() * 3;
        g2.drawString(text, x, y);


        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 36F));
        text = "Inventory";
        x = getXforCenteredText(text, g2);
        y += (int) (getGp().getTileSize() * 3.5);
        g2.drawString(text, x, y);
        if(getCommandNum() == 0){
            g2.drawString(">", x - getGp().getTileSize(), y);
        }

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 36F));
        text = "Market";
        x = getXforCenteredText(text, g2);
        y += getGp().getTileSize();
        g2.drawString(text, x, y);
        if(getCommandNum() == 1){
            g2.drawString(">", x - getGp().getTileSize(), y);
        }

        text = "Resume";
        x = getXforCenteredText(text, g2);
        y += getGp().getTileSize();
        g2.drawString(text, x, y);
        if(getCommandNum() == 2){
            g2.drawString(">", x - getGp().getTileSize(), y);
        }

        text = "Back to Menu";
        x = getXforCenteredText(text, g2);
        y += getGp().getTileSize();
        g2.drawString(text, x, y);
        if(getCommandNum() == 3){
            g2.drawString(">", x - getGp().getTileSize(), y);
        }
    }
}
