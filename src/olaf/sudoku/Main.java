package olaf.sudoku;

public class Main {

    public static void main(String[] args) {
        Grid.size = 9;
        Block.maxDigit = 9;
        Cell.maxDigit = 9;

        Grid grid = new Grid();

        grid.setStartingValue(new Coords(1, 1), 7);
        grid.setStartingValue(new Coords(9, 1), 3);
        grid.setStartingValue(new Coords(4, 2), 2);
        grid.setStartingValue(new Coords(4, 3), 1);
        grid.setStartingValue(new Coords(2, 9), 8);

        grid.display();
        Integer result = grid.tryToSolve(true);
        System.out.println(result);
        grid.display();
    }
}
