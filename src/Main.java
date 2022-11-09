public class Main {
    public static void main(String[] args) {
        int width = 20, height = 10, walls = 5, spots = 10;
        Game game = new Game(width, height);
        game.addStart();
        game.addFinish();
        for (int i = 0; i < walls; i++)
            game.addWall();
        for (int i = 0; i < spots; i++)
            game.addLava();
        game.printGrid();
    }
}
