package btd.towers.towers;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.util.ArrayList;
import java.util.List;

import btd.boards.Cell;
import btd.utils.Balloon;

public class SlowTowerTest {

    private SlowTower slowTower;
    private List<Cell> path;
    private Balloon b;

    @BeforeEach
    public void before() {
        slowTower = new SlowTower(new Cell(0, 0));
        path = new ArrayList<>();
        path.add(new Cell(1, 1));
        b = new Balloon(5, 2, path, 1);
    }

    @Test
    public void applySlowTest() {
        List<Balloon> inRange = new ArrayList<>();
        inRange.add(b);

        slowTower.shoot(inRange);
        assertEquals(0.5, b.getSlowValue());
        assertEquals(5, b.getCountdown());
    }

    @Test
    public void cooldownTest() {
        List<Balloon> inRange = new ArrayList<>();
        inRange.add(b);

        slowTower.shoot(inRange);
        b.applyMovementEffect(0, 1); 

        List<Balloon> touched = slowTower.shoot(inRange);
        assertTrue(touched.isEmpty());
        assertNotEquals(0.5, b.getSlowValue());
    }
    @Test
    public void shootTest() {
        List<Balloon> targets = new ArrayList<>();
        Balloon target = new Balloon(10, 1.0, List.of(slowTower.getCell()), 0);
        targets.add(target);

        List<Balloon> touched = slowTower.shoot(targets);
        
        assertFalse(touched.isEmpty());
        assertTrue(target.getSlowValue() < 1.0);
        assertEquals(target, touched.get(0));
    }
}