package Input;

import Launcher.GamePanel;
import UI.Screen;
import java.awt.event.KeyEvent;
import java.util.Arrays;

public class MenuInputHandler extends ScreenInputHandler{

    public MenuInputHandler(GamePanel gp, Screen screen, KeyboardInputHandler keyboard) {
        super(keyboard, screen, gp);
        screen.setMenuOptions(Arrays.asList(
                "Play",
                "Load",
                "Settings",
                "Quit"
        ));
        bindNavigationKeys();
        bindOptionKeys();
    }

    public void bindOptionKeys() {
        getKeyboard().bindKey(KeyEvent.VK_ENTER, this::handleMenuSelection);
    }

    private void handleMenuSelection() {
        switch (getScreen().getCommandNum()) {
            case 0: getGp().getGameStateManager().setPlay(); break;
            case 1: getGp().getGameStateManager().setLoad(); break;
            case 2: getGp().getGameStateManager().setSettings(); break;
            case 3: System.exit(0); break;
            default: break;
        }
    }
}
