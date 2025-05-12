package Input;

import UI.PauseScreen;
import Utils.GameStateManager;
import Launcher.GamePanel;
import UI.Screen;
import java.awt.event.KeyEvent;
import java.util.List;

public class PauseInputHandler extends ScreenInputHandler {

    public PauseInputHandler(KeyboardInputHandler keyboard, Screen screen, GamePanel gp) {
        super("PAUSE", keyboard, screen, gp);
        initializePauseMenu();
        bindKeys();
    }

    private void initializePauseMenu() {
        getScreen().setOptions(List.of(
                "Inventory",
                "Trade",
                "Save",
                "Resume",
                "Back to Menu"
        ));

        setOptionActions(List.of(
                this::openInventory,
                this::openMarket,
                this::saveGame,
                this::resumeGame,
                this::returnToMenu
        ));

        getScreen().setCommandNum(0);
    }

    @Override
    public void bindKeys() {
        super.bindKeys();
        getKeyboard().bindKey(KeyEvent.VK_P, this::togglePause);
    }

    private void openInventory() {
        getGp().getGameStateManager().setInventory();
    }

    private void openMarket() {
        getGp().getGameStateManager().setMarket();
    }

    private void resumeGame() {
        getGp().getGameStateManager().setPlay();
    }

    private void returnToMenu() {
        getGp().getGameStateManager().setMenu();
    }

    private void saveGame() {
        getGp().getPersistence().saveGameData();
        getScreen().showNotification("Game saved!");
    }

    private void togglePause() {
        GameStateManager gsm = getGp().getGameStateManager();
        if (gsm.isPlaying()) {
            gsm.setPause();
        } else {
            gsm.setPlay();
        }
    }
}