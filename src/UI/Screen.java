package UI;

import Launcher.GamePanel;
import java.util.List;
import java.awt.*;

public class Screen {
    private final GamePanel gp;
    private static int commandNum = 0;
    private String selectionMessage;
    private List<String> options;

    public Screen(GamePanel gp) {
        this.gp = gp;
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
        Screen.commandNum = commandNum;
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
}
