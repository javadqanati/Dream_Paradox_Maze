package Input;

import Launcher.GamePanel;
import UI.Screen;
import java.util.List;

public class MenuInputHandler extends ScreenInputHandler {

    public MenuInputHandler(KeyboardInputHandler keyboard, Screen screen, GamePanel gp) {
        super("MENU", keyboard, screen, gp);
        initializeMenuOptions();
        bindKeys();
    }

    private void initializeMenuOptions() {
        getScreen().setOptions(List.of(
                "Play",
                "Load",
                "Settings",
                "Quit"
        ));

        setOptionActions(List.of(
                this::startNewGame,
                this::loadGame,
                this::openSettings,
                this::quitGame
        ));

        getScreen().setCommandNum(0);
    }

    private void startNewGame() {
        getGp().getGameController().startNewGameFromMenu();
    }

    private void loadGame() {
        getGp().getGameStateManager().setLoad();
    }

    private void openSettings() {
        getGp().getGameStateManager().setSettings();
    }

    private void quitGame() {
        System.exit(0);
    }
}