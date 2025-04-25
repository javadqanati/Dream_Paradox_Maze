package Input;

import Launcher.GamePanel;
import UI.Screen;

import java.awt.event.KeyEvent;

public class PlayScreenInputHandler extends ScreenInputHandler {

    public PlayScreenInputHandler(KeyboardInputHandler keyboard, Screen screen, GamePanel gp) {
        super(keyboard, screen, gp);
        bindKeys();
        bindOptionKeys();
    }

    public void bindOptionKeys() {
        getKeyboard().bindKey(KeyEvent.VK_P, this::togglePause);
    }

    private void togglePause() {
        getGp().getGameStateManager().setPause();
    }
}
