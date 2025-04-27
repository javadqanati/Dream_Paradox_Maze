package UI;

import Launcher.GamePanel;

import java.awt.*;

public class MainScreen extends Screen {

    public MainScreen(GamePanel gp) {
        super(gp);
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 60F));
        g2.setColor(Color.white);
        String text = "Maze Dream Paradox";
        int x = getXforCenteredText(text, g2);
        int y = getGp().getTileSize() * 3;
        g2.drawString(text, x, y);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 36F));
        text = "NEW GAME";
        x = getXforCenteredText(text, g2);
        y += (int) (getGp().getTileSize() * 3.5);
        g2.drawString(text, x, y);
        if(getCommandNum() == 0){
            g2.drawString(">", x - getGp().getTileSize(), y);
        }

        text = "LOAD GAME";
        x = getXforCenteredText(text, g2);
        y += getGp().getTileSize();
        g2.drawString(text, x, y);
        if(getCommandNum() == 1){
            g2.drawString(">", x - getGp().getTileSize(), y);
        }

        text = "SAVE GAME";
        x = getXforCenteredText(text, g2);
        y += getGp().getTileSize();
        g2.drawString(text, x, y);
        if(getCommandNum() == 2){
            g2.drawString(">", x - getGp().getTileSize(), y);
        }

        text = "SETTINGS";
        x = getXforCenteredText(text, g2);
        y += getGp().getTileSize();
        g2.drawString(text, x, y);
        if(getCommandNum() == 3){
            g2.drawString(">", x - getGp().getTileSize(), y);
        }

        text = "QUIT";
        x = getXforCenteredText(text, g2);
        y += getGp().getTileSize();
        g2.drawString(text, x, y);
        if(getCommandNum() == 4){
            g2.drawString(">", x - getGp().getTileSize(), y);
        }
    }
}