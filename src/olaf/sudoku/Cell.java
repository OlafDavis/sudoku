package olaf.sudoku;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Cell {
    static int maxDigit;

    private Coords coords;
    private Block row;
    private Block col;
    private Block square;
    private Integer value;
    private boolean fixed;
    private Cell nextCell;

    public Cell(int x, int y, Cell nextCell) {
        this.coords = new Coords(x, y);
        this.nextCell = nextCell;
    }

    public Integer getValue() {
        return value;
    }

    public boolean isEmpty() {
        return (this.value == null);
    }

    public boolean isFinalCell() {
        return (this.getNextCell() == null);
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public boolean isFixed() {
        return fixed;
    }

    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }

    public Block getRow() {
        return row;
    }

    public void setRow(Block row) { this.row = row; }

    public Block getCol() {
        return col;
    }

    public void setCol(Block col) { this.col = col; }

    public Block getSquare() {
        return square;
    }

    public void setSquare(Block square) { this.square = square; }

    public Cell getNextCell() {
        return nextCell;
    }

    public Integer tryToFill(boolean assumeUnique) {
        System.out.println(coords.getX() + ", " + coords.getY() + ", currently " + value);
        if (isFixed()) return isFinalCell()
            ? 1
            : getNextCell().tryToFill(assumeUnique);
        DigitsStatus rowStatus = getRow().getDigitStatus();
        DigitsStatus colStatus = getCol().getDigitStatus();
        DigitsStatus squareStatus = getSquare().getDigitStatus();
        DigitsStatus status = rowStatus.or(colStatus).or(squareStatus);
        int[] possibleDigits = IntStream.rangeClosed(1, maxDigit)
            .filter(d -> !status.isUsed(d))
            .toArray();
        int numPossibilities = possibleDigits.length;
        System.out.println("possibilities: " + Arrays.toString(possibleDigits));
        for (int i = 0; i < numPossibilities; i++) {
            setValue(possibleDigits[i]);
            System.out.println("select " + value);
            if (isFinalCell()) return 1;
            if (getNextCell().tryToFill(assumeUnique) == 1) return 1;
            System.out.println("retry " + coords.getX() + ", " + coords.getY() + ", currently " + value);
        }
        System.out.println("no possibilities for " + coords.getX() + " " + coords.getY());
        setValue(null);
        return 0;
    }
}
