package btd.towers.towers;

import btd.boards.Cell;
import btd.exceptions.NotEnoughCreditsException;
import btd.towers.upgrades.*;
import btd.utils.Balloon;
import btd.utils.Player;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;

public class DamageTowerTest {

    private DamageTower tower;
    private Player player;
    private Upgrade u1;

    @BeforeEach
    public void before() {
        player = new Player();
        tower = new DartTower(new Cell(0, 0));
        u1 = tower.getRemainingUpgrade().get(0);
    }

    @Test
    public void changeBonusDamageTest() {
        double base = tower.getFinalDamage();
        tower.changeBonusDamage(2.5);
        assertEquals(base + 2.5, tower.getFinalDamage());
    }

    @Test
    public void multiplyBonusRangeTest() {
        double baseRange = tower.getFinalRange();
        tower.multiplyBonusRange(1.5);
        assertEquals(baseRange * 1.5, tower.getFinalRange(), 0.01);
    }

    @Test
    public void multiplyBonusAttackSpeedTest() {
        double initialRate = tower.getFinalRate();
        tower.multiplyBonusAttackSpeed(1.2);
        assertTrue(tower.getFinalRate() < initialRate);
    }

    @Test
    public void selectBalloonTest() {
        List<Cell> path = List.of(new Cell(0, 0), new Cell(0, 1));
        Balloon b1 = new Balloon(10, 1.0, path, 0);
        Balloon b2 = new Balloon(10, 1.0, path, 1);
        b2.move();

        List<Balloon> targets = List.of(b1, b2);
        List<Balloon> selected = tower.selectBalloon(targets);

        assertNotNull(selected);
        assertEquals(b2, selected.get(0));
    }

    @Test
    public void shootTest() {
        List<Balloon> targets = new ArrayList<>();
        Balloon target = new Balloon(10, 1.0, List.of(new Cell(0, 0)), 0);
        targets.add(target);

        tower.shoot(targets);
        List<Balloon> touched = tower.shoot(targets);
        
        if (!touched.isEmpty()) {
            assertTrue(target.getHealth() < 10);
        }
    }

    @Test
    public void upgradeTest() throws NotEnoughCreditsException {
        int initialCredits = player.getCredits();
        tower.upgrade(u1, player);
        assertTrue(tower.getAppliedUpgrade().contains(u1));
        assertEquals(initialCredits - u1.getCost(), player.getCredits());
    }

    @Test
    public void revertUpgradeTest() throws NotEnoughCreditsException {
        int initialCredits = player.getCredits();
        tower.upgrade(u1, player);
        tower.revertUpgrade(u1, player);
        assertFalse(tower.getAppliedUpgrade().contains(u1));
        assertEquals(initialCredits, player.getCredits());
    }

    @Test
    public void getFinalDamageTest() {
        assertTrue(tower.getFinalDamage() > 0);
    }

    @Test
    public void getFinalRateTest() {
        assertTrue(tower.getFinalRate() > 0);
    }

    @Test
    public void getFinalRangeTest() {
        assertTrue(tower.getFinalRange() >= 0);
    }

    @Test
    public void upgradeNoCreditsExceptionTest() {
        player.decreaseCredits(player.getCredits());
        assertThrows(NotEnoughCreditsException.class, () -> tower.upgrade(u1, player));
    }

    @Test
    public void upgradeNotExistExceptionTest() {
        Upgrade fakeUpgrade = new DamageUpgrade(10, 0);
        assertThrows(NoSuchElementException.class, () -> tower.upgrade(fakeUpgrade, player));
    }

    @Test
    public void revertUpgradeNotExistExceptionTest() {
        assertThrows(NoSuchElementException.class, () -> tower.revertUpgrade(u1, player));
    }
}