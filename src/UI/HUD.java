package UI;

import Game.GameStates.GameStateManager;
import Launcher.GamePanel;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class HUD {
    private final GamePanel gp;
    private final GameStateManager gsm;
    private final Map<String, Screen> screenMap;

    public HUD(GamePanel gp, GameStateManager gsm) {
        this.gp = gp;
        this.gsm = gsm;

        screenMap = new HashMap<>();
        screenMap.put("PLAY", new PlayScreen(gp));
        screenMap.put("PAUSE", new PauseScreen(gp));
        screenMap.put("MENU", new MainScreen(gp));
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        String stateName = gsm.getStateName();
        Screen screen = screenMap.get(stateName);
        if (screen != null) {
            screen.draw(g2);
        }
    }

    public void setGameFinished(boolean finished) {
        ((PlayScreen) screenMap.get("PLAY")).setGameFinished(finished);
    }

    public void showMessage(String message) {
        ((PlayScreen) screenMap.get("PLAY")).showMessage(message);
    }
}
