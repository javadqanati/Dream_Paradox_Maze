package GameEntities;

class Maze extends GameEntities{
    private int width;
    private int height;
    private Tile[][] grid;

    public Maze(int width, Tile[][] grid, int height) {
        this.width = width;
        this.grid = grid;
        this.height = height;
    }

    public void generateMaze(){

    }

    public Tile[][] getGrid() {
        return grid;
    }

    public void setGrid(Tile[][] grid) {
        this.grid = grid;
    }
}
