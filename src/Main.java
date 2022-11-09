public class Main {
    public static void main(String[] args) {
        int width = 20, height = 10, walls = 5;
        Game game = new Game(width, height);
        game.addStart();
        game.addFinish();
        for (int i = 0; i < walls; i++)
            game.addWall();
        game.printGrid();
    }
}
