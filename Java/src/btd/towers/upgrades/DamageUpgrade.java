package btd.towers.upgrades;

import btd.towers.towers.*;

public class DamageUpgrade implements Upgrade{
    
    /*This upgrade's bonus damage */
    private double damageBonus;

    /*This upgrade's cost */
    private int cost;

    /**
     * Build a Damage upgrade of a given bonus damage and cost.
     * 
     * @param damageBonus this upgrade's bonus damage
     * @param cost this upgrade's cost
     */
    public DamageUpgrade(double damageBonus, int cost) {
        this.damageBonus = damageBonus;
        this.cost = cost;
    }

    @Override
    public void apply(DamageTower tower) {
        tower.changeBonusDamage(damageBonus);
    }

    @Override
    public void remove(DamageTower tower) {
        tower.changeBonusDamage(-damageBonus);
    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public String toString(){
        return "Damage +" + damageBonus + " (cost " + cost + ")";
    }

    @Override
    public String displayAppliedUpgrade(DamageTower tower){
        return String.format("Applying upgrade Increase damage by %.0f (damage: %.0f -> %.0f)", damageBonus, tower.getDamage(), tower.getDamage()+damageBonus);
    }
}
