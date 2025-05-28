package UI;

import Audio.AudioManager;
import Launcher.GamePanel;
import java.awt.*;
import java.util.List;

public class SettingScreen extends Screen {
    private final List<SettingOption> options;

    public SettingScreen(GamePanel gp) {
        super(gp, "SETTINGS");

        options = List.of(
                new SettingOption("Full Screen", () -> getGp().getPersistence().isFullScreenOn() ? "ON" : "OFF"),
                new SettingOption("Music", () -> AudioManager.isMusicMuted() ? "OFF" : "ON"),
                new SettingOption("Sound Effects", () -> AudioManager.isSfxMuted() ? "OFF" : "ON"),
                new SettingOption("Back", () -> "")
        );
    }

    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);
        g2.setFont(getScreenfont().deriveFont(Font.BOLD, 50f));
        g2.setColor(Color.white);

        String title = "Settings";
        int x = getXforCenteredText(title, g2);
        int y = getGp().getTileSize() * 2;
        g2.drawString(title, x, y);

        y += getGp().getTileSize() * 2;
        g2.setFont(getScreenfont().deriveFont(36f));

        for (int i = 0; i < options.size(); i++) {
            String text = options.get(i).getDisplayText();
            x = getXforCenteredText(text, g2);
            g2.drawString(text, x, y);

            if (getCommandNum() == i) {
                g2.drawString(">", x - getGp().getTileSize(), y);
            }

            y += getGp().getTileSize();
        }
    }
}
