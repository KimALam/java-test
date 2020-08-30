package test.variant;

class CovariantTest {
    public static void main(String[] args) {
        Integer[] integers = new Integer[10];
        for (int i = 0; i < integers.length; i++) {
            integers[i] = i;
        }

        // covariant: A feature which allows to substitute a subtype with supertype.
        // array is covariant.
        Number[] numbers = integers;
        for (int i = 0; i < numbers.length; i++) {
            System.out.println(numbers[i]);
        }
    }
}
