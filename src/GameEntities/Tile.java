package GameEntities;

public class Tile extends GameEntities{
    private boolean passable;
    private String type;

    public Tile(boolean passable, String type, int x, int y) {
        this.passable = passable;
        this.type = type;
    }

    public boolean isPassable() {
        return passable;
    }

    public void setPassable(boolean passable) {
        this.passable = passable;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
