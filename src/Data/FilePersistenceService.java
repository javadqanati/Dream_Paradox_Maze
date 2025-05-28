package Data;

import Launcher.GamePanel;
import Audio.AudioManager;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FilePersistenceService implements PersistenceService {
    private final GamePanel gp;
    private final File configFile = new File("config.txt");
    private static boolean fullScreenOn = false;
    private static final Logger LOGGER = Logger.getLogger(FilePersistenceService.class.getName());

    public FilePersistenceService(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void saveGameData() {
        gp.getPlayerManager().savePlayer();
        saveConfig();
    }

    @Override
    public void saveConfig() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(configFile))) {
            bw.write(fullScreenOn ? "Full Screen On" : "Full Screen Off");
            bw.newLine();
            bw.write(Boolean.toString(AudioManager.isMusicMuted()));
            bw.newLine();
            bw.write(Boolean.toString(AudioManager.isSfxMuted()));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed saving the setting configs ", e);
        }
    }

    @Override
    public void loadConfig() {
        if (!configFile.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(configFile))) {
            String line = br.readLine();
            if ("Full Screen On".equals(line)) {
                fullScreenOn = true;
            }
            line = br.readLine();
            if (line != null) AudioManager.setMusicMuted(Boolean.parseBoolean(line));
            line = br.readLine();
            if (line != null) AudioManager.setSfxMuted(Boolean.parseBoolean(line));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed loading the setting configs ", e);
        }
    }

    public void applyDisplaySettings() {
        if (fullScreenOn && GamePanel.getWindowManager() != null) {
            GamePanel.getWindowManager().toggleFullscreen();
        }
    }

    @Override
    public boolean isFullScreenOn() {
        return fullScreenOn;
    }

    @Override
    public void setFullScreenOn(boolean fullScreenOn) {
        FilePersistenceService.fullScreenOn = fullScreenOn;
    }
}
