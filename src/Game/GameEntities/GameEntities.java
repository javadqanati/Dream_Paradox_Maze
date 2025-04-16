package Game.GameEntities;

public abstract class GameEntities {
    private int x;
    private int y;

    public GameEntities(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public GameEntities() {

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
