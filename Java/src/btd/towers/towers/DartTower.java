package btd.towers.towers;

import btd.boards.Cell;
import btd.towers.projectiles.*;
import btd.towers.upgrades.*;

/** A dart tower */
public class DartTower extends DamageTower {

    /**
     * Creates a dart tower
     * @param pos position
     */
    public DartTower(Cell pos) {
        super(pos, 100, 1000, new LinearProjectile("Dart", 1), 200);

        Upgrade u1 = new RangeUpgrade(1.25, 100);
        Upgrade u2 = new AttackSpeedUpgrade(1.25, 150);
        Upgrade u3 = new DamageUpgrade(1, 250);
        remainingUpgrades.add(u1);
        remainingUpgrades.add(u2);
        remainingUpgrades.add(u3);
    }

    @Override
    public String getSymbol(){
        return " 🐵";
    }
}
