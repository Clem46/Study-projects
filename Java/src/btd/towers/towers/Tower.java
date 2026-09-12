package btd.towers.towers;

import btd.boards.Cell;
import btd.exceptions.NotEnoughCreditsException;
import btd.towers.upgrades.Upgrade;
import btd.utils.Balloon;
import btd.utils.Game;
import btd.utils.Player;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;

/**
 * Abstract base class for all towers in the game.
 */
public abstract class Tower{
    /** The position of this Tower */
    protected Cell pos;
    /** The range of this Tower */
    protected double range;
    /** The attack speed of this Tower */
    protected int attackSpeed;
    /** The base price of this tower */
    protected int basePrice;
    /** The cooldown timer for this tower's attacks */
    protected int cooldown;
    /** The remaining upgrades for this Tower */
    protected List<Upgrade> remainingUpgrades;
    /** The applied upgrades on this Tower */
    protected List<Upgrade> appliedUpgrades;

    /**
     * Build a new Tower
     * @param pos The position of this Tower
     * @param range The range of this Tower
     * @param attackSpeed The attack speed of this Tower
     * @param basePrice THe base price of this Tower
     */
    protected Tower(Cell pos, double range, int attackSpeed, int basePrice){
        this.pos = pos;
        this.range = range;
        this.attackSpeed = attackSpeed;
        this.cooldown = attackSpeed;
        this.basePrice = basePrice;
        this.remainingUpgrades = new ArrayList<Upgrade>();
        this.appliedUpgrades = new ArrayList<Upgrade>();
    }

    /**
     * Return the final range after the upgrades
     * @return the final range after the upgrades
     */
    public double getRange(){
        return range;
    }


    /**
     * Return the final fire rate after the upgrades
     * @return the final fire rate after the upgrades
     */
    public double getRate(){
        return attackSpeed;
    }
    
    /**
     * Return this Tower and return the money from selling it
     * @return the money from selling this Tower
     */
    public int getTotalValue(){
        return basePrice;
    }

    /**
     * Get the cell where this tower is located
     * 
     * @return the cell of this tower
     */
    public Cell getCell(){
        return this.pos;
    }

    /**
     * Returns all the baloons in range of the tower
     * @param activeBalloons all the actives ballons 
     * @return list of the ballons in the towers range
     */
    public List<Balloon> getBalloonsInRange(List<Balloon> activeBalloons){
        List<Balloon> inRangeList = new ArrayList<>();
        if (this.range < 0) { 
            return new ArrayList<>(activeBalloons);
        }
        double rangeCarre = this.range * this.range;
        for (Balloon b : activeBalloons){
            double distance = Game.distanceSquaredOf(this.pos, b.getCurrentCell());
            if (distance <= rangeCarre) {
                inRangeList.add(b);
            }
        }
        return inRangeList;
    }
    
    /**
     * Return the list of remaining upgrade of this Tower
     * 
     * @return the list of remaining upgrade of this Tower
     */
    public List<Upgrade> getRemainingUpgrade(){
        return remainingUpgrades;
    }

    /**
     * Return the list of applied upgrade of this Tower
     * 
     * @return the list of applied upgrade of this Tower
     */
    public List<Upgrade> getAppliedUpgrade(){
        return appliedUpgrades;
    }
    /**
     * Set cell of Tower
     * @param cell the cell to set in this Tower
     */
    public void setCell(Cell cell) {
            this.pos=cell;
        }
    /**
     * Shoots the balloon
     * @param balloonsInRange The balloon to shoot
     * @return The list of touched balloons
     */
    public abstract List<Balloon> shoot(List<Balloon> balloonsInRange);

    /**
     * Adds a new upgrade to appliedUpgrade
     * @param u The upgrade to apply
     * @param p The player who upgrades this Tower
     * @throws NotEnoughCreditsException 
     */
    public abstract void upgrade(Upgrade u, Player p) throws NoSuchElementException, NotEnoughCreditsException;

    /**
     * Revert the latest upgrade of this Tower
     * @param p The player to refund to gold
     */
    public abstract void revertUpgrade(Upgrade u, Player p) throws NoSuchElementException;

    /**
     * Select balloons to shoot from those in range
     * 
     * @param inRangeBalloons the balloons in range
     * @return the balloons selected as targets
     */
    protected abstract List<Balloon> selectBalloon(List<Balloon> inRangeBalloons);

    /**
     * Return a representation of Tower
     */
    public String toString(){
        return getClass().getSimpleName() + " at (" + pos.getX() + ", " + pos.getY() + ")";
    }

    /**
     * Display the Tower's shot to a Balloon
     * 
     * @param b the balloon being shooted 
     */
    public abstract void displayShoot(Balloon b);

    /**
     * Return the symbol of this Tower
     * @return a String representing this Tower
     */
    public abstract String getSymbol();

    
}