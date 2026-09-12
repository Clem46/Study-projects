package btd.towers.upgrades;

import btd.towers.towers.*;
import btd.towers.projectiles.*;

public class ProjectileUpgrade implements Upgrade {

    /*This upgrade's projectile */
    private Projectile newProjectile;

    /*This upgrade's cost */
    private int cost;

    /**
     * Build a Projectile upgrade of a given projectile and cost.
     * 
     * @param newProjectile this upgrade's projectile
     * @param cost this upgrade's cost
     */
    public ProjectileUpgrade(Projectile newProjectile, int cost) {
        this.newProjectile = newProjectile;
        this.cost = cost;
    }

    @Override
    public void apply(DamageTower tower) {
        if (tower instanceof BombTower) {
            ((BombTower) tower).setProjectileOverride(newProjectile);
        }
    }

    @Override
    public void remove(DamageTower tower) {
        if (tower instanceof BombTower) {
            ((BombTower) tower).removeProjectileOverride();
        }
    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public String toString(){
        return "Change projectile to :" + newProjectile + " (cost " + cost + ")";
    }

    @Override
    public String displayAppliedUpgrade(DamageTower tower){
        return String.format("Applying upgrade change projectile by %s (Projectile  : %s -> %s)", newProjectile, tower.getProjectile(), newProjectile);
    }
}
