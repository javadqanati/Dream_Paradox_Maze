package Input;

import Game.GameStates.GameStateManager;
import Launcher.GamePanel;
import UI.Screen;

import java.util.Map;

public class InputManager {
    private final InputHandler inputHandler;

    public InputManager(GamePanel gp,
                        GameStateManager gsm,
                        KeyboardInputHandler keyboard,
                        PlayerInputHandler playerHandler,
                        Map<String, Screen> screens) {

        this.inputHandler = new InputHandler(gp, gsm, keyboard, playerHandler, screens);
        gp.addKeyListener(inputHandler);
        gp.setFocusable(true);
    }

    public InputHandler getInputHandler() {
        return inputHandler;
    }
}
