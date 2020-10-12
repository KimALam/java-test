package test.methodhandle;

import java.util.function.IntPredicate;

class LambdaTest {
    public static void main(String[] args) {
        IntPredicate p = n -> n > 5;
    }
}
