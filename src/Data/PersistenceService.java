package Data;

public interface PersistenceService {
    void saveGameData();
    void saveConfig();
    void loadConfig();
    boolean isFullScreenOn();
    void setFullScreenOn(boolean isFullscreen);
}
