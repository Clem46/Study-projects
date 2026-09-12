package btd.boards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import btd.towers.towers.*;

/** A classic board with random path generation */
public class ClassicBoard extends Board {
    /**
     * Creates a classic board
     * @param sizex width
     * @param sizey height
     */
    public ClassicBoard(int sizex, int sizey) {
        super(sizex, sizey);
        List<Cell> Path = null;
        Cell[] cells;
        while (Path == null){
            int[] maxTry = {sizex*sizey};
            cells = this.randomCell(new ArrayList<>());
            Path = this.createPath(cells[0], cells[1], new ArrayList<>(), new HashSet<>(), maxTry);
        }
        this.path.add(Path);
        for (List<Cell> tempPath : this.getPath()) {
            for (Cell cell : tempPath) {
                cell.switchIsOccupied();
                if(!this.path.get(0).contains(cell)){
                    cell.setState(StateCell.PATH);
                }
            }
        }
    }

    @Override
    public List<Cell> createPath(Cell curr, Cell finale, List<Cell> list, Set<Cell> visited, int[] maxTry) {
        maxTry[0]--;
        if (maxTry[0] <= 0) return null;
        list.add(curr);
        visited.add(curr);
        if (curr.equals(finale)) return list;
        List<Cell> neighbor = this.getNeighbours(curr);
        Collections.shuffle(neighbor);
        for (Cell next : neighbor) {
            if (!(visited.contains(next)) && (super.countPathNeighbors(next, list) == 1)) {
                List<Cell> result = createPath(next, finale, list, visited, maxTry);
                if (result != null)
                    return result;
            }
        }
        visited.remove(curr);   
        list.remove(list.size()-1);
        return null;
    }
    
    @Override
    public Cell[] randomCell(List<Cell> cellAlreadyUsed) {
        Random r = new Random();
        int x1 = r.nextInt(this.board.length);
        int x2 = r.nextInt(this.board.length);
        Cell[] cells = new Cell[2];
        cells[0] = this.getCell(x1, 0);
        cells[1] = this.getCell(x2, this.board[0].length - 1);
        return cells;
    }
    
    @Override
    public void addTower(Tower t) {

        super.addTower(t);
        this.getCell(t.getCell().getX(), t.getCell().getY()).switchIsOccupied();
    }

    @Override
    public void removeTower(Tower t){
        this.towers.remove(t);
        t.getCell().popState();
    }
}
