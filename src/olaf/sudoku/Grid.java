package olaf.sudoku;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Grid {
    static int size;

    private Cell firstCell;
    private Cell[][] cells;
    private Block[] rows;
    private Block[] cols;
    private Block[] squares;
    private int noOfSolutions;

    public Grid() {
        rows = new Block[size];
        cols = new Block[size];
        squares = new Block[9];
        cells = new Cell[size][size];
        for (int i = 0; i < size; i++) {
            rows[i] = new Block(size);
            cols[i] = new Block(size);
        }
        for (int i = 0; i < 9; i++) {
            squares[i] = new Block(size == 9 ? 9 : 1);
        }
        Cell nextCell = null;
        for (int y = size; y >= 1; y--) {
            for (int x = size; x >= 1; x--) {
                Coords coords = new Coords(x, y);
                int colIndex = getColIndex(coords);
                int rowIndex = getRowIndex(coords);
                int squareIndex = getSquareIndex(coords);
                Cell cell = new Cell(x, y, nextCell);
                cells[colIndex][rowIndex] = cell;
                Block row = rows[rowIndex];
                Block col = cols[colIndex];
                Block square = squares[squareIndex];
                row.addCell(cell);
                col.addCell(cell);
                square.addCell(cell);
                cell.setRow(row);
                cell.setCol(col);
                cell.setSquare(square);
                nextCell = cell;
                firstCell = cell;
            }
        }
    }

    public void display() {
        Coords coords;
        Cell cell;
        String cellOutput, preOutput, postOutput;
        String rowDisplay = size == 9 ? "--------------------------------------" : "-------------";
        for (int y = size; y >= 1; y--) {
            System.out.println(rowDisplay);
            System.out.print("|");
            for (int x = 1; x <= size; x++) {
                coords = new Coords(x, y);
                cell = cells[getColIndex(coords)][getRowIndex(coords)];
                if (cell.isFixed()) {
                    preOutput = "<";
                    postOutput = ">";
                } else {
                    preOutput = postOutput = " ";
                }
                cellOutput = (cell.getValue() == null)
                        ? " "
                        : cell.getValue().toString();
                cellOutput = preOutput + cellOutput + postOutput;
                System.out.print(cellOutput + "|");
            }
            System.out.println();
        }
        System.out.println(rowDisplay);
    }

    public void setStartingValue(Coords coords, Integer value) {
        int rowIndex = getRowIndex(coords);
        int colIndex = getColIndex(coords);
        cells[colIndex][rowIndex].setValue(value);
        cells[colIndex][rowIndex].setFixed(true);
    }

    public Integer tryToSolve(boolean assumeUnique) {
        return firstCell.tryToFill(assumeUnique);
    }

    static int getRowIndex(Coords coords) {
        return coords.getY() - 1;
    }

    static int getColIndex(Coords coords) {
        return coords.getX() - 1;
    }

    static int getSquareIndex(Coords coords) {
        if (size == 9) return (getRowIndex(coords) / 3) * 3 + (getColIndex(coords) / 3);
        return getRowIndex(coords) * 3 + getColIndex(coords);
    }

}
