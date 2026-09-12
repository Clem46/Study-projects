package btd.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

import btd.exceptions.NotEnoughCreditsException;
import btd.boards.*;
import btd.towers.towers.*;
import btd.towers.upgrades.Upgrade;

/**
 * Manages the game logic including rounds, balloons and towers.
 */
public class Game {
    /** Tick rate */
    public static final int TICK = 100;
    /** This Game's Player */
    private Player player;

    /** This Game's board */
    private Board board;

    /** This Game's numbers of rounds */
    public int round;

    /** List of balloons waiting in a specific round */
    private List<Balloon> waitingBalloons;

    /** List of active balloons in a specific round */
    private List<Balloon> activeBalloons;

    /** The scanner for input */
    private final Scanner SCANNER;

    /** The bolean for knowing if the game is interactive or not */
    private final boolean interactive;
    /** F<or automatic choices */
    private final Random random = new Random();

    /**
     * Build a Game with a given board
     * @param board this game's board
     * @param interactive wheter the game is interactive or not
     */
    public Game(Board board, boolean interactive) {
        this.player = new Player();
        this.board = board;
        this.round = 1;
        this.waitingBalloons = new ArrayList<Balloon>();
        this.activeBalloons = new ArrayList<Balloon>();
        this.SCANNER = new Scanner(System.in);
        this.interactive = interactive;
    }

    /**
     * Build a Game with a given board and player
     * @param board this game's board
     * @param player this game's player
     */
    public Game(Board board, Player player) {
        this.player = player;
        this.board = board;
        this.round = 1;
        this.waitingBalloons = new ArrayList<Balloon>();
        this.activeBalloons = new ArrayList<Balloon>();
        this.SCANNER = new Scanner(System.in);
        this.interactive = true;
    }

    /**
     * Return player
     * @return Player 
     */
    public Player getPlayer(){
        return this.player;
    }

    /**
     * Displays a msg and reads an integer input from the console.
     * This method reads the entire input line. If the input cannot be 
     * parsed into a valid integer, a NoSuchElementException is thrown.
     *
     * @param msg The message to be displayed to the player.
     * @return The integer value entered by the player.
     * @throws NoSuchElementException If the input is not a valid integer.
     */
    public int getInput(String msg) throws NoSuchElementException{
        System.out.print(msg);
        String var = SCANNER.nextLine();
        try{
            return Integer.parseInt(var);
        }
        catch(NumberFormatException e) {
            throw new NoSuchElementException("ERROR : Invalid input\n");
        }
    }
     /**
     * Return the square of distance of two points
     * @param c1 The first Cell
     * @param c2 The second Cell
     * @return double
     */
    public static double distanceSquaredOf(Cell c1, Cell c2) {
        int dx = (c2.getX() - c1.getX()) * Cell.SIZE;
        int dy = (c2.getY() - c1.getY()) * Cell.SIZE;
        return dx * dx + dy * dy;
    }
    
    /**
     * Add t in board.
     * @param t Tower
     * @throws NotEnoughCreditsException 
     */
    public void buyTower(Tower t) throws NotEnoughCreditsException {
        if(!player.hasEnoughCredits(t.getTotalValue())){
            throw new NotEnoughCreditsException("Not enough credits to buy this tower !");
        }
        board.addTower(t);
        board.getCell(t.getCell().getX(), t.getCell().getY()).setState(StateCell.TOWER);
        player.changeCredits(-t.getTotalValue());
        System.out.printf("%s has been bought\n\n", t);
    }

    /**
     * Removes the Tower t from the board
     * @param t The tower to remove
     */
    public void removeTower(Tower t){
        board.removeTower(t);
        player.changeCredits(t.getTotalValue());
    }
    
    /**
     * This function updates the waiting balloons.
     * @param nbBalloons the number of balloons to update
     */
    private void actualizeBalloons(int nbBalloons) {
        this.waitingBalloons = new ArrayList<Balloon>();
        double speed = 25;
        int health = 2 + (this.round / 2);
        Random r = new Random();

        for (int i = 0; i < nbBalloons; i++) {
            int nbPath = r.nextInt(this.board.getPath().size());
            Balloon b = new Balloon(health, speed, this.board.getPath().get(nbPath), i + 1);
            this.waitingBalloons.add(b);
        }
    }

