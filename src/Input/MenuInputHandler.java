package Input;

import Launcher.GamePanel;
import UI.Screen;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.List;

public class MenuInputHandler extends ScreenInputHandler{

    public MenuInputHandler(KeyboardInputHandler keyboard, Screen screen, GamePanel gp) {
        super(keyboard, screen, gp);

        screen.setOptions(List.of("Play", "Load", "Settings", "Quit"));
        setOptionActions(List.of(
                () -> getGp().restartGame(),
                () -> getGp().getGameStateManager().setLoad(),
                () -> getGp().getGameStateManager().setSettings(),
                () -> System.exit(0)
        ));
        getScreen().setCommandNum(0);

        bindKeys();
    }
}
