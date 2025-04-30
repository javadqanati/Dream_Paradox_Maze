package Utils;

import java.util.List;

public class Level {
    private String mapFile;
    private List<EntityConfig> entities;
    private List<EntityConfig> enemies;


    public String getMapFile()                { return mapFile;    }
    public List<EntityConfig> getEntities()   { return entities;  }
    public List<EntityConfig> getEnemies()    { return enemies;   }
}