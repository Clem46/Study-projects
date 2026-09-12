package btd.boards;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import btd.towers.towers.IceTower;

import java.util.ArrayList;
import java.util.List;

public class LinearBoardTest {

    private LinearBoard board;

    @BeforeEach
    public void before() {
        board = new LinearBoard(5, 5,2);
    }

    @Test
    public void createPathTest() {
        List<List<Cell>> allPaths = board.getPath();
        
        assertFalse(allPaths.isEmpty());
        
        List<Cell> mainPath = allPaths.get(0);
        assertFalse(mainPath.isEmpty());
    }

    @Test
    public void randomCellTest() {
        List<Cell> usedCells = new ArrayList<>();
        usedCells.add(board.getCell(0, 0));
        
        Cell[] result = board.randomCell(usedCells);
        
        assertNotNull(result);
        assertEquals(2, result.length); 
        
        assertNotEquals(result[0], result[1]);
        assertFalse(usedCells.contains(result[0]));
        assertFalse(usedCells.contains(result[1]));
    }

    @Test
    public void testRemoveTower(){
        IceTower t = new IceTower(board.getCell(0, 0));
        board.addTower(t);
        assertEquals(board.getTowers().size(), 1);
        board.removeTower(t);
        assertTrue(board.getTowers().isEmpty());
    }

    @Test
    public void isPlaceableTest(){
        for (int i =0; i<5; i++)
            for (int j=0; j<5; j++)
                assertTrue(board.isPlaceable(i, j));
    }
}