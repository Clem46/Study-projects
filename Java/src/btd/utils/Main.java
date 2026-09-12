package btd.utils;

import java.util.NoSuchElementException;

import btd.boards.*;

/** Main classic mode */
public class Main {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println(
                    "Le programme nécessite 2 arguments: <x> <y>\n\t<x> Largeur du plateau\n\t<y> Hauteur du plateau");
        }else {
            int width, height;
            try {
                width = Integer.parseInt(args[0]);
                height = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                System.out.println("\nInvalid Arguments : Board's dimension must be integer !\n");
                return;
            }
            Game game = new Game(null, true);
            Board board = null;
            boolean choice = false;
            while (!choice){
                try{
                    System.out.println("Choose your Board :");
                    System.out.println("\t0 - ClassicBoard");
                    System.out.println("\t1 - LinearBoard");
                    int input = game.getInput("\t\tchoice ?\n");
                    if (input == 0) {
                        board = new ClassicBoard(width, height);
                        choice=true;
                    }else if (input == 1) {
                        int nbPaths = game.getInput("Number of path : ");
                        board = new LinearBoard(width, height, nbPaths);
                        choice = true;
                    }else{
                        throw new NoSuchFieldError("Warning: Not in the list of actions\n");
                    }
                }catch(NoSuchElementException e){
                    System.out.println(e.getMessage());
                }catch(IllegalArgumentException e){
                    System.out.println(e.getMessage());
                }catch(NoSuchFieldError e){
                    System.out.println(e.getMessage());
                }
            }
            game = new Game(board, true);
            game.play();
        }
    }
}
