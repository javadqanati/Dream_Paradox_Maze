package Input;

import Launcher.GamePanel;
import UI.Screen;

import java.util.List;

public class SettingScreenInputHandler extends ScreenInputHandler {

    public SettingScreenInputHandler(KeyboardInputHandler keyboard, Screen screen, GamePanel gp) {
        super(keyboard, screen, gp);

        screen.setOptions(List.of(
                "Music",
                "Sound Effects",
                "Back"
        ));

        setOptionActions(List.of(
                () -> {
                    getGp().getAudioManager().toggleMusicMute(); System.out.println("▶ toggling music mute");
                },
                () -> getGp().getAudioManager().toggleSfxMute(),
                () -> getGp().getGameStateManager().setMenu()
        ));
        getScreen().setCommandNum(0);
        bindKeys();
    }
}
