package btd.utils;
import btd.boards.ClassicBoard;

public class MainClassicRandom {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Le programme nécessite 2 arguments: <x> <y> <b>\n\t<x> Largeur du plateau\n\t<y> Hauteur du plateau");
            return;
        }

        try {
            int width = Integer.parseInt(args[0]);
            int height = Integer.parseInt(args[1]);

            ClassicBoard board = new ClassicBoard(width, height);

            // Jeu en mode aléatoire 
            Game game = new Game(board, false);
            game.play();
            
        } catch (NumberFormatException e) {
            System.out.println("La Largeur et la Hautuer doivent être des Integers");
        }
    }
}
