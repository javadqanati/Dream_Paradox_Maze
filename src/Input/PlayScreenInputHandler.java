package Input;

import Launcher.GamePanel;
import UI.PlayScreen;
import UI.Screen;

import java.awt.event.KeyEvent;
import java.util.List;

public class PlayScreenInputHandler extends ScreenInputHandler {
    private final GamePanel gp;
    private final Screen screen;

    public PlayScreenInputHandler(KeyboardInputHandler keyboard,
                                  Screen screen,
                                  GamePanel gp) {
        super("PLAY", keyboard, screen, gp);
        this.gp     = gp;
        this.screen = screen;

        getScreen().setOptions(List.of(
                "Next Level",
                "Back to menu"
        ));

        bindKeys();
    }

    @Override
    public void bindKeys() {
        bindNavigationKeys();

        getKeyboard().bindKey(KeyEvent.VK_ENTER, () -> {
            PlayScreen ps = (PlayScreen) screen;
            if (gp.getGameStateManager().isStory()) {
                gp.getGameStateManager().setPlay();
            }
            if (ps.isGameFinished()) {
                int cmd = screen.getCommandNum();
                if (cmd == 0) {
                    gp.getGameController().onLevelComplete();
                }
                if (cmd == 1){
                    gp.getGameStateManager().setMenu();
                }
            }
        });

        getKeyboard().bindKey(KeyEvent.VK_SPACE, () -> {
            if (gp.getGameStateManager().isStory()) {
                gp.getGameStateManager().setPlay();
            }
        });

        getKeyboard().bindKey(KeyEvent.VK_P, () -> {
            if (gp.getGameStateManager().isPlaying()) {
                gp.getGameStateManager().setPause();
            } else {
                gp.getGameStateManager().setPlay();
            }
        });
    }
}
