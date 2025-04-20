package Game.GameEntities;

import Launcher.GamePanel;

public abstract class PowerUp extends GameEntities{
    private int cost;

    public PowerUp(GamePanel gp) {
        super(gp);
    }

    public void apply(){}

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
