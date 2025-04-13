package GameEntities;

public abstract class Character extends GameEntities{
    private int health;
    private int speed;

    public void move(int dx, int dy){}

    public void takeDamage(int amount){}

    public boolean isAlive(){return true;}
}
