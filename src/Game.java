import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

class CELL_TYPE {
    static final char EMPTY = '.';
    static final char START = '>';
    static final char FINISH = '<';
    static final char WALL = 'W';
    static final char LAVA = 'C';
}

public class Game {
    private int width, height;
    private char[][] grid;
    private final int MAX_WALL_LENGTH = 5;
    private final Set<String> occupied = new HashSet<>();

    public Game(int width, int height) {
        this.width = width;
        this.height = height;
        grid = new char[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[x][y] = CELL_TYPE.EMPTY;
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
        grid[startX][startY] = CELL_TYPE.START;
        markOccupiedCell(startX, startY);
    }

    public void addFinish() {
        int finishX = width - 1;
        int finishY = getRandomInt(0, height);
        grid[finishX][finishY] = CELL_TYPE.FINISH;
        markOccupiedCell(finishX, finishY);
    }

    public void addWall() {
        int[] wallA = getUnoccupiedCell();
        int[] wallB = getUnoccupiedCell();
        while (true) {
            if ((wallA[0] == wallB[0] && wallA[1] != wallB[1])) {
                if (Math.abs(wallA[1] - wallB[1]) < MAX_WALL_LENGTH) {
                    break;
                }
            } else if (wallA[0] != wallB[0] && wallA[1] == wallB[1]) {
                if (Math.abs(wallA[0] - wallB[0]) < MAX_WALL_LENGTH) {
                    break;
                }
            }
            wallA = getUnoccupiedCell();
            wallB = getUnoccupiedCell();
        }

        if (wallA[0] == wallB[0]) {
            int wallX = wallA[0];
            int wallStartY = Math.min(wallA[1], wallB[1]);
            int wallEndY = Math.max(wallA[1], wallB[1]);
            for (int y = wallStartY; y <= wallEndY; y++) {
                grid[wallX][y] = CELL_TYPE.WALL;
                markOccupiedCell(wallX, y);
            }
        } else {
            int wallY = wallA[1];
            int wallStartX = Math.min(wallA[0], wallB[0]);
            int wallEndX = Math.max(wallA[0], wallB[0]);
            for (int x = wallStartX; x <= wallEndX; x++) {
                grid[x][wallY] = CELL_TYPE.WALL;
                markOccupiedCell(x, wallY);
            }
        }
    }

    public void addLava() {
        int[] lava = getUnoccupiedCell();
        int x = lava[0];
        int y = lava[1];
        grid[x][y] = CELL_TYPE.LAVA;
        markOccupiedCell(x, y);

        // Update neighbouring cells
        if (x > 0) {
            // left top neighbour
            if (y > 0) {
                updateNeighbour(x - 1, y - 1);
            }

            // left middle neighbour
            updateNeighbour(x - 1, y);

            // left bottom neighbour
            if (y < height - 1) {
                updateNeighbour(x - 1, y + 1);
            }
        }

        // middle top neighbour
        if (y > 0) {
            updateNeighbour(x, y - 1);
        }

        // middle bottom neighbour
        if (y < height - 1) {
            updateNeighbour(x, y + 1);
        }

        if (x < width - 1) {
            // right top neighbour
            if (y > 0) {
                updateNeighbour(x + 1, y - 1);
            }

            // right middle neighbour
            updateNeighbour(x + 1, y);

            // right bottom neighbour
            if (y < height - 1) {
                updateNeighbour(x + 1, y + 1);
            }
        }
    }

    public boolean canAddLava(int spots) {
        return width * height - occupied.size() >= spots;
    }

    private void markOccupiedCell(int x, int y) {
        String occupiedCoordinate = x + ":" + y;
        occupied.add(occupiedCoordinate);
    }

    private boolean isCellOccupied(int x, int y) {
        String checkCoordinate = x + ":" + y;
        return occupied.contains(checkCoordinate);
    }

    private int[] getUnoccupiedCell() {
        int [] cell = new int[2];
        do {
            cell[0] = getRandomInt(0, width);
            cell[1] = getRandomInt(0, height);
        } while (isCellOccupied(cell[0], cell[1]));
        return cell;
    }

    private boolean isEditable(char value) {
        return value != CELL_TYPE.START && value != CELL_TYPE.FINISH && value != CELL_TYPE.WALL && value != CELL_TYPE.LAVA;
    }

    private void updateNeighbour(int x, int y) {
        char existingValue = grid[x][y];
        if (existingValue == CELL_TYPE.EMPTY) {
            grid[x][y] = '1';
        } else if (isEditable(existingValue)) {
            grid[x][y] = (char) (existingValue + 1);
        }
    }

    private int getRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }
}
