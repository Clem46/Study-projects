package btd.utils;

import btd.boards.Cell;
import java.util.List;
import java.util.Random;

/**
 * Represents a balloon that travels along a path on the game board.
 */
public class Balloon {

    // The index of the balloon used in specifical round
    private int index;
    // The health of the balloon
    private int health;
    // The speed of the balloon
    private double speed;
    // Distance travelled by the balloon
    private double distanceTravelled;
    // the path of the balloon
    private List<Cell> path;
    // the current cell of the balloon
    private Cell currentCell;
    // the current slow value of the balloon
    private double slowValue;
    // the countdown of the balloon when it's slowed or frozen
    private int countdown;
    // the amount of credits gained by popping a balloon
    public static final int CREDITSPOPPED = 40;

    /**
     * Build a balloon of a given health, speed, path and index
     * @param health this balloon's health
     * @param speed  this balloon's speed
     * @param path   this balloon's path
     * @param index  this balloon's index
     */
    public Balloon(int health, double speed, List<Cell> path, int index) {
        Random r = new Random();
        this.health = health;
        this.speed = speed * (1 + r.nextInt(3));
        this.path = path;
        this.distanceTravelled = 0;
        this.currentCell = path.get(0);
        this.index = index;
        this.countdown = 0;
        this.slowValue = 1;
    }

    /**
     * Return the health of the balloon
     * 
     * @return int
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * Return the speed of the balloon
     * 
     * @return float
     */
    public double getSpeed() {
        return this.speed;
    }

    /**
     * Return the index of the balloon
     * 
     * @return int
     */
    public int getIndex() {
        return this.index;
    }

    /**
     * Return the distance travelled of the balloon
     * @return int
     */
    public double getDistanceTravelled() {
        return this.distanceTravelled;
    }

    /**
     * Return the path of this Balloon
     * @return the path of this Balloon
     */
    public List<Cell> getPath() {
        return this.path;
    }

    /**
     * Return the current cell of this Balloon
     * @return the current cell of this Balloon
     */
    public Cell getCurrentCell() {
        return this.currentCell;
    }

    /**
     * Return the current countdown of the Balloon
     * @return the current countdown of the Balloon
     */
    public int getCountdown() {
        return this.countdown;
    }

    /**
     * Return the current slow value of the balloon
     * @return double the current slow value of the balloon
     */
    public double getSlowValue() {
        return this.slowValue;
    }

    /**
     * Returns the index of this Balloon in the path
     * @return the index of this Balloon in the path
     */
    public int getIndexPosition() {
        return (int) (this.distanceTravelled / Cell.SIZE);
    }

    /**
     * Soustract the amount of damages of the balloon
     * @param damage the amount of damage to subtract
     */
    public void decreaseHealth(double damage) {
        this.health -= damage;
        if (this.health < 0) {
            this.health = 0;
        }
    }

    /**
     * Soustract a amount of speed to the balloon
     * @param changespeed the amount of speed decreased
     * 
     */
    public void changeSpeed(double changespeed) {
        this.speed += changespeed;
    }

    /**
     * Apply a movement effect to the balloon
     * @param speedDown the speed down applied {@code (0 < speedDown <= 1)}
     * @param countdown countdown of the effect in number of moves {@code (0 < countdown)}
     * @throws IllegalArgumentException if speedDown is not between 0 and 1 or if countdown is not greater than 0
     */
    public void applyMovementEffect(double speedDown, int countdown) throws IllegalArgumentException {
        if (speedDown < 0 || speedDown > 1) {
            throw new IllegalArgumentException("speedDown must be between 0 and 1");
        }
        if (countdown <= 0) {
            throw new IllegalArgumentException("countdown must be greater than 0");
        }
        this.slowValue = 1 - speedDown;
        this.countdown = countdown;
    }

    /**
     * Verify if the health of the balloon is lower than zero 
     * @return a boolean
     */
    public boolean isDead() {
        return this.health <= 0;
    }

    /**
     * Returns true if the balloon has finished the path 
     * @return boolean
     */
    public boolean hasFinished() {
        return this.distanceTravelled >= (this.path.size() * Cell.SIZE);
    }

    /**
     * Move the balloon
     */
    public void move() {
        this.distanceTravelled += (this.speed * this.slowValue);

        if (this.countdown > 0) {
            this.countdown--;
            if (this.countdown <= 0) {
                this.slowValue = 1.0;
            }
        }
        if (this.getIndexPosition() < this.path.size()) {
            this.currentCell = this.path.get(this.getIndexPosition());
        } else {
            this.currentCell = this.path.get(this.path.size() - 1); // Si on est sur l'avant derniere case et que l'on avance de plus d'une case
        }
    }

    /**
     * Return a String representation of this Balloon with numbering
     */
    public String toString() {
        return "Balloon " + this.index + " at " + this.currentCell;
    }

    /**
     * Display the Balloon at spawn
     */
    public void displaySpawn() {
        System.out.printf("Balloon Incoming !!! %s speed: %.1f%n", this, getSpeed());
    }

    /**
     * Display the Balloon when touched
     */
    public void displayTouched() {
        System.out.printf("%s touched (health: %d)%n", this, getHealth());
    }

    /**
     * Display the Balloon when popped
     */
    public void displayPopped() {
        System.out.printf("%s popped (+%d Credits)%n", this, Balloon.CREDITSPOPPED);
    }

    /**
     * Display the Balloon when the balloon finish his path.
     */
    public void displayFinished() {
        System.out.printf("%s passed !! -%d Lives\n", this, getHealth());
    }
}
