package btd.utils;

import btd.boards.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.util.ArrayList;
import java.util.List;

public class BalloonTest {

    private List<Cell> l;
    private Balloon b;

    @BeforeEach
    public void before() {
        l = new ArrayList<>();
        Cell c1 = new Cell(0, 0);
        Cell c2 = new Cell(0, 1);
        l.add(c1);
        l.add(c2);
        b = new Balloon(3, 1000, l, 1);

    }

    @Test
    public void decreaseHealthTest() {
        b.decreaseHealth(1);
        assertEquals(2, b.getHealth());
        b.decreaseHealth(2);
        assertEquals(0, b.getHealth());
        b.decreaseHealth(1);
        assertEquals(0, b.getHealth());
    }

    @Test
    public void changeSpeedTest() {
        double oldSpeed = b.getSpeed();
        b.changeSpeed(0.5);
        assertEquals(oldSpeed + 0.5, b.getSpeed());
        b.changeSpeed(2);
        assertEquals(oldSpeed + 0.5 + 2, b.getSpeed());
        b.changeSpeed(-3);
        assertEquals(oldSpeed + 0.5 + 2 - 3, b.getSpeed());
    }

    @Test
    public void isDeadTest() {
        assertFalse(b.isDead());
        b.decreaseHealth(3);
        assertEquals(0, b.getHealth());
        assertTrue(b.isDead());
    }

    @Test
    public void moveTest() {
        double oldDistance = b.getDistanceTravelled();
        b.move();
        assertEquals(oldDistance + b.getSpeed() * b.getSlowValue(), b.getDistanceTravelled());
        b.move();
        assertEquals(oldDistance + 2 * b.getSpeed() * b.getSlowValue(), b.getDistanceTravelled());
    }
    @Test
    public void applyMovementEffectTest() {
        b.applyMovementEffect(0.5, 1);
        assertEquals(0.5, b.getSlowValue());
        b.applyMovementEffect(1, 1);
        assertEquals(0, b.getSlowValue());

        b.applyMovementEffect(0.4, 1); // 40% de ralentissement
        b.move();
        assertEquals(0.6 * b.getSpeed(), b.getDistanceTravelled());

        b.applyMovementEffect(1, 3); // 100% de ralentissement de 3 tours
        b.move();
        b.move();
        b.move();
        assertEquals(0.6 * b.getSpeed(), b.getDistanceTravelled()); // le ballon n'a pas bougé
        b.move();
        assertNotEquals(0.6 * b.getSpeed(), b.getDistanceTravelled()); // le ballon recommence à bouger après 3 tours
    }

    @Test
    public void hasFinishedTest() {
        assertFalse(b.hasFinished());
        b.move();
        assertTrue(b.hasFinished());
    }
}
