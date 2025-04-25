package Input;

import Launcher.GamePanel;
import UI.Screen;
import java.util.List;
import java.awt.event.KeyEvent;

public abstract class ScreenInputHandler {
    private final KeyboardInputHandler keyboard;
    private final Screen screen;
    private final GamePanel gp;
    private List<Runnable> optionActions;

    public ScreenInputHandler(KeyboardInputHandler keyboard, Screen screen, GamePanel gp) {
        this.keyboard = keyboard;
        this.screen = screen;
        this.gp = gp;
    }

    public Screen getScreen() {
        return screen;
    }
    public KeyboardInputHandler getKeyboard() {
        return keyboard;
    }
    public GamePanel getGp() {
        return gp;
    }

    protected void setOptionActions(List<Runnable> actions) {
        this.optionActions = actions;
    }

    public void bindNavigationKeys() {
        final int max = getScreen().getNumScreenOptions();

        getKeyboard().bindKey(KeyEvent.VK_UP, () -> {
            int current = getScreen().getCommandNum();
            int c = current - 1;
            if (max == 0) return; // No options
            if (c < 0) {
                c = max - 1;
            } else if (c >= max) { // Handle overflow after decrement
                c = max - 1;
            }
            getScreen().setCommandNum(c);
        });

        getKeyboard().bindKey(KeyEvent.VK_DOWN, () -> {
            int current = getScreen().getCommandNum();
            int c = current + 1;
            if (max == 0) return; // No options
            if (c >= max) {
                c = 0;
            }
            getScreen().setCommandNum(c);
        });
    }

    public void bindKeys(){
        bindNavigationKeys();
        getKeyboard().bindKey(KeyEvent.VK_ENTER, this::runCurrentAction);
    }

    private void runCurrentAction() {
        int cmd = getScreen().getCommandNum();
        if (optionActions != null
                && cmd >= 0
                && cmd < optionActions.size()) {
            optionActions.get(cmd).run();
        }
    }
}
