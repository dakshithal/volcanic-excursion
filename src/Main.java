public class Main {
    public static void main(String[] args) {
        int width = 20, height = 10;
        Game game = new Game(width, height);
        game.addStart();
        game.addFinish();
        game.printGrid();
    }
}
