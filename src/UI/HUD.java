package UI;

import Game.GameStates.GameStateManager;
import Launcher.GamePanel;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class HUD {
    private final GameStateManager gsm;
    private final Map<String, Screen> screens;
    private final GamePanel gp;

    public HUD(GamePanel gp, GameStateManager gsm, Map<String, Screen> sharedScreens) {
        this.gsm = gsm;
        this.screens = sharedScreens;
        this.gp = gp;
    }

    public void draw(Graphics2D g2) {
        Screen s = screens.get(gsm.getStateName());
        if (s != null) s.draw(g2);
    }

    public void setGameFinished(boolean finished) {
        ((PlayScreen) screens.get("PLAY")).setGameFinished(finished);
    }

    public void showMessage(String message) {
        ((PlayScreen) screens.get("PLAY")).showMessage(message);
    }

}
