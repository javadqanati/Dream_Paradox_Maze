package UI;

import Launcher.GamePanel;
import java.util.HashMap;
import java.util.Map;

public class ScreenManager {
    private static final Map<String, Screen> screens = new HashMap<>();

    public static void register(String key, Screen screen) {
        screens.put(key, screen);
    }

    public ScreenManager(GamePanel gp) {
        new MainScreen(gp);
        new PlayScreen(gp);
        new PauseScreen(gp);
        new SettingScreen(gp);
        new InventoryScreen(gp);
        new MarketScreen(gp);
        new GameOverScreen(gp);
        new LoadScreen(gp);
        // "STORY" can reuse "PLAY"
        screens.put("STORY", screens.get("PLAY"));
    }

    public Screen get(String key) {
        return screens.get(key);
    }

    public Map<String, Screen> all() {
        return screens;
    }
}

