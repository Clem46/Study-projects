package btd.towers.towers;

import btd.boards.Cell;
import btd.towers.upgrades.DamageUpgrade;
import btd.towers.upgrades.Upgrade;
import btd.utils.Balloon;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class UtilityTowerTest {

    private UtilityTower tower;
    private Cell towerCell;

    @BeforeEach
    public void before() {
        towerCell = new Cell(0, 0);
        tower = new SlowTower(towerCell);
    }

    @Test
    public void selectBalloonTest() {
        List<Balloon> balloons = new ArrayList<>();
        balloons.add(new Balloon(10, 1.0, List.of(towerCell), 0));
        balloons.add(new Balloon(10, 1.0, List.of(towerCell), 1));

        List<Balloon> selected = tower.selectBalloon(balloons);
        assertEquals(balloons, selected);
    }

    @Test
    public void upgradeExceptionTest() {
        Upgrade u = new DamageUpgrade(1, 100);
        assertThrows(NoSuchElementException.class, () -> tower.upgrade(u, null));
    }

    @Test
    public void revertUpgradeExceptionTest() {
        Upgrade u = new DamageUpgrade(1, 100);
        assertThrows(NoSuchElementException.class, () -> tower.revertUpgrade(u, null));
    }

    @Test
    public void shootTest() {
        List<Balloon> targets = new ArrayList<>();
        Balloon target = new Balloon(10, 1.0, List.of(towerCell), 0);
        targets.add(target);

        List<Balloon> touched = tower.shoot(targets);
        assertFalse(touched.isEmpty());
        assertTrue(target.getSlowValue() < 1.0);
    }
}