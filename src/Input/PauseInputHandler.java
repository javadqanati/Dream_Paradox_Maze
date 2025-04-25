package Input;

import Launcher.GamePanel;
import UI.Screen;

import java.awt.event.KeyEvent;

public class PauseInputHandler extends ScreenInputHandler{

    public PauseInputHandler(KeyboardInputHandler keyboard, Screen screen, GamePanel gp) {
        super(keyboard, screen, gp);

        bindKeys();
    }

    @Override
    public void bindKeys() {
        getKeyboard().bindKey(KeyEvent.VK_UP, () -> getScreen().setCommandNum(getScreen().getCommandNum() - 1));
        getKeyboard().bindKey(KeyEvent.VK_DOWN, () -> getScreen().setCommandNum(getScreen().getCommandNum() + 1));
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