    /**
     * Make all tower shoot and display the shot.
     */
    private void towerShoot() {
        for (Tower t : board.getTowers()) {
            List<Balloon> touchedBallon = t.shoot(t.getBalloonsInRange(activeBalloons));
            for (Balloon b : touchedBallon) {
                if (b.getHealth() <= 0) {
                    activeBalloons.remove(b);
                    b.displayPopped();
                    player.changeCredits(Balloon.CREDITSPOPPED);
                }
            }
        }
    }
    
    /**
     * Manages the main interaction loop where the player chooses list before a round begins.
     * Provides options to start the round, view the board, or manage towers and upgrades.
     */
    private void playerChoice(){
        boolean roundStarted = false;
        while (!roundStarted) {
            List<Tower> allTowers = board.getTowers();
        
            List<Tower> upgradableTowers = new ArrayList<>(allTowers);
            upgradableTowers.removeIf(t -> t.getRemainingUpgrade().isEmpty());
            
            List<Tower> towersWithUpgrades = new ArrayList<>(allTowers);
            towersWithUpgrades.removeIf(t -> t.getAppliedUpgrade().isEmpty());

            boolean showSell = !allTowers.isEmpty();
            boolean showBuyUpgrade = !upgradableTowers.isEmpty();
            boolean showSellUpgrade = !towersWithUpgrades.isEmpty();

            List<String> list = new ArrayList<>();
            list.add("start round");
            list.add("view");
            list.add("buy Tower");
            if (showSell)
                list.add("sell Tower"); // a afficher seulement si au moins une tour est placé 
            if (showBuyUpgrade)
                list.add("buy upgrade Tower"); // a afficher seulement si une tour placé et a au moins une upgrade ou si tt upgrade sont pas deja mise
            if (showSellUpgrade)
                list.add("sell upgrade Tower"); // a afficher seulement si une tour placé a au moins une upgrade d'appliqué

            // Vérifie si c'est une parti interactive ou non
            int input;
            if (this.interactive) {
                displayChoice(list);
                input = getInput("\t\tchoice ?\n");
            } else {
                // L'ordinateur choisit une action au hasard
                input = this.random.nextInt(list.size());
                System.out.println("Randomly choosing : " + list.get(input));
            }

            try {
                if (input >= 0 && input < list.size()) {
                    System.out.println("Executing action : " + list.get(input));

                    switch (list.get(input)) {
                        case "start round":
                            roundStarted=!roundStarted;
                            break;
                        case "view": // view
                            player.displayStats();
                            board.displayBoard();
                            break;
                        case "buy Tower": 
                            buyTowerChoice();
                            break;
                        case "sell Tower": 
                            if (!showSell)
                                throw new NoSuchFieldError("Warning: Not in the list of actions");
                            sellTowerChoice();
                            break;
                        case "buy upgrade Tower": 
                            if (!showBuyUpgrade)
                                throw new NoSuchFieldError("Warning: Not in the list of actions");
                            buyUpgradeTowerChoice();
                            break;
                        case "sell upgrade Tower":
                            if (!showSellUpgrade)
                                throw new NoSuchFieldError("Warning: Not in the list of actions");
                            sellUpgradeTowerChoice();
                            break;
                        default:
                            throw new NoSuchFieldError("Warning: Not in the list of actions");              
                    }
                }
                else{
                    throw new NoSuchFieldError("Warning: Not in the list of actions");  
                }
            }
            catch (NoSuchElementException e) {
                System.out.println(e.getMessage());
            }
            catch (NoSuchFieldError e){
                System.out.println(e.getMessage());
            }
        }
    }
    
