
package btd.utils;

import java.util.Random;

import btd.boards.*;
import btd.towers.BombTower;
import btd.towers.DartTower;
import btd.towers.IceTower;
import btd.towers.SlowTower;
import btd.towers.SniperTower;
import btd.towers.SuperTower;
import btd.towers.TackTower;
public class MainLinear {
    private static Cell findUnusedCell(Board board, int w, int h) throws RuntimeException {
        int x, y;
        Random r= new Random(); 
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
    public static void main(String[] args) {
        if (args.length != 4){
            System.out.println("Le programme nécessite 4 arguments <x> <y> <b> <n>\n\t<x> Largeur du plateau\n\t<y> Hauteur du plateau\n\t<b> Nombre de Ballons \n\t<n> Nombre de chemin");
            return ;
        }
        Board board = new LinearBoard(Integer.parseInt(args[0]),Integer.parseInt(args[1]),Integer.parseInt(args[3]));
        Game game = new Game(board);
        try {
            for (int i = 0; i < 2; i++) {
            game.addTower(new BombTower(findUnusedCell(board, Integer.parseInt(args[0]), Integer.parseInt(args[1]))));
            System.err.println("BombTower added");
            game.addTower(new DartTower(findUnusedCell(board, Integer.parseInt(args[0]), Integer.parseInt(args[1]))));
            System.out.println("DartTower added");
            game.addTower(new IceTower(findUnusedCell(board, Integer.parseInt(args[0]), Integer.parseInt(args[1]))));
            System.out.println("IceTower added");
            game.addTower(new SlowTower(findUnusedCell(board, Integer.parseInt(args[0]), Integer.parseInt(args[1]))));
            System.out.println("SlowTower added");
            game.addTower(new SniperTower(findUnusedCell(board, Integer.parseInt(args[0]), Integer.parseInt(args[1]))));
            System.out.println("SniperTower added");
            game.addTower(new SuperTower(findUnusedCell(board, Integer.parseInt(args[0]), Integer.parseInt(args[1]))));
            System.out.println("SuperTower added");
            game.addTower(new TackTower(findUnusedCell(board, Integer.parseInt(args[0]), Integer.parseInt(args[1]))));
            System.out.println("TackTower added");
        }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        board.show();
        
        game.playRound(Integer.parseInt(args[2]));
    }
}
