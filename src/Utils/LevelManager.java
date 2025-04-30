package Utils;

import Launcher.GamePanel;
import UI.PlayScreen;
import com.google.gson.Gson;

import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LevelManager {
    private final GamePanel gp;
    private final List<Level> levels = new ArrayList<>();
    private int currentIndex;
    private final Path progressFile = Path.of("Save_progress.txt");

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
                e.printStackTrace();
            }
        }
        this.currentIndex = 0;
    }

    /** Call when user selects 'Load Game' */
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
        System.out.println("▶ Loading level index " + currentIndex + ", mapFile=" + lvl.getMapFile());
        gp.getMaze().loadMaze(lvl.getMapFile());
        gp.getEntitySetter().loadEntities(lvl.getEntities(), lvl.getEnemies());
    }

    public void nextLevel() {
        if (currentIndex < levels.size() - 1) {
            currentIndex++;
            saveProgress();
            loadCurrentLevel();
        } else {
            ((PlayScreen)gp.getScreens().get("PLAY")).setGameFinished(true);
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
            e.printStackTrace();
        }
    }

    /** @return the zero-based index of the currently loaded level */
    public int getCurrentIndex() {
        return currentIndex;
    }

    /**
     * @return the list of EntityConfig for non-enemy entities
     *         in the current level.
     */
    public List<EntityConfig> getEntityConfigs() {
        return levels.get(currentIndex).getEntities();
    }

    /**
     * @return the list of EntityConfig for enemies
     *         in the current level.
     */
    public List<EntityConfig> getEnemyConfigs() {
        return levels.get(currentIndex).getEnemies();
    }
}
