package olaf.sudoku;

public class Block {
    static int maxDigit;
    private Cell[] cells;
    private int numCells;
    private int size;

    public Block(int size) {
        this.size = size;
        cells = new Cell[size];
        numCells = 0;
    }

    public DigitsStatus getDigitStatus() {
        DigitsStatus status = new DigitsStatus(maxDigit);
        for (int i = 0; i < size; i++) {
            Integer value = this.cells[i].getValue();
            if (value != null) status.setUsed(value);
        }
        return status;
    }

    public void addCell(Cell cell) {
        if (numCells >= size) System.out.println("Block is full");
        this.cells[numCells++] = cell;
    }
}
