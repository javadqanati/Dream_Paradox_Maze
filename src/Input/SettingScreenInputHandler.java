package Input;

import Launcher.GamePanel;
import UI.Screen;

import java.util.List;

public class SettingScreenInputHandler extends ScreenInputHandler {

    public SettingScreenInputHandler(KeyboardInputHandler keyboard, Screen screen, GamePanel gp) {
        super("SETTINGS", keyboard, screen, gp);

        screen.setOptions(List.of(
                "Full Screen",
                "Music",
                "Sound Effects",
                "Back"
        ));


        setOptionActions(List.of(
                () -> GamePanel.getWindowManager().toggleFullscreen(),
                () -> getGp().getAudioManager().toggleMusicMute(),
                () -> getGp().getAudioManager().toggleSfxMute(),
                () -> getGp().getGameStateManager().setMenu()
        ));
        getScreen().setCommandNum(0);
        bindKeys();
    }
}