package UI;

import Launcher.GamePanel;

import java.awt.*;

abstract class UIComponent {
    private GamePanel gp;
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean visible = true;

    public UIComponent(GamePanel gp) {
        this.gp = gp;
    }

    public void draw(Graphics g) {}

    public GamePanel getGp() {
        return gp;
    }

    public void setGp(GamePanel gp) {
        this.gp = gp;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
