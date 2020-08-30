package test.lambda_exception;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class LambdaException {
    private List<Integer> integers = Arrays.asList(3, 9, 7, 0, 20);

    static Integer readFromFile(Integer integer) throws IOException {
        throw new IOException();
    }

    static void doTest() {
//        Consumer<Integer> consumer = i -> readFromFile(i);

        CheckedConsumer<Integer, IOException> consumer1 = i -> readFromFile(i);
    }

    @FunctionalInterface
    private interface CheckedConsumer<T, E extends Exception> {
        void accept(T t) throws E;
    }
}
