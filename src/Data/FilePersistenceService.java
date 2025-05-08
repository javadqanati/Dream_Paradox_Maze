package Data;

import Launcher.GamePanel;
import Audio.AudioManager;
import java.io.*;

public class FilePersistenceService implements PersistenceService {
    private final GamePanel gp;
    private final File configFile = new File("config.txt");

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
            bw.write(gp.fullScreenOn() ? "Full Screen On" : "Full Screen Off");
            bw.newLine();
            bw.write(Boolean.toString(AudioManager.isMusicMuted()));
            bw.newLine();
            bw.write(Boolean.toString(AudioManager.isSfxMuted()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadConfig() {
        if (!configFile.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(configFile))) {
            String line = br.readLine();
            if ("Full Screen On".equals(line)) {
                gp.setFullScreenOn(true);
                gp.toggleFullScreen();
            }
            line = br.readLine();
            if (line != null) AudioManager.setMusicMuted(Boolean.parseBoolean(line));
            line = br.readLine();
            if (line != null) AudioManager.setSfxMuted(Boolean.parseBoolean(line));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
