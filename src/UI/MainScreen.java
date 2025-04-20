package UI;

import Launcher.GamePanel;

import java.awt.*;

public class MainScreen extends Screen {
    private final Font arial_40B = new Font("Arial", Font.BOLD, 40);
    private Graphics2D g2;
    public MainScreen(GamePanel gp) {
        super(gp);
    }

    @Override
    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 60F));
        g2.setColor(Color.white);
        String text = "Maze Dream Paradox";
        int textLength = g2.getFontMetrics().stringWidth(text);
        int x = getXforCenteredText(text);
        int y = getGp().getTileSize() * 3;
        g2.drawString(text, x, y);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 36F));
        text = "NEW GAME";
        x = getXforCenteredText(text);
        y += (int) (getGp().getTileSize() * 3.5);
        g2.drawString(text, x, y);
        if(getCommandNum() == 0){
            g2.drawString(">", x - getGp().getTileSize(), y);
        }

        text = "LOAD GAME";
        x = getXforCenteredText(text);
        y += getGp().getTileSize();
        g2.drawString(text, x, y);
        if(getCommandNum() == 1){
            g2.drawString(">", x - getGp().getTileSize(), y);
        }

        text = "SETTINGS";
        x = getXforCenteredText(text);
        y += getGp().getTileSize();
        g2.drawString(text, x, y);
        if(getCommandNum() == 2){
            g2.drawString(">", x - getGp().getTileSize(), y);
        }

        text = "QUIT";
        x = getXforCenteredText(text);
        y += getGp().getTileSize();
        g2.drawString(text, x, y);
        if(getCommandNum() == 3){
            g2.drawString(">", x - getGp().getTileSize(), y);
        }
    }

    private int getXforCenteredText(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = getGp().getScreenWidth() / 2 - length / 2;
        return x;
    }
}