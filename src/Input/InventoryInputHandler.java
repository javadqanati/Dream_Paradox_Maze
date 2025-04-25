package Input;

import Launcher.GamePanel;
import UI.Screen;

import java.awt.event.KeyEvent;

public class InventoryInputHandler extends ScreenInputHandler {
    public InventoryInputHandler(KeyboardInputHandler keyboard, Screen screen, GamePanel gp) {
        super(keyboard, screen, gp);
        bindKeys();
    }

    @Override
    public void bindKeys() {
        getKeyboard().bindKey(KeyEvent.VK_UP, () -> getScreen().setCommandNum(getScreen().getCommandNum() - 1));
        getKeyboard().bindKey(KeyEvent.VK_DOWN, () -> getScreen().setCommandNum(getScreen().getCommandNum() + 1));

        getKeyboard().bindKey(KeyEvent.VK_ENTER, this::handleInventorySelection);
    }

    private void handleInventorySelection() {
        switch (getScreen().getCommandNum()) {
            case 0 -> getGp().getGameStateManager().setPause();
            case 1 -> usePowerUp("SpeedBoost");
            case 2 -> usePowerUp("TimeFreeze");
            case 3 -> usePowerUp("ExtraLife");
        }
    }

    private void usePowerUp(String powerUpName) {
        boolean used = getGp().getPlayer().usePowerUp(powerUpName);
        getScreen().setSelectionMessage(used ? powerUpName + " activated!" : "You don't have a " + powerUpName + "!");
    }
}
