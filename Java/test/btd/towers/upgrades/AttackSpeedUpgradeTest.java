package btd.towers.upgrades;

import btd.boards.Cell;
import btd.towers.towers.DartTower;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class AttackSpeedUpgradeTest {

    private DartTower tower;
    private AttackSpeedUpgrade upgrade;

    @BeforeEach
    public void before() {
        tower = new DartTower(new Cell(0, 0));
        upgrade = new AttackSpeedUpgrade(1.25, 200);
    }

    @Test
    public void applyTest() {
        double initial = tower.getFinalRate();
        upgrade.apply(tower);
        assertTrue(tower.getFinalRate() < initial);
    }

    @Test
    public void removeTest() {
        double initial = tower.getFinalRate();
        upgrade.apply(tower);
        upgrade.remove(tower);
        assertEquals(initial, tower.getFinalRate(), 0.01);
    }
}