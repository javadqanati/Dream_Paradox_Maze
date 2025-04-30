package Input;

import Launcher.GamePanel;
import UI.PlayScreen;
import UI.Screen;

import java.awt.event.KeyEvent;

public class PlayScreenInputHandler extends ScreenInputHandler {
    private final GamePanel gp;
    private final Screen screen;

    public PlayScreenInputHandler(KeyboardInputHandler keyboard,
                                  Screen screen,
                                  GamePanel gp) {
        super(keyboard, screen, gp);
        this.gp     = gp;
        this.screen = screen;

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
            else if (ps.isGameFinished()) {
                int cmd = screen.getCommandNum();
                if (cmd == 0) {
                    gp.onLevelComplete();
                } else {
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
