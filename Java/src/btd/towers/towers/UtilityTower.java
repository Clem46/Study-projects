package btd.towers.towers;

import java.util.List;
import java.util.NoSuchElementException;

import btd.boards.Cell;
import btd.towers.upgrades.Upgrade;
import btd.utils.Balloon;
import btd.utils.Player;

/** Abstract utility tower */
public abstract class UtilityTower extends Tower {

    /** This Tower slow countdown */
    protected int countdown;

    /** This Tower slow power */
    protected double slowPower;

    /**
     * Creates a utility tower
     * @param pos position
     * @param range range
     * @param attackSpeed attack speed
     * @param basePrice base price
     */
    protected UtilityTower(Cell pos, double range, int attackSpeed, int basePrice) {
        super(pos, range, attackSpeed, basePrice);
    }

    @Override
    protected List<Balloon> selectBalloon(List<Balloon> inRangeBalloons) {
        return inRangeBalloons;
    }

    @Override
    public void upgrade(Upgrade u, Player p) throws NoSuchElementException{
        throw new NoSuchElementException("This tower is not upgradable.");
    }

    @Override
    public  void revertUpgrade(Upgrade u, Player p) throws NoSuchElementException{
        throw new NoSuchElementException("This tower doesn't possesse any upgrade.");
    }
}
