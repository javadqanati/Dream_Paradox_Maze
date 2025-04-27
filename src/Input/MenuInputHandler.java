package Input;

import Launcher.GamePanel;
import UI.Screen;
import java.util.List;

public class MenuInputHandler extends ScreenInputHandler{

    public MenuInputHandler(KeyboardInputHandler keyboard, Screen screen, GamePanel gp) {
        super(keyboard, screen, gp);

        screen.setOptions(List.of("Play", "Load", "Save", "Settings", "Quit"));
        setOptionActions(List.of(
                () -> getGp().restartGame(),
                () -> getGp().getGameStateManager().setLoad(),
                () -> getGp().getDataSaver().saveData(),
                () -> getGp().getGameStateManager().setSettings(),
                () -> System.exit(0)
        ));
        getScreen().setCommandNum(0);

        bindKeys();
    }
}
