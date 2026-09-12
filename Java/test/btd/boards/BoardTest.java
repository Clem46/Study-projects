package btd.boards;

import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;


public class BoardTest {
    @Test
    public void testIfNeighborsWorking(){
        Board b1 = new ClassicBoard(5, 5);
        Cell c1 = new Cell(1, 1);
        Cell c2 = new Cell(0, 0);
        List<Cell> neighbors = new ArrayList<>();
        neighbors.add(b1.getCell(0, 1));
        neighbors.add(b1.getCell(2, 1));
        neighbors.add(b1.getCell(1, 0));
        neighbors.add(b1.getCell(1, 2));
        assertEquals(neighbors, b1.getNeighbours(c1));
        neighbors = new ArrayList<>();
        neighbors.add(new Cell(1, 0));
        neighbors.add(new Cell(0, 1));
        assertEquals(neighbors, b1.getNeighbours(c2));
    }
}
