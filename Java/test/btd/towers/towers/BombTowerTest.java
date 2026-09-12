package btd.towers.towers;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.util.ArrayList;
import java.util.List;

import btd.boards.Cell;
import btd.utils.Balloon;

public class BombTowerTest {

    private BombTower bombTower;
    private List<Cell> path;
    private Balloon target1;
    private Balloon target2;
    private Balloon b;

    @BeforeEach
    public void before() {
        Cell towerPos = new Cell(5, 5);
        bombTower = new BombTower(towerPos);

        path = new ArrayList<>();
        path.add(new Cell(5, 6));
        path.add(new Cell(5, 7));
        path.add(new Cell(10, 10));

        target1 = new Balloon(10, 2, path, 1);
        target2 = new Balloon(10, 2, path, 2);
        b = new Balloon(10, 2, path, 3);

        target2.move();

    }

    @Test
    public void aoeDamageTest() {
        List<Balloon> inRange = new ArrayList<>();
        inRange.add(target1);
        inRange.add(target2);

        List<Balloon> touched = bombTower.shoot(inRange);

        assertEquals(8, target1.getHealth());
        assertEquals(8, target2.getHealth());
        assertEquals(10, b.getHealth());

        assertTrue(touched.contains(target1));
        assertTrue(touched.contains(target2));
    }

    @Test
    public void cooldownTest() {
        List<Balloon> inRange = new ArrayList<>();
        inRange.add(target1);

        assertFalse(bombTower.shoot(inRange).isEmpty());
        assertTrue(bombTower.shoot(inRange).isEmpty()); 
        assertEquals(8, target1.getHealth());
    }
    
}