    /**
     * Displays the tower shop menu and handles the logic for purchasing and placing a new tower.
     * Prompts the player for the tower type and the coordinates on the board.
     */
    private void buyTowerChoice(){
        List<String> list = new ArrayList<>();
        List<Tower> placeableTowers = List.of(
            new IceTower(null), new BombTower(null), 
            new SlowTower(null), new DartTower(null),
            new SniperTower(null), new SuperTower(null),
            new TackTower(null));
            list.add("return");
        for (Tower tower : placeableTowers) {
            list.add("buy a new " + tower.getClass().getSimpleName() + " (cost: " + tower.getTotalValue() + ")");
        }
        
        boolean choice = false;
        while (!choice){
            try{
                int input;
                if (this.interactive) {
                    displayChoice(list); 
                    input = getInput("\t\tchoice ?\n");
                } else {
                    input = this.random.nextInt(list.size()); // Choisis un iput au hasard dans la liste
                }
                if (input ==0){
                    choice=true;
                }
                else if (input >= 0 && input < list.size()) {
                    List<Integer> coordinate = managePlacement();
                    if (coordinate  == null){
                        throw new NoSuchFieldError("Warning: Not in the list of actions\n");
                    }
                    Cell cell = board.getCell(coordinate.get(0), coordinate.get(1));
                    Tower addedTower = placeableTowers.get(input-1);
                    addedTower.setCell(cell);
                    buyTower(addedTower); // Exception sami stp
                    choice = true;
                }
                else{
                    throw new NoSuchFieldError("Warning: Not in the list of actions\n");
                }
                
            }catch(NoSuchElementException e){
                System.out.println(e.getMessage());
            }
            catch (NoSuchFieldError e){
                System.out.println(e.getMessage());
            }
            catch (NotEnoughCreditsException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    /**
     * This function manage the placement in board.
     * Return x,y valid
     * @return
     */
    private List<Integer> managePlacement() {

        if (!this.interactive) {
            int x, y;
            int attempts = 0;
            // On cherche une case libre au hasard au maximum 100 essais pour éviter les cas où la liste peut être vide 
            do {
                x = this.random.nextInt(board.getWidth());
                y = this.random.nextInt(board.getHeight());
                attempts++;
            } while (!board.isPlaceable(x, y) && attempts < 100);
            
            return (attempts < 100) ? List.of(x, y) : null;
        }

        int essai = 5;
        int x=-1,y=-1;
        boolean choice = false;
        board.displayBoard();
        while (!choice && 0 < essai) {
            try {
                x = getInput("x ?");
                y = getInput("y ?");
                choice = board.isPlaceable(x, y);
                if (choice){
                    return List.of(x,y);
                }
                board.displayBoard();
                System.out.printf("Warning : Wrong coordinates.%nRemaining try : %s%n",--essai);
                
            } 
            catch (NoSuchElementException e) {
                System.out.println(e.getMessage());
                board.displayBoard();
                System.out.printf("Warning : Wrong coordinates.%nRemaining try : %s%n",--essai);
            }
        }
        return null;
    }

    /**
     * Manages the process of selling an existing tower from the board.
     * Displays the list of current towers, updates the player's credits, and clears the board cell.
     */
    private void sellTowerChoice(){
        List<String> list = new ArrayList<>();
        list.add("return");
        List<Tower> sellableTowers = board.getTowers();
        for (Tower towers : sellableTowers) {
            list.add(String.format("sell %s for %d credits", towers, towers.getTotalValue()));
        }
        displayChoice(list);
        boolean choice = false;
        while (!choice){
            try{

                int input;
                if (this.interactive) {
                    displayChoice(list);
                    input = getInput("\t\tchoice ?\n");
                } else {
                    input = this.random.nextInt(list.size());
                }

                if (input ==0){
                    choice=true;
                }
                else if (input > 0 && input < list.size()) {
                    
                    Tower sellTower = sellableTowers.get(input-1);
                    
                    System.out.printf("%s has been sold (%d -> %d)%n", sellTower, player.getCredits(), player.getCredits()+sellTower.getTotalValue()); // une fonction pr l'affichage ?
                    player.changeCredits(sellTower.getTotalValue());
                    board.getTowers().remove(input-1);
                    sellTower.getCell().popState(); 
                    sellTower.getCell().switchIsOccupied(); // a changer ca parce que on ne switch pas le isOccupied si c un LinearBoard (créer un  boolean board.removeTower(Tower))
                    choice = true;
                }else{
                    throw new NoSuchFieldError("Warning: Not in the list of actions");
                }
            }catch(NoSuchElementException e){
                System.out.println(e.getMessage());
            }catch (NoSuchFieldError e){
                System.out.println(e.getMessage());
            }

        }
    }
    /**
     * Identifies towers that are eligible for upgrades and prompts the player to select one.
     * Filters the list to only show towers that still have available upgrades.
     */
    private void buyUpgradeTowerChoice() {
        List<String> list = new ArrayList<>();
        list.add("return");
        List<Tower> upgradableTower = new ArrayList<>();
        for (Tower t: board.getTowers())
            if (!t.getRemainingUpgrade().isEmpty())
                upgradableTower.add(t);
        for (int i=0;i< upgradableTower.size();i++){
            list.add(String.format("%s",upgradableTower.get(i)));
        }
        displayChoice(list);
        boolean choice = false;
        while (!choice){
            try{
                int input;
                if (this.interactive) {
                    displayChoice(list); 
                    input = getInput("\t\tchoice ?\n");
                } else {
                    input = this.random.nextInt(list.size());
                }
                if (input ==0){
                    choice=true;
                }
                else if (input > 0 && input < list.size()) {
                    whichUpgradeApplied(upgradableTower.get(input-1));
                    choice = true;
                }
                else{
                    throw new NoSuchFieldError("Warning: Not in the list of actions");
                }
            }catch(NoSuchElementException e){
                System.out.println(e.getMessage());
            }catch (NoSuchFieldError e){
                System.out.println(e.getMessage());
            }
        }
    }
    
    /**
     * Handles the selection and application of a specific upgrade for a chosen tower.
     * @param tower The tower to be upgraded.
     */
    private void whichUpgradeApplied(Tower tower) {
        List<String> list = new ArrayList<>();
        list.add("return");
        for (Upgrade u: tower.getRemainingUpgrade()){
            list.add(u.toString());
        }
        displayChoice(list);
        boolean choice = false;
        while (!choice){
            try{
                int input;
                if (this.interactive) {
                    displayChoice(list);
                    input = getInput("\t\tchoice ?\n");
                } else {
                    input = this.random.nextInt(list.size());
                }
                if (input ==0){
                    choice=true;
                }
                else if (input > 0 && input < list.size()) {
                    tower.upgrade(tower.getRemainingUpgrade().get(input-1), player);
                    choice = true;
                }
                else{
                    throw new NoSuchFieldError("Warning: Not in the list of actions");
                }
            }catch(NoSuchElementException e){
                System.out.println(e.getMessage());
            }catch (NoSuchFieldError e){
                System.out.println(e.getMessage());
            }
            catch (NotEnoughCreditsException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Identifies towers with active upgrades and prompts the player to select one for reversion.
     * Filters the list to only show towers that have at least one applied upgrade.
     */
    private void sellUpgradeTowerChoice() {
        List<String> list = new ArrayList<>();
        list.add("return");
        List<Tower> revertableTower = board.getTowers();
        revertableTower.removeIf(t -> t.getAppliedUpgrade().isEmpty() );
        for (int i=0;i< revertableTower.size();i++){
            list.add(String.format("%s",revertableTower.get(i)));
        }
        displayChoice(list);
        boolean choice = false;
        while (!choice){
            try{
                int input;
                if (this.interactive) {
                    displayChoice(list);
                    input = getInput("\t\tchoice ?\n");
                } else {
                    input = this.random.nextInt(list.size());
                }

                if (input ==0){
                    choice=true;
                }
                else if (input > 0 && input < list.size()) {
                    whichUpgradeRevert(revertableTower.get(input-1));
                    choice = true;
                }
                else{
                    throw new NoSuchFieldError("Warning: Not in the list of actions");
                }
            }catch(NoSuchElementException e){
                System.out.println(e.getMessage());
            }catch (NoSuchFieldError e){
                System.out.println(e.getMessage());
            }
        }
    }
    /**
     * Handles the selection and removal of an applied upgrade from a chosen tower.
     * @param tower The tower whose upgrade will be reverted.
     */
    private void whichUpgradeRevert(Tower tower){
        List<String> choices = new ArrayList<>();
        choices.add("return");
        for(Upgrade u : tower.getAppliedUpgrade()){
            choices.add(u.toString());
        }
        displayChoice(choices);
        boolean choice = false;
        while (!choice){
            try{
                int input;
                if (this.interactive) {
                    displayChoice(choices);
                    input = getInput("\t\tchoice ?\n");
                } else {
                    input = this.random.nextInt(choices.size());
                }
                if (input ==0){
                    choice=true;
                }
                else if (input > 0 && input < choices.size()) {
                    tower.revertUpgrade(tower.getAppliedUpgrade().get(input-1),this.player);
                    choice = true;
                }
                else{
                    throw new NoSuchFieldError("Warning: Not in the list of actions");
                }
            }catch(NoSuchElementException e){
                System.out.println(e.getMessage());
            }catch (NoSuchFieldError e){
                System.out.println(e.getMessage());
            }
        }
    }


    /**
     * Play a round
     * @param nbBalloons the number of balloons to spawn in this round
     */
    public void playRound(int nbBalloons) {
        int time = 0;
        this.actualizeBalloons(nbBalloons); // waiting balloons
        
        System.out.println("\n\n\n===== Step 2 : spawn ballons");
        int i = 0;
        while (i < this.waitingBalloons.size() || !this.activeBalloons.isEmpty()) { // Mouvement des ballons
            System.out.println("\n---- time: " + time);
            Balloon newBalloon = null;
            if (i < this.waitingBalloons.size()) {
                newBalloon = this.waitingBalloons.get(i);
                Balloon b = this.waitingBalloons.get(i);
                this.activeBalloons.add(b);
                i++;
            }
            for (int j = 0; j < this.activeBalloons.size(); j++) { // Verification si ballons ont fini parcours
                Balloon b = this.activeBalloons.get(j);
                if (b.hasFinished()) {
                    b.displayFinished();
                    this.activeBalloons.remove(j);
                    this.player.decreaseLife(b.getHealth());
                    j--;
                } else {
                    b.move();
                }
            }

            System.out.println("---- balloons positions");
            if (newBalloon != null) {
                newBalloon.displaySpawn();
            }
            // this.displayActiveBalloons();

            System.out.println("---- towers shots");
            towerShoot();

            time += Game.TICK;
            if (player.isDead()){
                System.out.println("GAME OVER !!"); // BREAK 
            }
        }
        if (!player.isDead()){
            System.out.println("\nRound cleared !");
            player.displayStats();
        }
    }

    /**
     * Play a game of Bloons Tower Defense.
     */
    public void play(){
        while (!player.isDead()){
            System.out.println("\n\n=== Starting Round " + this.round + " ===");
            board.displayBoard();
            System.out.println("\n===== Step 1 : player plays");
            playerChoice();
            playRound(15);
            this.round++;
        }
        System.out.println("\n=== Game Finished ===");
        player.displayStats();
    }
    
    /**
     * Display this game's list of active balloons.
     */
    public void displayActiveBalloons() {
        for (Balloon b : this.activeBalloons) {
            String status = "";
            if (b.getSlowValue() == 0) {
                status = " [FREEZE]";
            } else if (b.getSlowValue() < 1.0) {
                status = " [SLOW]";
            }
            System.out.println(b + " (health: " + b.getHealth() + ")" + status);
        }
    }
    
    /** Display the choice of action for the player
     * function returns the list of labels for the choice, in order to be used in the main function
     * For example, if in the list existingTowers we have a BombTower and a DartTower, the display will be :
     *      0 - start round
     *      1 - view
     *      2 - buy a new BombTower (cost: 500) 
     *      3 - buy a new DartTower (cost: 300)
     *  and function will return the list ["start round", "view", "buy a new BombTower (cost: 500)", "buy a new DartTower (cost: 300)"]
     * @param existingTowers the list of towers already existing in the game
     * @return List<String> the list of labels for the choice, in order to be used in the main function
     */
    private void displayChoice(List<String> list){
        player.displayCredits();
        System.out.println("\nWhich action ?");
        for (int i=0; i < list.size();i++){
            System.out.printf("\t%d - %s%n",i , list.get(i));
        }
    }
}
