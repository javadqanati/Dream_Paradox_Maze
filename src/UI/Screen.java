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
    private final String stateName;

    public Screen(GamePanel gp, String stateName) {
        this.gp = gp;
        font = getCustomFont();
        this.stateName = stateName;
        ScreenManager.register(stateName, this);
    }

    public String getStateName() {
        return stateName;
    }

    public int getXforCenteredText(String text, Graphics g2) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return getGp().getScreenWidth() / 2 - length / 2;
    }

    public void draw(Graphics2D g2){
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

    public String getSelectionMessage() {
        return selectionMessage;
    }

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
