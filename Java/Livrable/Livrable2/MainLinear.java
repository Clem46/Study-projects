/* 
package btd.utils;

import btd.boards.*;
public class MainLinear {

    public static void main(String[] args) {
        if (args.length != 4){
            System.out.println("Le programme nécessite 4 arguments <x> <y> <b> <n>\n\t<x> Largeur du plateau\n\t<y> Hauteur du plateau\n\t<b> Nombre de Ballons \n\t<n> Nombre de chemin");
            return ;
        }
        Board board2 = new LinearBoard(Integer.parseInt(args[0]),Integer.parseInt(args[1]),Integer.parseInt(args[3]));
        board2.show();
        Game game = new Game(board2);
        game.playRound(Integer.parseInt(args[2]));
    }
}
 */