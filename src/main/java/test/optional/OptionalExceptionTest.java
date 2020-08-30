package test.optional;

import java.util.Optional;

public class OptionalExceptionTest {
    public static void main(String[] args) {
        Optional<String> op = Optional.ofNullable(null);
        op.ifPresent(i -> System.out.println(i));
        System.out.println("main: " + op);
    }

    private static String getString() {
//        throw new RuntimeException();
        return "abc";
    }
}
