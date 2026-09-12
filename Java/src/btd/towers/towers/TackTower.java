package btd.towers.towers;

import java.util.ArrayList;
import java.util.List;
import btd.boards.Cell;
import btd.towers.projectiles.*;
import btd.towers.upgrades.*;
import btd.utils.Balloon;

/** A tack tower */
public class TackTower extends DamageTower {

    /**
     * Creates a tack tower
     * @param pos position
     */
    public TackTower(Cell pos) {
        super(pos, 100, 1200, new LinearProjectile("tack", 1), 350);

        Upgrade u1 = new RangeUpgrade(1.2, 150);
        Upgrade u2 = new AttackSpeedUpgrade(1.25, 200);
        remainingUpgrades.add(u1);
        remainingUpgrades.add(u2);
    }

    @Override
    public List<Balloon> shoot(List<Balloon> inRange) {
        List<Balloon> touchedBalloons = new ArrayList<>();
        for (Balloon b : inRange) {
            b.decreaseHealth(this.getFinalDamage());
            touchedBalloons.add(b);
            displayShoot(b);
            b.displayTouched();
        }
        return touchedBalloons;

    }

    @Override
    public String getSymbol(){
        return " ⚙️ ";
    }
}
