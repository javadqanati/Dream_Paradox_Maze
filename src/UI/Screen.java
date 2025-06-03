package UI;

import Launcher.GamePanel;
import java.util.List;
import java.awt.*;

public class Screen implements CustomFontProvider {
    private final GamePanel gp;
    private int commandNum = 0;
    private List<String> options;
    private final Font font;
    private String notification = "";
    private int    notificationTimer = 0;
    private static final int NOTIFICATION_DURATION = 120;

    public Screen(GamePanel gp, String stateName) {
        this.gp = gp;
        font = getCustomFont();
        ScreenManager.register(stateName, this);
    }


    public int getXforCenteredText(String text, Graphics g2) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return getGp().getScreenWidth() / 2 - length / 2;
    }

    public void draw(Graphics2D g2){
        Color darkBlue = new Color(15, 28, 67);
        g2.setColor(darkBlue);
        g2.fillRect(0, 0, gp.getScreenWidth(), gp.getScreenHeight());

        if (!notification.isEmpty()) {
            g2.setFont(font.deriveFont(Font.BOLD, 28f));
            g2.setColor(Color.YELLOW);
            int nx = (int) (getGp().getTileSize() * 8.5);
            int ny = (int) (getGp().getTileSize() * 4.5);
            g2.drawString(notification, nx, ny);

            if (++notificationTimer > NOTIFICATION_DURATION) {
                notification = "";
                notificationTimer = 0;
            }
        }
    }

    public GamePanel getGp() {
        return gp;
    }

    public int getCommandNum() {
        return commandNum;
    }

    public void setCommandNum(int commandNum) {
        this.commandNum = commandNum;
    }

    public void showNotification(String msg) {
        this.notification = msg;
        this.notificationTimer = 0;
    }

    public void resetTimer(){

    }

    public void loadStory(String storyPath) {}

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public int getNumScreenOptions() {
        return options == null ? 0 : options.size();
    }

    public Font getScreenfont() {
        return font;
    }

}
