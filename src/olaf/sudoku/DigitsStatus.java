package olaf.sudoku;

public class DigitsStatus {
    private int noDigits;
    private int maxDigit;
    private boolean[] statuses;

    public DigitsStatus(int maxDigit) {
        this.maxDigit = maxDigit;
        this.statuses = new boolean[maxDigit];
    }

    public boolean isUsed(int digit) {
        return this.statuses[digit - 1];
    }

    public void setUsed(int digit) {
        this.statuses[digit - 1] = true;
    }

    public DigitsStatus or(DigitsStatus argument) {
        DigitsStatus result = new DigitsStatus(maxDigit);
        for (int digit = 1; digit <= maxDigit; digit++) {
            if (this.isUsed(digit) || argument.isUsed(digit)) result.setUsed(digit);
        }
        return result;
    }
}
