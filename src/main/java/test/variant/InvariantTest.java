package test.variant;

import java.util.ArrayList;
import java.util.List;

class InvariantTest {
    public static void main(String[] args) {
        List<Integer> integerList = new ArrayList<>();
        integerList.add(0);
        integerList.add(1);
        integerList.add(2);

        // invariant: A feature does not allow any of substitutions.
        // generic is invariant.
//        List<Number> numberList = integerList; // compile error.

        // wildcard extend generic is covariant.
        List<? extends Number> numberList = integerList;
//        numberList.add(10); // compile error.

        for (Number n : numberList) {
            System.out.println(n);
        }
    }
}
