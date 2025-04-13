package GameEntities;

public abstract class PowerUp {
    private int cost;

    public void apply(){}

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
