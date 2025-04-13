package Launcher;

public class GamePanelConfig {
    private int originalTileSize = 16;
    private int scale = 3;
    private int maxScreenCol = 16;
    private int maxScreenRow = 12;

    public GamePanelConfig(int originalTileSize, int scale, int maxScreenCol, int maxScreenRow) {
        this.originalTileSize = originalTileSize;
        this.scale = scale;
        this.maxScreenCol = maxScreenCol;
        this.maxScreenRow = maxScreenRow;
    }

    public GamePanelConfig() {
    }

    public GamePanelConfig setOriginalTileSize(int originalTileSize) {
        this.originalTileSize = originalTileSize;
        return this;
    }

    public GamePanelConfig setScale(int scale) {
        this.scale = scale;
        return this;
    }

    public GamePanelConfig setMaxScreenCol(int maxScreenCol) {
        this.maxScreenCol = maxScreenCol;
        return this;
    }

    public GamePanelConfig setMaxScreenRow(int maxScreenRow) {
        this.maxScreenRow = maxScreenRow;
        return this;
    }

    public int getOriginalTileSize() {
        return originalTileSize;
    }

    public int getScale() {
        return scale;
    }

    public int getMaxScreenCol() {
        return maxScreenCol;
    }

    public int getMaxScreenRow() {
        return maxScreenRow;
    }
}
