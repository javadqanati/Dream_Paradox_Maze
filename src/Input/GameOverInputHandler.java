package Input;


import Launcher.GamePanel;
import UI.Screen;

import java.util.List;

public class GameOverInputHandler extends ScreenInputHandler{
    public GameOverInputHandler(KeyboardInputHandler keyboard, Screen screen, GamePanel gp) {
        super(keyboard, screen, gp);
        screen.setOptions(List.of(
                "Back"
        ));

        setOptionActions(List.of(
                () -> getGp().getGameStateManager().setMenu()
        ));
        getScreen().setCommandNum(0);
        bindKeys();
    }
}
