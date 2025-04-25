package Input;

import Launcher.GamePanel;
import UI.Screen;

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

    public abstract void bindKeys();
}
