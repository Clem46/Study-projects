package btd.boards;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import btd.towers.towers.IceTower;

public class ClassicBoardTest {

    private Board board;

    @BeforeEach
    public void before() {
        board = new ClassicBoard(8, 8);
    }

     @Test
    public void testAddTower(){
        assertTrue(board.getTowers().isEmpty());
        IceTower t = new IceTower(board.getCell(0, 0));
        board.addTower(t);
        assertEquals(board.getTowers().size(), 1);
    }

    @Test
    public void testRemoveTower(){
        IceTower t = new IceTower(board.getCell(0, 0));
        board.addTower(t);
        board.getCell(0, 0).setState(StateCell.TOWER);
        assertEquals(board.getTowers().size(), 1);
        board.removeTower(t);
        assertTrue(board.getTowers().isEmpty());
    }

    @Test
    public void isPlaceableTest(){
        for (int i =0; i<8; i++)
            for (int j=0; j<8; j++){
                if (board.getCell(i, j).isOccupied())
                    assertFalse(board.isPlaceable(i, j));
                else
                    assertTrue(board.isPlaceable(i, j));
            }
    }
}