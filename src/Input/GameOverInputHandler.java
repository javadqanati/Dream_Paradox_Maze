package Input;

import Launcher.GamePanel;
import UI.Screen;
import java.util.List;

public class GameOverInputHandler extends ScreenInputHandler {

    public GameOverInputHandler(KeyboardInputHandler keyboard, Screen screen, GamePanel gp) {
        super("GAMEOVER", keyboard, screen, gp);
        screen.setOptions(List.of(
                "Back",
                "Restart"
        ));

        setOptionActions(List.of(
                () -> getGp().getGameStateManager().setMenu(),
                () -> getGp().getGameController().restartGame()
        ));
        getScreen().setCommandNum(0);
        bindKeys();
    }
}