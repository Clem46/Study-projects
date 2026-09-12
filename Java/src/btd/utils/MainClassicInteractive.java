package btd.utils;
import btd.boards.ClassicBoard;

public class MainClassicInteractive {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Le programme nécessite 2 arguments: <x> <y> <b>\n\t<x> Largeur du plateau\n\t<y> Hauteur du plateau");
            return;
        }
        int width = Integer.parseInt(args[0]);
        int height = Integer.parseInt(args[1]);
        ClassicBoard board = new ClassicBoard(width, height);
        Game game = new Game(board, true); // true = interactive
        game.play();
    }
}
