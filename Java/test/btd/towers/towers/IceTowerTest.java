package btd.towers.towers;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.util.ArrayList;
import java.util.List;

import btd.boards.Cell;
import btd.utils.Balloon;

public class IceTowerTest {

    private Tower iceTower;
    private List<Cell> path;
    private Balloon b;

    @BeforeEach
    public void before() {
        iceTower = new IceTower(new Cell(0, 0));
        path = new ArrayList<>();
        path.add(new Cell(1, 1));
        b = new Balloon(5, 2, path, 1);
    }

    @Test
    public void applyFreezeTest() {
        List<Balloon> inRange = new ArrayList<>();
        inRange.add(b);

        iceTower.shoot(inRange);

        assertEquals(0, b.getSlowValue());
        assertEquals(5, b.getCountdown());
    }
}