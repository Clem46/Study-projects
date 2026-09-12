package btd.towers.towers;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import btd.boards.Cell;
import btd.exceptions.NotEnoughCreditsException;
import btd.towers.projectiles.Projectile;
import btd.towers.upgrades.Upgrade;
import btd.utils.Balloon;
import btd.utils.Game;
import btd.utils.Player;

/** Abstract tower that deals damage */
public abstract class DamageTower extends Tower {

    /** The projectile for this Tower */
    protected Projectile projectile;    
    /*This tower's bonus damage */
    protected double bonusDamage = 0;
    /*This tower's range multiplier */
    protected double rangeMultiplier = 1.0;
    /*This tower's fire rate multiplier */
    protected double AttackSpeedMultiplier = 1.0;

    /**
     * Creates a damage tower
     * @param pos position
     * @param range range
     * @param attackSpeed attack speed
     * @param projectile projectile
     * @param basePrice base price
     */
    protected DamageTower(Cell pos, double range, int attackSpeed, Projectile projectile, int basePrice) {
        super(pos, range, attackSpeed, basePrice);
        this.projectile = projectile;
    }

    /**
     * Return the total amount of damage for this Tower
     * @return the total amount of damage for this Tower
     */
    public double getFinalDamage() {
        return getDamage() + bonusDamage;
    }

    public double getDamage(){
        return getProjectile().getDamage();
    }
    
    public double getFinalRate(){
        return attackSpeed - ((attackSpeed * AttackSpeedMultiplier) - attackSpeed);
    }

    public double getFinalRange(){
        return range * rangeMultiplier;
    }

    @Override //Changement de la range de la tower pour passer à range * rangeMultiplier
    public List<Balloon> getBalloonsInRange(List<Balloon> activeBalloons){
        List<Balloon> inRangeList = new ArrayList<>();
        if (this.range < 0) { 
            return new ArrayList<>(activeBalloons);
        }
        double newRange = range * rangeMultiplier;
        double rangeCarre = newRange * newRange;
        for (Balloon b : activeBalloons){
            double distance = Game.distanceSquaredOf(this.pos, b.getCurrentCell());
            if (distance <= rangeCarre) {
                inRangeList.add(b);
            }
        }
        return inRangeList;
    }

    /**
     * Return the projectile of this Tower
     * @return Projectile the projectile of this Tower
     */
    public Projectile getProjectile() {
        return projectile;
    }

    @Override
    public int getTotalValue(){
        int price = basePrice;
        for (Upgrade u: appliedUpgrades){
            price += u.getCost();

        }
        return price;
    }
    
    /**
     * Increase this tower's bonus damge by amount.
     * 
     * @param amount the increase amount
     */
    public void changeBonusDamage(double amount) {
        bonusDamage += amount;
    }

    /**
     * Increase this tower's range multiplier by multiplier.
     * 
     * @param multiplier the increase amount
     */
    public void multiplyBonusRange(double multiplier) {
        rangeMultiplier *= multiplier;
    }     
    
    /**
     * Increase this tower's fire rate multiplier by multiplier.
     * 
     * @param multiplier the increase amount
     */
    public void multiplyBonusAttackSpeed(double multiplier) {
        AttackSpeedMultiplier *= multiplier;
    }
    
    /**
     * Return the cell with the highest travelled distance
     * @param inRangeBalloons list of the cells in the range of the tower
     * @return the cell that would be shooted
     */
    protected List<Balloon> selectBalloon(List<Balloon> inRangeBalloons) {
        if (inRangeBalloons.isEmpty()) {
            return null;
        }
        Balloon max = inRangeBalloons.get(0);
        for (Balloon b : inRangeBalloons) { // tri + tard
            if (b.getDistanceTravelled() > max.getDistanceTravelled()) {
                max = b;
            }
        }
        return List.of(max);
    }

    @Override
    public List<Balloon> shoot(List<Balloon> inRange) {
        List<Balloon> touchedBalloons = new ArrayList<Balloon>();
        if (cooldown >= attackSpeed) {
            List<Balloon> b = selectBalloon(inRange);
            if (b != null && !b.isEmpty()) {
                Balloon cible = b.get(0);
                cible.decreaseHealth(this.getFinalDamage());
                displayShoot(cible);
                cible.displayTouched();
                touchedBalloons.add(cible);
                cooldown = 0;
            }
        } else {
            cooldown += Game.TICK;
        }
        return touchedBalloons;
    }

    @Override
    public void upgrade(Upgrade u, Player p) throws NoSuchElementException, NotEnoughCreditsException{
        if (!remainingUpgrades.contains(u))
            throw new NoSuchElementException("This upgrade doesn't exist in remaining upgrade");
        if (!p.hasEnoughCredits(u.getCost())) 
            throw new NotEnoughCreditsException("Not enough credits to buy this upgrade !");
        p.decreaseCredits(u.getCost());
        u.apply(this);
        this.remainingUpgrades.remove(u); 
        this.appliedUpgrades.add(u); 
        displayUpgrade(u,p);            
    }

    @Override
    public void revertUpgrade(Upgrade u, Player p) throws NoSuchElementException{
        if (!appliedUpgrades.contains(u)){
            throw new NoSuchElementException("This upgrade doesn't exist in applied upgrade");
        }
        p.changeCredits(u.getCost());
        u.remove(this);
        this.appliedUpgrades.remove(u);
        this.remainingUpgrades.add(u);
        displayRevert(u,p);
    }

    @Override
    public void displayShoot(Balloon b) {
        System.out.printf("%s shoots %s(%.0f) at %s%n", toString(), getProjectile().getName(), getFinalDamage(), b);
    }

    private void displayUpgrade(Upgrade u,Player p){
        System.out.printf("%s%n%s has been bought (money: %d -> %d)%n\n", u.displayAppliedUpgrade(this), displayUpgrade(u),p.getCredits()+u.getCost(),p.getCredits());
    }

    private String displayUpgrade(Upgrade u) {
        return String.format("%s[fireRate: %.0f, range: %s][upgradeLevel: %d, projectile: %s(%.0f)]", this, getFinalRate(), getFinalRange() !=-1 ? String.format("%.0f", getFinalRange()):"infinite", appliedUpgrades.size(), getProjectile().getName(), getFinalDamage());
    }

    private void displayRevert(Upgrade u, Player p) {
        System.out.printf("%s has its upgrade sold (money: %s -> %s)%n", displayUpgrade(u), p.getCredits()-u.getCost(),p.getCredits());
    }

    /**
     * Display the remaining upgrade of a tower
     */
    public void displayRemainingUpgrade(){
        System.out.printf("Remaining upgrades for %s : %n", this);
        for(Upgrade u: remainingUpgrades){
            System.out.printf("\t-%s%n",u);
        }
    }

    /**
     * Display the applied upgrade of a tower
     */
    public void diplayAppliedUpgrade(){
        System.out.printf("Applied upgrades of %s :%n", this);
        for(Upgrade u: appliedUpgrades){
            System.out.printf("\t-%s%n",u);
        }
    }
}
