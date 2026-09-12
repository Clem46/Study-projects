package btd.utils;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import btd.boards.*;
import btd.exceptions.NotEnoughCreditsException;
import btd.towers.towers.IceTower;
import btd.towers.towers.Tower;

public class GameTest {
    private Game game;
    private Board board;
    private Player player;
    private Cell cell1;
    private Cell cell2;

    @BeforeEach
    public void before() {
        board = new ClassicBoard(10, 10);
        player = new Player();
        game = new Game(board, player);
        cell1 = new Cell(0, 0);
        cell2 = new Cell(5, 5);
    }

    @Test
    public void DistanceSquaredOfTest() {
        assertEquals(50* Cell.SIZE*Cell.SIZE, Game.distanceSquaredOf(cell1, cell2));        
    }

    @Test
    public void buyTowerTest() throws NotEnoughCreditsException {
        Cell target = board.getCell(2, 2);
        Tower ice = new IceTower(target);
        
        assertNotEquals(StateCell.TOWER, target.getState());
        game.buyTower(ice);

        assertEquals(StateCell.TOWER, board.getCell(2, 2).getState());
        assertTrue(board.getTowers().contains(ice));
    }
}