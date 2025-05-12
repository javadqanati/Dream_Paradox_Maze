package UI;

import java.util.Map;

public class HUD {
    private final Map<String, Screen> screens;

    public HUD(Map<String, Screen> sharedScreens) {
        this.screens = sharedScreens;
    }

    public void setGameFinished(boolean finished) {
        ((PlayScreen) screens.get("PLAY")).setGameFinished(finished);
    }

    public void showMessage(String message) {
        ((PlayScreen) screens.get("PLAY")).showMessage(message);
    }
}