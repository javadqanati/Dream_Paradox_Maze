package Input;

import Launcher.GamePanel;
import UI.Screen;

import java.awt.event.KeyEvent;

public class SettingScreenInputHandler extends ScreenInputHandler{

    public SettingScreenInputHandler(KeyboardInputHandler keyboard, Screen screen, GamePanel gp) {
        super(keyboard, screen, gp);
        bindKeys();
    }

    @Override
    public void bindKeys() {
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
