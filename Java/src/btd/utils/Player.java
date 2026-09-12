package btd.utils;

/**
 * Represents a player during the game.
 */
public class Player {
    /* Remains credits of player */
    private int credits;
    /* Remains lives of player */
    private int lives;

    /** Default constructor */
    public Player() {
        this.credits = 2000;
        this.lives = 20;
    }
    
    /**
     * Constructor of Player with 2 parameters

     * @param credits int
     * @param lives int
     */
    public Player(int credits, int lives) {
        this.credits = credits;
        this.lives = lives;
    }
    
    /**
     * Getter of credits
     * @return credits : int
     */
    public int getCredits() {
        return this.credits;
    }
    
    /**
     * Getter of lives
     * @return lives : int
     */
    public int getLives() {
        return this.lives;
    }
    
    /**
     * Add amount of credits and return this value
     * @param amount : int
     */
    public void changeCredits(int amount) {
        this.credits += amount;
    }

    /**
     * Remove amount of credits
     * if the player has enough credit, returns true, false otherwise
     * @param amount : int
     */
    public void decreaseCredits(int amount) {
        this.credits -= amount;
    }
    
    /**
     * Remove amount of lives.
     * if the player has enough lives, returns true, false otherwise
     * @param amount : int
     */
    public void decreaseLife(int amount) {
        this.lives -= amount;
    }

    /**
     * Return true if this player's dead (lives below or equals 0).
     * 
     * @return true if this player's dead
     */
    public boolean isDead(){
        return lives <= 0;
    }

    /**
     * Return true if price is equals or lower than this player's credits.
     * 
     * @param price the amout of credits we are checking.
     * @return true if price is equals or lower than this player's credits.
     */
    public boolean hasEnoughCredits(int price){
        return price <= credits;
    }

    /**
     * Display this player's Lives
     */
    public void displayLives(){
        System.out.printf("Player Lives: %d%n", lives);
    }

    /**
     * Display this player's Credits
     */
    public void displayCredits(){
        System.out.printf("Player Credits: %d%n", credits);
    }

    /**
     * Display this player's stats
     */
    public void displayStats(){
        displayLives();
        displayCredits();
    }
}
