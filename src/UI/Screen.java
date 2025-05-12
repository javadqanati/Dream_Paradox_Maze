package UI;

import Launcher.GamePanel;
import java.util.List;
import java.awt.*;

public class Screen implements CustomFontProvider {
    private final GamePanel gp;
    private int commandNum = 0;
    private String selectionMessage;
    private List<String> options;
    private final Font font;

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
    }

    public void resetTimer(){

    }

    public void loadStory(String storyPath) {}

    public void setSelectionMessage(String selectionMessage) {
        this.selectionMessage = selectionMessage;
    }

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
