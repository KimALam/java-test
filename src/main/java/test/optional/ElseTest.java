package test.optional;

import java.util.Optional;

public class ElseTest {
    public static void main(String[] args) {
        Optional<String> a = Optional.of("a");
        Optional emptyOptional = Optional.empty();
        Optional alsoEmpty = Optional.ofNullable(null);

        System.out.println("empty: " + a.orElse("b"));
        System.out.println("also: " + alsoEmpty.orElseGet(() -> "c"));
    }
}
