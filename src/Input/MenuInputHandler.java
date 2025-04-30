package Input;

import Launcher.GamePanel;
import UI.MainScreen;
import UI.Screen;
import java.util.List;

public class MenuInputHandler extends ScreenInputHandler {
    public MenuInputHandler(KeyboardInputHandler keyboard, Screen screen, GamePanel gp) {
        super(keyboard, screen, gp);

        screen.setOptions(List.of("Play", "Load", "Save", "Settings", "Quit"));
        setOptionActions(List.of(
                () -> getGp().startNewGameFromMenu(),
                () -> getGp().getGameStateManager().setLoad(),
                () -> {
                    getGp().getPersistence().saveGameData();
                    MainScreen ms = (MainScreen) screen;
                    ms.showNotification("Game saved!");
                },
                () -> getGp().getGameStateManager().setSettings(),
                () -> System.exit(0)
        ));
        getScreen().setCommandNum(0);
        bindKeys();
    }
}
