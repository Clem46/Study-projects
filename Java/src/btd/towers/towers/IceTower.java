package btd.towers.towers;

import btd.boards.Cell;
import btd.utils.Balloon;
import btd.utils.Game;

/** An ice tower that freezes balloons */
public class IceTower extends SlowTower {

    /**
     * Creates an ice tower
     * @param pos position
     */
    public IceTower(Cell pos) {
        super(pos);
        this.slowPower = 1.0;
        this.countdown = 5;
    }

    @Override
    public void displayShoot(Balloon b) {
        System.out.printf("\t%s frozen for %dms %n", b, this.countdown * Game.TICK);
    }

    @Override
    public String getSymbol(){
        return " ❄️ ";
    }
}
