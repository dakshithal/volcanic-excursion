public class Main {
    public static void main(String[] args) {
        if (args.length != 4) {
            System.out.println("Please provide inputs for width, height, walls and collapsing spots");
            System.out.println("eg: java Main.java 10 20 5 20");
            return;
        }
        int width = Integer.parseInt(args[0]);
        int height = Integer.parseInt(args[1]);
        int walls = Integer.parseInt(args[2]);
        int spots = Integer.parseInt(args[3]);
        Game game = new Game(width, height);
        game.addStart();
        game.addFinish();
        for (int i = 0; i < walls; i++)
            game.addWall();
        if (game.canAddLava(spots)) {
            for (int i = 0; i < spots; i++)
                game.addLava();
            game.printGrid();
        } else {
            System.out.println("No space to add lava!");
        }
    }
}
