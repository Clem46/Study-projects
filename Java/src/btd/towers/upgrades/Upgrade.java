package btd.towers.upgrades;

import btd.towers.towers.*;

/** Interface for upgrades */
public interface Upgrade {

    /**
     * apply this upgrade to tower 
     * 
     * @param tower this upgrade's target
    */
    void apply(DamageTower tower);

    /**
     * Remove this upgrade from tower.
     * 
     * @param tower the tower we wants to remove this upgrade.
     */
    void remove(DamageTower tower);

    /**
     * return this upgrade's cost.
     * 
     * @return this upgrade's cost.
     */
    int getCost();

    /**
     * Return a string representation of this upgrade
     * 
     * @return a string representation of this upgrade
     */
    String toString();

    /**
     * Display the upgrade applied
      */
    String displayAppliedUpgrade(DamageTower tower);
}
