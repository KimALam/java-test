package test.inline;

public class InlineTestMain {
    private static final int NUMBERS_OF_ITERATIONS = 15_000;

    public static void main(String[] args) {
        for (int i = 1; i < NUMBERS_OF_ITERATIONS; i++) {
            calculateSum(i);
        }
    }

    private static long calculateSum(int n) {
        return new ConsecutiveNumberSum(n).getTotalSum();
    }
}
