package UI;

import Launcher.GamePanel;

import java.awt.*;

public class MainScreen extends Screen {
    private String notification = "";
    private int    notificationTimer = 0;
    private static final int NOTIFICATION_DURATION = 120;

    public MainScreen(GamePanel gp) {
        super(gp, "MENU");
    }

    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);

        g2.setFont(getScreenfont().deriveFont(Font.BOLD, 70F));
        g2.setColor(Color.gray);
        String text = "Maze Dream Paradox";
        int x = getXforCenteredText(text, g2);
        int y = getGp().getTileSize() * 3;
        g2.drawString(text, x + 3, y + 3);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        g2.setFont(getScreenfont().deriveFont(36F));
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

        if (!notification.isEmpty()) {
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 28f));
            g2.setColor(Color.YELLOW);
            int nx = getXforCenteredText(notification, g2);
            int ny = (int) (getGp().getTileSize() * 4.5);
            g2.drawString(notification, nx, ny);

            if (++notificationTimer > NOTIFICATION_DURATION) {
                notification = "";
                notificationTimer = 0;
            }
        }
    }

    public void showNotification(String msg) {
        this.notification = msg;
        this.notificationTimer = 0;
    }
}