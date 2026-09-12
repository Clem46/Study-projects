package btd.towers.upgrades;

import btd.boards.Cell;
import btd.towers.towers.DartTower;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class RangeUpgradeTest {

    private DartTower tower;
    private RangeUpgrade upgrade;

    @BeforeEach
    public void before() {
        tower = new DartTower(new Cell(0, 0));
        upgrade = new RangeUpgrade(1.5, 100);
    }

    @Test
    public void applyTest() {
        double initial = tower.getFinalRange();
        upgrade.apply(tower);
        assertEquals(initial * 1.5, tower.getFinalRange(), 0.01);
    }

    @Test
    public void removeTest() {
        double initial = tower.getFinalRange();
        upgrade.apply(tower);
        upgrade.remove(tower);
        assertEquals(initial, tower.getFinalRange(), 0.01);
    }
}