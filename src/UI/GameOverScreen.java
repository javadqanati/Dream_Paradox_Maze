package UI;

import Launcher.GamePanel;
import java.awt.*;

public class GameOverScreen extends Screen {

    public GameOverScreen(GamePanel gp) {
        super(gp);
    }

    @Override
    public void draw(Graphics2D g2){
        int tileSize = getGp().getTileSize();

        g2.setFont(getScreenfont());
        g2.setColor(Color.white);
        String text = "GAME OVER!";
        int titleX = getXforCenteredText(text, g2);
        int y = tileSize * 2;
        g2.drawString(text, titleX, y);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 36F));
        text = "BACK TO MENU!";
        int backX = tileSize * 2;
        y += tileSize * 2;
        g2.drawString(text, backX, y);
        if (getCommandNum() == 0) {
            g2.drawString(">", backX - tileSize, y);
        }

        text = "RESTART";
        y += tileSize * 2;
        g2.drawString(text, backX, y);
        if (getCommandNum() == 1) {
            g2.drawString(">", backX - tileSize, y);
        }
    }
}
