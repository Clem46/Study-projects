package btd.utils;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;


public class PlayerTest {
    private Player p;

    @BeforeEach
    public void before() {
        p = new Player(5000, 7);
    }

    @Test
    public void addCreditsTest() {
        p.changeCredits(1000);
        assertEquals(6000, p.getCredits());
    }

    @Test
    public void decreaseCreditsTest() {
        p.decreaseCredits(1000);
        assertEquals(4000, p.getCredits());
    }

    @Test
    public void decreaseLifeTest() {
        p.decreaseLife(1);
        assertEquals(6, p.getLives());
    }

    @Test
    public void isDeadTest() {
        p.decreaseLife(7);
        assertTrue(p.isDead());
    }

    @Test
    public void hasEnoughCreditsTest() {
        assertTrue(p.hasEnoughCredits(4000));
        assertFalse(p.hasEnoughCredits(6000));


    }

}
