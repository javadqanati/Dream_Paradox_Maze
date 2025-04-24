package Game.GameEntities;

import Launcher.GamePanel;

public abstract class PowerUp extends GameEntities {
    private int cost;
    private String type;

    public PowerUp(GamePanel gp) {
        super(gp);
    }

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

    public void initialize(String type, int cost) {
        this.type = type;
        this.cost = cost;
    }
}
