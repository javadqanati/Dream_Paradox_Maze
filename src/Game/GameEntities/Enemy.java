package Game.GameEntities;

public abstract class Enemy extends Character{
    private String type;
    private int damage;
    private int detectionRange;

    public void attack(Player player){}

    public void patrol(){}

    public void chase(Player player){}

}
