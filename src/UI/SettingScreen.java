package UI;

import Audio.AudioManager;
import Launcher.GamePanel;
import java.awt.*;

public class SettingScreen extends Screen {
    private final String[] options = {"Full Screen", "Music", "Sound Effects", "Back"};


    public SettingScreen(GamePanel gp) {
        super(gp, "SETTINGS");
    }

    @Override
    public void draw(Graphics2D g2){
        super.draw(g2);
        g2.setFont(getScreenfont().deriveFont(Font.BOLD, 50f));
        g2.setColor(Color.white);

        String title = "Settings";
        int x = getXforCenteredText(title, g2);
        int y = getGp().getTileSize() * 2;
        g2.drawString(title, x, y);

        y += getGp().getTileSize() * 2;
        g2.setFont(getScreenfont().deriveFont(36f));
        for (int i = 0; i < options.length; i++) {
            String text = options[i];
            if (text.equals("Full Screen")) {
                text += ": " + (getGp().getPersistence().isFullScreenOn() ? "ON" : "OFF");
            } else if (text.equals("Music")) {
                text += ": " + (AudioManager.isMusicMuted() ? "OFF" : "ON");
            } else if (text.equals("Sound Effects")) {
                text += ": " + (AudioManager.isSfxMuted() ? "OFF" : "ON");
            }


            x = getXforCenteredText(text, g2);

            g2.drawString(text, x, y);

            if (getCommandNum() == i) {
                g2.drawString(">", x - getGp().getTileSize(), y);
            }

            y += getGp().getTileSize();
        }
    }
}

