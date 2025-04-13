package Data;

import GameEntities.PowerUp;
import java.util.List;

public class PlayerData {
    private List<PowerUp> inventory;
    private int level;
    private int memoryFragments;

    public List<PowerUp> getInventory() {
        return inventory;
    }

    public void setInventory(List<PowerUp> inventory) {
        this.inventory = inventory;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getMemoryFragments() {
        return memoryFragments;
    }

    public void setMemoryFragments(int memoryFragments) {
        this.memoryFragments = memoryFragments;
    }
}
