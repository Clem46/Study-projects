package btd.towers.towers;

import btd.boards.Cell;
import btd.towers.projectiles.*;
import btd.towers.upgrades.*;

/** A super tower */
public class SuperTower extends DamageTower {

    /**
     * Creates a super tower
     * @param pos position
     */
    public SuperTower(Cell pos) {
        super(pos, 200, 300, new LinearProjectile("sharp dart", 2), 1200);
        
        Upgrade u1 = new RangeUpgrade(1.50, 400);
        Upgrade u2 = new AttackSpeedUpgrade(3, 1000);
        Upgrade u3 = new DamageUpgrade(2, 600);
        remainingUpgrades.add(u1);
        remainingUpgrades.add(u2);
        remainingUpgrades.add(u3);
    }

    @Override
    public String getSymbol(){
        return " 🦸";
    }
}
