import java.util.concurrent.ThreadLocalRandom;

public class Game {
    private int width, height;
    private char[][] grid;

    public Game(int width, int height) {
        this.width = width;
        this.height = height;
        grid = new char[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[x][y] = '.';
            }
        }
    }

    public void printGrid() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                System.out.print(grid[x][y] + " ");
            }
            System.out.println();
        }
    }

    public void addStart() {
        int startX = 0;
        int startY = getRandomInt(0, height);
        grid[startX][startY] = '>';
    }

    public void addFinish() {
        int finishX = width - 1;
        int finishY = getRandomInt(0, height);
        grid[finishX][finishY] = '<';
    }

    private int getRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }
}
