
// package btd.utils;

// import java.util.Random;

// import btd.boards.*;
// import btd.towers.towers.*;

// public class MainLinear {
//     private static Cell findUnusedCell(Board board, int w, int h) throws RuntimeException {
//         int x, y;
//         Random r = new Random();
//         int a = 100;
//         do {
//             if (a == 0) {
//                 throw new RuntimeException("Board cannot accommodate more towers");
//             }
//             x = r.nextInt(w);
//             y = r.nextInt(h);
//             a--;
//         } while (!board.isPlaceable(x, y));
//         return board.getCell(x, y);
//     }
//     public static void main(String[] args) {
//         if (args.length != 4) {
//             System.out.println(
//                     "Le programme nécessite 4 arguments <x> <y> <b> <n>\n\t<x> Largeur du plateau\n\t<y> Hauteur du plateau\n\t<b> Nombre de Ballons \n\t<n> Nombre de chemin");
//             return;
//         }
//         Board board = new LinearBoard(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[3]));
//         Game game = new Game(board);
//         game.getPlayer().addCredits(10000);
//         game.getPlayer().decreaseLife(-10000);
//         try {
//             for (int i = 0; i < 2; i++) {
//                 DamageTower tower= new BombTower(findUnusedCell(board, Integer.parseInt(args[0]), Integer.parseInt(args[1])));
//                 game.buyTower(tower);
//                 System.out.println("\nBombTower added");
//                 tower.upgrade(tower.getRemainingUpgrade().get(0), game.getPlayer());
//                 tower = new DartTower(findUnusedCell(board, Integer.parseInt(args[0]), Integer.parseInt(args[1])));
//                 game.buyTower(tower);
//                 System.out.println("\nDartTower added");
//                 tower.upgrade(tower.getRemainingUpgrade().get(0), game.getPlayer());                    
//                 game.buyTower(new IceTower(findUnusedCell(board, Integer.parseInt(args[0]), Integer.parseInt(args[1]))));
//                 System.out.println("\nIceTower added");
//                 tower.upgrade(tower.getRemainingUpgrade().get(0), game.getPlayer());     
//                 game.buyTower(new SlowTower(findUnusedCell(board, Integer.parseInt(args[0]), Integer.parseInt(args[1]))));
//                 tower.upgrade(tower.getRemainingUpgrade().get(0), game.getPlayer());
//                 System.out.println("\nSlowTower added");
//                 tower = new SniperTower(findUnusedCell(board, Integer.parseInt(args[0]), Integer.parseInt(args[1])));
//                 game.buyTower(tower);
//                 System.out.println("\nSniperTower added");
//                 tower.upgrade(tower.getRemainingUpgrade().get(0), game.getPlayer());                   
//                 tower = new SuperTower(findUnusedCell(board, Integer.parseInt(args[0]), Integer.parseInt(args[1])));
//                 game.buyTower(tower);
//                 System.out.println("\nSuperTower added");
//                 tower.upgrade(tower.getRemainingUpgrade().get(0), game.getPlayer());                    
//                 tower = new TackTower(findUnusedCell(board, Integer.parseInt(args[0]), Integer.parseInt(args[1])));
//                 game.buyTower(tower);
//                 System.out.println("\nTackTower added");
//                 tower.upgrade(tower.getRemainingUpgrade().get(0), game.getPlayer());  
//             }
//         } catch (RuntimeException e) {
//             System.out.println(e.getMessage());
//         }
//         board.displayBoard();
        
//         for(int i = 1; i < 6; i++){
//             if(game.getPlayer().getLives() <= 0){
//                 return;
//             }
//             System.out.printf("%n%n=== Starting Round %d", i);
//             game.playRound(Integer.parseInt(args[2]) * i);
//         }
//         for (int i = 0; i < board.getTowers().size(); i++) {
//             if(game.getPlayer().getLives() <= 0){
//                 return;
//             }
//             // Copie de la référence de liste de Tower de board
//             Tower currentTower = board.getTowers().get(i);
                
//             if (currentTower instanceof DamageTower) {
//                 DamageTower dmgTower = (DamageTower) currentTower;
//                 try{
//                     System.out.println("\nThe upgrade " + dmgTower.getAppliedUpgrade().get(0) + " has been reverted from the " + dmgTower.toString());
//                     dmgTower.revertUpgrade(dmgTower.getAppliedUpgrade().get(0), game.getPlayer());
//                 }
//                 catch(IndexOutOfBoundsException e){
//                     System.out.println("The next upgrade can't be reverted");
//                 }
//                 System.out.println("\n");
//             }
//         }
//         for(int i = 6; i < 11; i++){
//             if(game.getPlayer().getLives() <= 0){
//                 return;
//             }
//             System.out.printf("%n%n=== Starting Round %d", i);
//             game.playRound(Integer.parseInt(args[2]) * i);
//         }
//     }
// }
