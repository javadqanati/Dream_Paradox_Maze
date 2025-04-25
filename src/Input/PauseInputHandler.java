package Input;

import Launcher.GamePanel;
import UI.Screen;

import java.awt.event.KeyEvent;
import java.util.Arrays;

public class PauseInputHandler extends ScreenInputHandler{

    public PauseInputHandler(KeyboardInputHandler keyboard, Screen screen, GamePanel gp) {
        super(keyboard, screen, gp);

        bindKeys();
    }

    @Override
    public void bindKeys() {
        getScreen().setOptions(Arrays.asList(
                "Inventory",
                "Market",
                "Resume",
                "Quit"
        ));

        bindNavigationKeys();
        bindOptionKeys();
    }

    public void bindOptionKeys() {
        getKeyboard().bindKey(KeyEvent.VK_P, this::togglePause);
        getKeyboard().bindKey(KeyEvent.VK_ENTER, this::handlePauseSelection);
    }

    private void handlePauseSelection() {
        switch (getScreen().getCommandNum()) {
            case 0: getGp().getGameStateManager().setInventory(); break;
            case 1: getGp().getGameStateManager().setMarket(); break;
            case 2: getGp().getGameStateManager().setPlay(); break;
            case 3: getGp().getGameStateManager().setMenu(); break;
            default: break;
        }
    }

    private void togglePause() {
        if (getGp().getGameStateManager().isPlaying()) {
            getGp().getGameStateManager().setPause();
        } else {
            getGp().getGameStateManager().setPlay();
        }
    }
}
