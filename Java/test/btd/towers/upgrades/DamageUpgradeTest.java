package btd.towers.upgrades;

import btd.boards.Cell;
import btd.towers.towers.DartTower;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class DamageUpgradeTest {

    private DartTower tower;
    private DamageUpgrade upgrade;

    @BeforeEach
    public void before() {
        tower = new DartTower(new Cell(0, 0));
        upgrade = new DamageUpgrade(2.0, 150);
    }

    @Test
    public void applyTest() {
        double initial = tower.getFinalDamage();
        upgrade.apply(tower);
        assertEquals(initial + 2.0, tower.getFinalDamage());
    }

    @Test
    public void removeTest() {
        double initial = tower.getFinalDamage();
        upgrade.apply(tower);
        upgrade.remove(tower);
        assertEquals(initial, tower.getFinalDamage());
    }
}