package btd.towers.towers;

import btd.boards.Cell;
import btd.towers.projectiles.*;
import btd.towers.upgrades.*;

/** A sniper tower with long range */
public class SniperTower extends DamageTower {

    /**
     * Creates a sniper tower
     * @param pos position
     */
    public SniperTower(Cell pos) {
        super(pos, -1, 2000, new LinearProjectile("very sharp dart", 4), 500);

        Upgrade u1 = new AttackSpeedUpgrade(1.25, 200);
        Upgrade u2 = new DamageUpgrade(2, 300);
        remainingUpgrades.add(u1);
        remainingUpgrades.add(u2);
    }

    @Override
    public String getSymbol(){
        return " 🏹";
    }
}
