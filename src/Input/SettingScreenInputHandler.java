package Input;

import Launcher.GamePanel;
import UI.Screen;

import java.awt.event.KeyEvent;
import java.util.Arrays;

public class SettingScreenInputHandler extends ScreenInputHandler{

    public SettingScreenInputHandler(KeyboardInputHandler keyboard, Screen screen, GamePanel gp) {
        super(keyboard, screen, gp);
        screen.setMenuOptions(Arrays.asList(
                "Music",
                "Sound effects",
                "Back"
        ));
        bindOptionKeys();
        bindNavigationKeys();
    }

    @Override
    public void bindOptionKeys() {
        getKeyboard().bindKey(KeyEvent.VK_UP, () -> getScreen().setCommandNum(getScreen().getCommandNum() - 1));
        getKeyboard().bindKey(KeyEvent.VK_DOWN, () -> getScreen().setCommandNum(getScreen().getCommandNum() + 1));
        getKeyboard().bindKey(KeyEvent.VK_ENTER, this::handleSettingSelection);

    }

    private void handleSettingSelection() {
        switch (getScreen().getCommandNum()) {
            case 0 -> getGp().getAudioManager().toggleMusicMute();
            case 1 -> getGp().getAudioManager().toggleSfxMute();
            case 2 -> getGp().getGameStateManager().setMenu();
        }
    }
}
