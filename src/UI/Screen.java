package UI;

import Launcher.GamePanel;

import java.awt.*;

public abstract class Screen {
    private final GamePanel gp;
    private int commandNum = 0;

    public Screen(GamePanel gp) {
        this.gp = gp;
    }

    public abstract void draw(Graphics2D g2);

    public GamePanel getGp() {
        return gp;
    }

    public int getCommandNum() {
        return commandNum;
    }

    public void setCommandNum(int commandNum) {
        this.commandNum = commandNum;
    }
}
