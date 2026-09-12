package btd.boards;

import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.*;

public class CellTest {
    public Cell c1;
    public Cell c2;

    @BeforeEach
    public void before(){
        this.c1 = new Cell(1, 0);
        this.c2 = new Cell(1, 0);
    }
    @Test
    public void testIfTwoCellsAreEquals(){
        assertEquals(c1, c2);
    }

    @Test
    public void testIfOccupiedSwitched(){
        c1.switchIsOccupied();
        assertTrue(c1.isOccupied());
    }
    @Test
    public void testAddInStackState(){
        assertEquals(StateCell.EMPTY,c1.getState());
        c1.setState(StateCell.PATH);
        assertEquals(StateCell.PATH, c1.getState());
        c1.popState();
        assertEquals(StateCell.EMPTY,c1.getState());
        c1.setState(StateCell.TOWER);
        c1.setState(StateCell.TOWER);
        assertEquals(StateCell.TOWER, c1.getState());

    }
}
