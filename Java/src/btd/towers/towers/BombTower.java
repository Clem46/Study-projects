package btd.towers.towers;

import java.util.ArrayList;
import java.util.List;
import btd.boards.Cell;
import btd.towers.projectiles.*;
import btd.towers.upgrades.*;
import btd.utils.Balloon;
import btd.utils.Game;

/** A bomb tower that deals area damage */
public class BombTower extends DamageTower {

    /*This bomb tower's other projectile */
    private Projectile overrideProjectile = null;

    /**
     * Creates a bomb tower
     * @param pos position
     */
    public BombTower(Cell pos) {
        super(pos, 150, 1800, new ExplosiveProjectile("Bomb", 2, Cell.SIZE), 600);
        
        Upgrade u1 = new RangeUpgrade(1.50, 250);
        Upgrade u2 = new AttackSpeedUpgrade(1.25, 300);
        Upgrade u3 = new DamageUpgrade(1, 200);
        Upgrade u4 = new ProjectileUpgrade(new ExplosiveProjectile("Extra Bomb", 2, 2*Cell.SIZE), 400);
        remainingUpgrades.add(u1);
        remainingUpgrades.add(u2);
        remainingUpgrades.add(u3);
        remainingUpgrades.add(u4);
    }

    @Override
    public Projectile getProjectile() {
        return (overrideProjectile != null) ? overrideProjectile : projectile;
    }

    /**
     * Calculate the final explosion radius for this Tower
     * @return double the final explosion radius for this Tower
     */
    private double getFinalExplosionRadius() {
        return ((ExplosiveProjectile) this.projectile).getExplosionRadius();
    }
    
    /**
     * Add a new projectile to this tower.
     * 
     * @param p the new projectile for this tower
     */
    public void setProjectileOverride(Projectile p) {
        overrideProjectile = p;
    }

    /**
     * Remove this bomb tower's override projectile
     */
    public void removeProjectileOverride() {
        overrideProjectile = null;
    }
    
    @Override
    public List<Balloon> shoot(List<Balloon> inRange) {
        List<Balloon> touchedBalloons = new ArrayList<>();

        if (this.cooldown >= this.getFinalRate()) {
            List<Balloon> targets = super.selectBalloon(inRange);
            if (targets != null && !targets.isEmpty()) {
                Balloon mainTarget = targets.get(0);

                if (this.getProjectile() instanceof ExplosiveProjectile) {

                    double radius = this.getFinalExplosionRadius();
                    double explosionRangeSq = radius * radius;
                    displayShoot(mainTarget);
                    for (Balloon b : inRange) {
                        if (Game.distanceSquaredOf(mainTarget.getCurrentCell(),
                                b.getCurrentCell()) <= explosionRangeSq) {
                            b.decreaseHealth(this.getFinalDamage());
                            touchedBalloons.add(b);
                            b.displayTouched();
                        }
                    }

                    this.cooldown = 100;
                }
            }
        } else {
            this.cooldown += Game.TICK;
        }
        return touchedBalloons;
    }

    @Override
    public String getSymbol(){
        return " 💣";
    }
}
