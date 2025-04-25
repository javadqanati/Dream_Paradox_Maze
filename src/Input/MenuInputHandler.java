package Input;

import Launcher.GamePanel;
import UI.Screen;
import java.awt.event.KeyEvent;

public class MenuInputHandler extends ScreenInputHandler{

    public MenuInputHandler(GamePanel gp, Screen screen, KeyboardInputHandler keyboard) {
        super(keyboard, screen, gp);

        bindKeys();
    }

    @Override
    public void bindKeys() {
        getKeyboard().bindKey(KeyEvent.VK_UP, () -> getScreen().setCommandNum(getScreen().getCommandNum() - 1));
        getKeyboard().bindKey(KeyEvent.VK_DOWN, () -> getScreen().setCommandNum(getScreen().getCommandNum() + 1));

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
