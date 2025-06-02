import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Stack;

public class MazeGenerator {
    private static final int WALL = 0;
    private static final int PATH = 1;

    private final int fullSize = 70;    // Total including frame
    private final int mazeSize = 50;    // Inner maze
    private final int frameSize = 10;    // Border
    private final int[][] maze;
    private final Random random = new Random();

    public MazeGenerator() {
        maze = new int[fullSize][fullSize];
    }

    public void generate() {
        // Fill everything with WALL
        for (int y = 0; y < fullSize; y++) {
            for (int x = 0; x < fullSize; x++) {
                maze[y][x] = WALL;
            }
        }

        // Start from inside
        Stack<int[]> stack = new Stack<>();
        int startY = frameSize + 1;
        int startX = frameSize + 1;
        maze[startY][startX] = PATH;
        maze[startY + 1][startX] = PATH; // make 2-width vertically
        maze[startY][startX + 1] = PATH; // make 2-width horizontally
        maze[startY + 1][startX + 1] = PATH;

        stack.push(new int[]{startY, startX});

        while (!stack.isEmpty()) {
            int[] current = stack.peek();
            int y = current[0];
            int x = current[1];

            int[][] directions = {
                {0, 4}, {4, 0}, {0, -4}, {-4, 0}  // bigger steps: 4 cells move
            };

            shuffleArray(directions);

            boolean moved = false;
            for (int[] dir : directions) {
                int ny = y + dir[0];
                int nx = x + dir[1];

                if (isInsideMaze(nx, ny) && maze[ny][nx] == WALL) {
                    // Remove thick passages 2x2
                    int midY = y + dir[0] / 2;
                    int midX = x + dir[1] / 2;

                    openPassage(midY, midX);
                    openPassage(midY + 1, midX);
                    openPassage(midY, midX + 1);
                    openPassage(midY + 1, midX + 1);

                    openPassage(ny, nx);
                    openPassage(ny + 1, nx);
                    openPassage(ny, nx + 1);
                    openPassage(ny + 1, nx + 1);

                    stack.push(new int[]{ny, nx});
                    moved = true;
                    break;
                }
            }

            if (!moved) {
                stack.pop();
            }
        }
    }

    private void openPassage(int y, int x) {
        if (x >= 0 && x < fullSize && y >= 0 && y < fullSize) {
            maze[y][x] = PATH;
        }
    }

    private boolean isInsideMaze(int x, int y) {
        return x > frameSize && x < fullSize - frameSize - 1 &&
               y > frameSize && y < fullSize - frameSize - 1;
    }

    private void shuffleArray(int[][] array) {
        for (int i = array.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int[] temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    public void saveToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int[] row : maze) {
                for (int cell : row) {
                    writer.write(cell + " ");
                }
                writer.newLine();
            }
            System.out.println("Maze saved to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printMaze() {
        for (int[] row : maze) {
            for (int cell : row) {
                System.out.print(cell == WALL ? "█" : " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        MazeGenerator generator = new MazeGenerator();
        generator.generate();
        generator.printMaze();
        generator.saveToFile("generated_maze.txt");
    }
}
