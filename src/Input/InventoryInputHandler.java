package Input;

import Game.GameEntities.PowerUp;
import Launcher.GamePanel;
import UI.Screen;
import java.awt.event.KeyEvent;
import java.util.List;

public class InventoryInputHandler extends ScreenInputHandler {
    public InventoryInputHandler(KeyboardInputHandler keyboard, Screen screen, GamePanel gp) {
        super(keyboard, screen, gp);

        bindKeys();
    }

    @Override
    public void bindKeys() {
        getScreen().setCommandNum(0);
        bindOptionKeys();
    }

    public void bindOptionKeys() {
        getKeyboard().bindKey(KeyEvent.VK_UP, () -> {
            int max = getGp().getPlayer().getPowerUps().size() + 1;
            int cmd = getScreen().getCommandNum() - 1;
            if (cmd < 0) cmd = max - 1;
            getScreen().setCommandNum(cmd);
        });

        getKeyboard().bindKey(KeyEvent.VK_DOWN, () -> {
            int max = getGp().getPlayer().getPowerUps().size() + 1;
            int cmd = getScreen().getCommandNum() + 1;
            if (cmd >= max) cmd = 0;
            getScreen().setCommandNum(cmd);
        });

        getKeyboard().bindKey(KeyEvent.VK_ENTER, this::handleInventorySelection);
    }

    private void handleInventorySelection() {
        int cmd = getScreen().getCommandNum();
        if (cmd == 0) {
            getGp().getGameStateManager().setPause();
        } else {

            List<PowerUp> ups = getGp().getPlayer().getPowerUps();
            PowerUp chosen = ups.get(cmd - 1);

            boolean used = getGp().getPlayer().usePowerUp(chosen.getType());
            getScreen().setSelectionMessage(
                    used
                            ? chosen.getType() + " activated!"
                            : "You don't have a " + chosen.getType() + "!"
            );
        }
    }
}
