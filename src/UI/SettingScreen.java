package UI;

import Audio.AudioManager;
import Launcher.GamePanel;
import java.awt.*;

public class SettingScreen extends Screen {
    private final Font arial_40B = new Font("Arial", Font.BOLD, 40);
    private Graphics2D g2;
    private final String[] options = {"Music", "Sound Effects", "Back"};

    public SettingScreen(GamePanel gp) {
        super(gp);
    }

    @Override
    public void draw(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(arial_40B);
        g2.setColor(Color.white);

        String title = "Settings";
        int x = getXforCenteredText(title);
        int y = getGp().getTileSize() * 2;
        g2.drawString(title, x, y);

        y += getGp().getTileSize() * 2;

        for (int i = 0; i < options.length; i++) {
            String text = options[i];
            if (text.equals("Music")) {
                text += ": " + (AudioManager.isMusicMuted() ? "OFF" : "ON");
            } else if (text.equals("Sound Effects")) {
                text += ": " + (AudioManager.isSfxMuted() ? "OFF" : "ON");
            }

            x = getXforCenteredText(text);
            g2.drawString(text, x, y);

            if (getCommandNum() == i) {
                g2.drawString(">", x - getGp().getTileSize(), y);
            }

            y += getGp().getTileSize();
        }
    }

    private int getXforCenteredText(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return getGp().getScreenWidth() / 2 - length / 2;
    }
}

