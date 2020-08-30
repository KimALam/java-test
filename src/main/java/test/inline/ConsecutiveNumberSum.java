package test.inline;

public class ConsecutiveNumberSum {
    private long totalSum;
    private int totalNumbers;

    public ConsecutiveNumberSum(int totalNumbers) {
        this.totalNumbers = totalNumbers;
    }

    public long getTotalSum() {
        totalSum = 0;
        for (int i = 0; i < totalNumbers; i++) {
            totalSum += i;
        }
        return totalSum;
    }
}
