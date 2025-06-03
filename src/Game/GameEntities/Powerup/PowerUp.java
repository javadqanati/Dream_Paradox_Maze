package Game.GameEntities.Powerup;

import Game.GameEntities.Entity;
import Launcher.GamePanel;

public abstract class PowerUp extends Entity {
    private int cost;
    private String type;
    private String description;

    public PowerUp(GamePanel gp) {
        super(gp);
    }

    public void initialize(String type, int cost) {
        this.type = type;
        this.cost = cost;
    }

    public abstract PowerUp createNewInstance();
    public abstract void apply();

    public int getCost() {
        return cost;
    }
    public void setCost(int cost) {
        this.cost = cost;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
