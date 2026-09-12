package btd.boards;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import btd.towers.towers.Tower;

/** A board with linear paths */
public class LinearBoard extends Board {

    /**
     * Creates a linear board
     * @param sizex width
     * @param sizey height
     * @param numberPath number of paths
     */
    public LinearBoard(int sizex, int sizey, int numberPath) throws IllegalArgumentException {
        super(sizex, sizey);
        if (numberPath >= (sizex + sizey) / 2) {
            throw new IllegalArgumentException("Le nombre de chemin est trop élevé.");
        }
        List<Cell> cellAlreadyUsed = new ArrayList<>();
        for (int i = 0; i < numberPath; i++) {
            int[] a = {0};
            Cell[] cells = this.randomCell(cellAlreadyUsed);
            cellAlreadyUsed.add(cells[0]);
            cellAlreadyUsed.add(cells[1]);
            this.path.add(this.createPath(cells[0], cells[1], new ArrayList<>(), new HashSet<>(), a));
        }
        for (List<Cell> tempPath : this.path) {
            for (Cell cell : tempPath) {
                cell.setState(StateCell.PATH);
            }
        }
    }

    @Override
    public List<Cell> createPath(Cell curr, Cell finale, List<Cell> list, Set<Cell> visited, int[] maxtry) throws IllegalArgumentException {
        if (curr.getX() != finale.getX() && curr.getY() != finale.getY()) {
            throw new IllegalArgumentException("Cells must be in the same row or column");
        }
        list.add(curr);

        if (curr.getX() == finale.getX()) {
            int diffY = finale.getY() - curr.getY();
            int distance = Math.abs(diffY);
            int step = (diffY > 0) ? 1 : -1;

            for (int i = 1; i < distance; i++) {
                list.add(getCell(curr.getX(), curr.getY() + (i * step)));
            }
        } else {
            int diffX = finale.getX() - curr.getX();
            int distance = Math.abs(diffX);
            int step = (diffX > 0) ? 1 : -1;

            for (int i = 1; i < distance; i++) {
                list.add(getCell(curr.getX() + (i * step), curr.getY()));
            }
        }

        if (!curr.equals(finale)) {
            list.add(finale);
        }

        return list;
    }

    /**
     * Return two random cells from the array that are not used for path.
     * @return Cell[]
     */
    @Override
    public Cell[] randomCell(List<Cell> cellAlreadyUsed) {
        Cell[] cells = new Cell[2];
        Random r = new Random();
        int i = r.nextInt(2);
        Cell c1;
        Cell c2;
        if (i == 1) { // Verticale
            int index = r.nextInt(this.board[0].length);
            if (cellAlreadyUsed.contains(this.board[0][index])
                    || cellAlreadyUsed.contains(this.board[this.board.length - 1][index])) {
                return randomCell(cellAlreadyUsed);
            }
            c1 = this.board[0][index];
            c2 = this.board[this.board.length - 1][index];
        } else { // Horizontale
            int index = r.nextInt(this.board.length);
            if (cellAlreadyUsed.contains(this.board[index][0])
                    || cellAlreadyUsed.contains(this.board[index][this.board[0].length - 1])) {
                return randomCell(cellAlreadyUsed);
            }
            c1 = this.board[index][0];
            c2 = this.board[index][this.board[0].length - 1];
        }
        i = r.nextInt(2);
        cells[i] = c1;
        cells[1 - i] = c2;
        return cells;
    }
    
    @Override
    public void removeTower(Tower t){
        this.towers.remove(t);
    }
}
