package btd.utils;
import btd.boards.LinearBoard;
public class MainLinearRandom {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Le programme nécessite 3 arguments: <x> <y> <z> <b>\n\t<x> Largeur du plateau\n\t<y> Hauteur du plateau\n\t<z> Nombre de chemins");
            return;
        }
        int width = Integer.parseInt(args[0]);
        int height = Integer.parseInt(args[1]);
        int nbPaths = Integer.parseInt(args[2]);
        LinearBoard board = new LinearBoard(width, height, nbPaths);
        Game game = new Game(board, false);
        game.play();
    }
}
