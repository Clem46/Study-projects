package btd.towers.towers;

import btd.boards.Cell;
import btd.exceptions.NotEnoughCreditsException;
import btd.towers.upgrades.*;
import btd.utils.Balloon;
import btd.utils.Player;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;
import java.util.List;
import java.util.ArrayList;

public class TowerTest {

    private Tower tower;
    private Cell towerCell;
    private Player player;
    private Upgrade u1;

    @BeforeEach
    public void before() {
        towerCell = new Cell(2, 2);
        tower = new DartTower(towerCell);
        player = new Player();
        u1 = tower.getRemainingUpgrade().get(0);
    }

    @Test
    public void setCellTest() {
        Cell newCell = new Cell(5, 5);
        tower.setCell(newCell);
        assertEquals(newCell, tower.getCell());
    }

    @Test
    public void getBalloonsInRangeTest() {
        List<Cell> path = List.of(new Cell(2, 2));
        List<Balloon> activeBalloons = new ArrayList<>();
        Balloon b1 = new Balloon(10, 1.0, path, 1);
        activeBalloons.add(b1);
        
        List<Balloon> inRange = tower.getBalloonsInRange(activeBalloons);
        assertTrue(inRange.contains(b1));
    }

    @Test
    public void getBalloonsOutOfRangeTest() {
        List<Cell> path = List.of(new Cell(10, 10)); 
        List<Balloon> activeBalloons = new ArrayList<>();
        Balloon b1 = new Balloon(10, 1.0, path, 1);
        activeBalloons.add(b1);
        
        List<Balloon> inRange = tower.getBalloonsInRange(activeBalloons);
        assertFalse(inRange.contains(b1));
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