package olaf.sudoku;

public class Tests {
    public static void main(String[] args) {
        Grid.size = 9;
        testDigitStatus();
        testGrid();
        testCoords();
        Grid.size = 3;
        testGrid3by3();
    }

    private static void testDigitStatus() {
        DigitsStatus status1 = new DigitsStatus(9);
        DigitsStatus status2 = new DigitsStatus(9);

        status1.setUsed(3);
        status1.setUsed(9);

        assert(status1.isUsed(3));
        assert(status1.isUsed(9));
        assert(!status1.isUsed(1));

        status2.setUsed(6);
        DigitsStatus combined = status1.or(status2);

        assert(!status1.isUsed(6));
        assert(status2.isUsed(6));
        assert(!status2.isUsed(3));
        assert(combined.isUsed(6));
        assert(combined.isUsed(3));
        assert(!combined.isUsed(8));
    }

    private static void testCoords() {
        Coords coords;

        coords = new Coords(1,7);
        assert(coords.getX() == 1);
        assert(coords.getY() == 7);
    }

    private static void testGrid() {
        Coords coords;

        coords = new Coords(1, 1);

        assert(Grid.getColIndex(coords) == 0);
        assert(Grid.getRowIndex(coords) == 0);
        assert(Grid.getSquareIndex(coords) == 0);

        coords = new Coords(1, 9);

        assert(Grid.getColIndex(coords) == 0);
        assert(Grid.getRowIndex(coords) == 8);
        assert(Grid.getSquareIndex(coords) == 6);

        coords = new Coords(3, 4);

        assert(Grid.getColIndex(coords) == 2);
        assert(Grid.getRowIndex(coords) == 3);
        assert(Grid.getSquareIndex(coords) == 3);

        coords = new Coords(7, 5);

        assert(Grid.getColIndex(coords) == 6);
        assert(Grid.getRowIndex(coords) == 4);
        assert(Grid.getSquareIndex(coords) == 5);
    }

    private static void testGrid3by3() {
        Coords coords;

        coords = new Coords(1, 1);

        assert(Grid.getColIndex(coords) == 0);
        assert(Grid.getRowIndex(coords) == 0);
        assert(Grid.getSquareIndex(coords) == 0);

        coords = new Coords(1, 2);

        assert(Grid.getColIndex(coords) == 0);
        assert(Grid.getRowIndex(coords) == 1);
        assert(Grid.getSquareIndex(coords) == 3);

        coords = new Coords(2, 3);

        assert(Grid.getColIndex(coords) == 1);
        assert(Grid.getRowIndex(coords) == 2);
        assert(Grid.getSquareIndex(coords) == 7);

        coords = new Coords(3, 2);

        assert(Grid.getColIndex(coords) == 2);
        assert(Grid.getRowIndex(coords) == 1);
        assert(Grid.getSquareIndex(coords) == 5);
    }
}
