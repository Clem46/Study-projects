/* package btd.utils;

import btd.boards.*;

/** Main classic mode 
public class MainClassic {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println(
                    "Le programme nécessite 2 arguments: <x> <y> <b>\n\t<x> Largeur du plateau\n\t<y> Hauteur du plateau");
        } else {
            System.out.println();
            Board board = new ClassicBoard(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
            Game game = new Game(board);
            game.play();
        }
    }
} */


package btd.utils;

import java.util.NoSuchElementException;
import java.util.Random;

import btd.boards.*;
import btd.exceptions.NotEnoughCreditsException;
import btd.towers.towers.*;

/** Main classic mode */
public class Main {

    private static Cell findUnusedCell(Board board, int w, int h) throws RuntimeException {
        int x, y;
        Random r = new Random();
        int a = 100;
        do {
            if (a == 0) {
                throw new RuntimeException("Board cannot accommodate more towers");
            }
            x = r.nextInt(w);
            y = r.nextInt(h);
            a--;
        } while (!board.isPlaceable(x, y));
        return board.getCell(x, y);
    }
     public static void main(String[] args) throws NoSuchElementException, NotEnoughCreditsException {
        if (args.length != 3) {
            System.out.println(
            "Le programme nécessite 3 arguments: <x> <y> <b>\n\t<x> Largeur du plateau\n\t<y> Hauteur du plateau\n\t<b> Nombre de balloons");
         } else {
            System.out.println();
            Board board = new ClassicBoard(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
            Game game = new Game(board);
            game.getPlayer().changeCredits(10000);
            game.getPlayer().decreaseLife(-10000);
            try {
                for (int i = 0; i < 2; i++) {
                    DamageTower tower= new BombTower(findUnusedCell(board, Integer.parseInt(args[0]), Integer.parseInt(args[1])));
                    game.buyTower(tower);
                    tower = new DartTower(findUnusedCell(board, Integer.parseInt(args[0]), Integer.parseInt(args[1])));
                    game.buyTower(tower);
                    tower = new SniperTower(findUnusedCell(board, Integer.parseInt(args[0]), Integer.parseInt(args[1])));
                    game.buyTower(tower);
                    tower = new SuperTower(findUnusedCell(board, Integer.parseInt(args[0]), Integer.parseInt(args[1])));
                    game.buyTower(tower);
                    tower = new TackTower(findUnusedCell(board, Integer.parseInt(args[0]), Integer.parseInt(args[1])));
                    game.buyTower(tower);
                }
                game.buyTower(new IceTower(findUnusedCell(board, Integer.parseInt(args[0]), Integer.parseInt(args[1]))));
                game.buyTower(new SlowTower(findUnusedCell(board, Integer.parseInt(args[0]), Integer.parseInt(args[1]))));
                game.buyTower(new IceTower(findUnusedCell(board, Integer.parseInt(args[0]), Integer.parseInt(args[1]))));
                game.buyTower(new SlowTower(findUnusedCell(board, Integer.parseInt(args[0]), Integer.parseInt(args[1]))));              
             } catch (RuntimeException e) {
                System.out.println(e.getMessage());
             }
             catch(NotEnoughCreditsException e){
                System.out.println(e.getMessage());
             }
            
            board.displayBoard();
            for(int i = 0; i < 5; i++){
                if(game.getPlayer().getLives() <= 0){
                    return;
                }
                board.getTowers().get(i).upgrade(board.getTowers().get(i).getRemainingUpgrade().get(0), game.getPlayer());

                System.out.printf("%n%n=== Starting Round %d", i+1);
                game.playRound(Integer.parseInt(args[2]));
            }
            for(int i = 5; i < 10; i++){
                if(game.getPlayer().getLives() <= 0){
                    return;
                }
                board.getTowers().get(i-5).revertUpgrade(board.getTowers().get(i-5).getAppliedUpgrade().get(0), game.getPlayer());
                
                System.out.printf("%n%n=== Starting Round %d", i+1);
                game.playRound(Integer.parseInt(args[2]));
            }
        }   
    }
}