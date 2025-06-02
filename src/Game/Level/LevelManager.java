package Game.Level;

import Launcher.GamePanel;
import UI.GameFinishable;
import UI.PlayScreen;
import UI.Screen;
import Utils.EntityConfig;
import com.google.gson.Gson;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class LevelManager {
    private final GamePanel gp;
    private final List<Level> levels = new ArrayList<>();
    private int currentIndex;
    private final Path progressFile = Path.of("Save_progress.txt");
    Logger LOGGER = Logger.getLogger(LevelManager.class.getName());

    public LevelManager(GamePanel gp, List<String> levelFiles) {
        this.gp = gp;
        Gson gson = new Gson();
        for (String f : levelFiles) {
            try (Reader r = new InputStreamReader(
                    Objects.requireNonNull(
                            getClass().getResourceAsStream("/levels/" + f)
                    )
            )) {
                levels.add(gson.fromJson(r, Level.class));
            } catch (Exception e) {
                LOGGER.log(java.util.logging.Level.SEVERE, "Failed reading levels ", e);
            }
        }
        this.currentIndex = 0;
    }

    public void loadProgressAndBegin() {
        this.currentIndex = loadProgress();
        loadCurrentLevel();
    }

    public void startNewGame() {
        this.currentIndex = 0;
        saveProgress();
        loadCurrentLevel();
    }

    public void loadCurrentLevel() {
        Level lvl = levels.get(currentIndex);
        System.out.println("▶ Loading level index " + currentIndex + ", mapFile=" + lvl.mapFile());
        gp.getMaze().loadMaze(lvl.mapFile());
        gp.getEntitySetter().loadEntities(lvl.entities(), lvl.enemies());
    }

    public void nextLevel() {
        if (currentIndex < levels.size() - 1) {
            currentIndex++;
            saveProgress();
            loadCurrentLevel();
        } else {
            Screen screen = gp.getScreens().get("PLAY");

            if (screen instanceof GameFinishable finishable) {
                finishable.setGameFinished(true);
            }

        }
    }

    private int loadProgress() {
        try {
            if (Files.exists(progressFile)) {
                int idx = Integer.parseInt(Files.readString(progressFile).trim());
                if (idx >= 0 && idx < levels.size()) return idx;
            }
        } catch (Exception ignored) {}
        return 0;
    }

    private void saveProgress() {
        try {
            Files.writeString(progressFile, String.valueOf(currentIndex));
        } catch (Exception e) {
            LOGGER.log(java.util.logging.Level.SEVERE, "Failed saving process ", e);
        }
    }

    public int getCurrentIndex() {
        return currentIndex;
    }
    public List<EntityConfig> getEntityConfigs() {
        return levels.get(currentIndex).entities();
    }
    public List<EntityConfig> getEnemyConfigs() {
        return levels.get(currentIndex).enemies();
    }
}
