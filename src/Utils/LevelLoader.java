package Utils;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LevelLoader {
    private final File levelsDir;

    public LevelLoader(String directoryPath) {
        this.levelsDir = new File(directoryPath);
        if (!levelsDir.isDirectory()) {
            throw new IllegalArgumentException("Not a valid directory: " + directoryPath);
        }
    }

    public List<String> loadLevelFiles() {
        File[] files = levelsDir.listFiles((dir, name) -> name.toLowerCase().endsWith(".json"));
        if (files == null) {
            throw new IllegalStateException("Unable to list files in: " + levelsDir);
        }
        return Arrays.stream(files)
                .map(File::getName)
                .sorted()
                .collect(Collectors.toList());
    }
}
