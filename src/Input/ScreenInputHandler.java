package Input;

import Launcher.GamePanel;
import UI.Screen;

import java.awt.event.KeyEvent;

public abstract class ScreenInputHandler {
    private final KeyboardInputHandler keyboard;
    private final Screen screen;
    private final GamePanel gp;

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

    public void bindNavigationKeys(){
        final int max = getScreen().getNumScreenOptions();

        getKeyboard().bindKey(KeyEvent.VK_UP, () -> {
            int c = getScreen().getCommandNum() - 1;
            if (c < 0) c = max - 1;
            getScreen().setCommandNum(c);
        });

        getKeyboard().bindKey(KeyEvent.VK_DOWN, () -> {
            int c = getScreen().getCommandNum() + 1;
            if (c >= max) c = 0;
            getScreen().setCommandNum(c);
        });
    }

    public abstract void bindKeys();

    public abstract void bindOptionKeys();
}
