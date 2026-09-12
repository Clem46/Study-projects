package btd.towers.towers;

import btd.boards.Cell;
import btd.utils.Balloon;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.ArrayList;

public class TackTowerTest {

    @Test
    public void shootTest() {
        TackTower tackTower = new TackTower(new Cell(1, 1));
        List<Cell> path = List.of(new Cell(1, 1));
        
        List<Balloon> targets = new ArrayList<>();
        Balloon b1 = new Balloon(10, 1.0, path, 0);
        Balloon b2 = new Balloon(10, 1.0, path, 1);
        targets.add(b1);
        targets.add(b2);

        List<Balloon> touched = tackTower.shoot(targets);
        
        assertEquals(2, touched.size());
        assertTrue(b1.getHealth() < 10);
        assertTrue(b2.getHealth() < 10);
    }
}