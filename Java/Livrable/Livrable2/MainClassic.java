/* 
package btd.utils;

import btd.boards.*;
import btd.towers.towers.*;
public class MainClassic {

    public static void main(String[] args) {
        if (args.length != 3){
            System.out.println("Le programme nécessite 3 arguments: <x> <y> <b>\n\t<x> Largeur du plateau\n\t<y> Hauteur du plateau\n\t<b> Nombre de balloons");
        }else{
            System.out.println();
            Board board2 = new ClassicBoard(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
            board2.show();
            Game game = new Game(board2);
            game.playRound(Integer.parseInt(args[2]));
        }
    }
}
 */