package UI;

import Utils.GameStateManager;
import java.awt.*;
import java.util.Map;

public class HUDRenderer {
    private final GameStateManager gsm;
    private final Map<String, Screen> screens;

    public HUDRenderer(GameStateManager gsm, Map<String, Screen> screens) {
        this.gsm = gsm;
        this.screens = screens;
    }

    public void draw(Graphics2D g2) {
        Screen s = screens.get(gsm.getStateName());
        if (s != null) s.draw(g2);
    }
}