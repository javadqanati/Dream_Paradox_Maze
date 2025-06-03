package Input;

import Game.GameEntities.Powerup.PowerUp;
import Launcher.GamePanel;
import UI.Screen;
import java.awt.event.KeyEvent;
import java.util.List;

public class InventoryInputHandler extends ScreenInputHandler {

    public InventoryInputHandler(KeyboardInputHandler keyboard, Screen screen, GamePanel gp) {
        super("INVENTORY", keyboard, screen, gp);
        bindKeys();
    }

    @Override
    public void bindKeys() {
        bindOptionKeys();
    }

    public void bindOptionKeys() {
        getKeyboard().bindKey(KeyEvent.VK_UP, () -> {
            int maxOptions = getGp().getPlayer().getPowerUps().size() + 1;
            int currentCmd = getScreen().getCommandNum();
            int newCmd = (currentCmd <= 0) ? maxOptions - 1 : currentCmd - 1;
            getScreen().setCommandNum(newCmd);
        });

        getKeyboard().bindKey(KeyEvent.VK_DOWN, () -> {
            int maxOptions = getGp().getPlayer().getPowerUps().size() + 1;
            int currentCmd = getScreen().getCommandNum();
            int newCmd = (currentCmd >= maxOptions - 1) ? 0 : currentCmd + 1;
            getScreen().setCommandNum(newCmd);
        });

        getKeyboard().bindKey(KeyEvent.VK_ENTER, this::handleInventorySelection);
    }

    private void handleInventorySelection() {
        int cmd = getScreen().getCommandNum();
        if (cmd == 0) {
            getGp().getGameStateManager().setPause();
        } else {
            List<PowerUp> powerUps = getGp().getPlayer().getPowerUps();
            if (cmd - 1 < powerUps.size()) {
                PowerUp chosen = powerUps.get(cmd - 1);
                getGp().getPlayer().usePowerUp(chosen.getType());
                getScreen().showNotification( chosen.getType() + " activated!");
            }
        }
    }
}