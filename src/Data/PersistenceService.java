package Data;

public interface PersistenceService {
    void saveGameData();
    void saveConfig();
    void loadConfig();
}
