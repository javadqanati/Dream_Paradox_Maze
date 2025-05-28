package Input;

import Utils.GameStateManager;
import Launcher.GamePanel;
import UI.*;

import java.util.Map;

public class InputManager {

    public InputManager(GamePanel gp,
                        GameStateManager gsm,
                        KeyboardInputHandler keyboard,
                        PlayerInputHandler playerHandler,
                        Map<String, Screen> screens) {

        new PlayScreenInputHandler(keyboard, screens.get("PLAY"), gp);
        new PauseInputHandler(keyboard, screens.get("PAUSE"), gp);
        new MenuInputHandler(keyboard, screens.get("MENU"), gp);
        new SettingScreenInputHandler(keyboard, screens.get("SETTINGS"), gp);
        new InventoryInputHandler(keyboard, screens.get("INVENTORY"), gp);
        new MarketInputHandler(keyboard, screens.get("MARKET"), gp);
        new GameOverInputHandler(keyboard, screens.get("GAMEOVER"), gp);

        InputHandler inputHandler = new InputHandler(gsm, keyboard, playerHandler);
        gp.addKeyListener(inputHandler);
    }
}
