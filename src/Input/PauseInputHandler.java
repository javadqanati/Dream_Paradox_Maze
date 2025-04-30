package Input;

import Launcher.GamePanel;
import UI.Screen;
import java.awt.event.KeyEvent;
import java.util.List;

public class PauseInputHandler extends ScreenInputHandler{

    public PauseInputHandler(KeyboardInputHandler keyboard, Screen screen, GamePanel gp) {
        super(keyboard, screen, gp);
        screen.setOptions(List.of("Inventory", "Trade", "Resume", "Back to Menu"));

        setOptionActions(List.of(
                () -> getGp().getGameStateManager().setInventory(),
                () -> getGp().getGameStateManager().setMarket(),
                () -> getGp().getGameStateManager().setPlay(),
                () -> getGp().getGameStateManager().setMenu()
        ));
        getScreen().setCommandNum(0);
        bindKeys();
        bindOptionKeys();
    }

    public void bindOptionKeys() {
        getKeyboard().bindKey(KeyEvent.VK_P, this::togglePause);
    }

    private void togglePause() {
        if (getGp().getGameStateManager().isPlaying()) {
            getGp().getGameStateManager().setPause();
        } else {
            getGp().getGameStateManager().setPlay();
        }
    }
}
