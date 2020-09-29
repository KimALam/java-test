package test.optional;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

class ElseTest {
    public static void main(String[] args) {
        Optional<String> a = Optional.of("a");
        Optional emptyOptional = Optional.empty();
        Optional alsoEmpty = Optional.ofNullable(null);

        System.out.println("empty: " + a.orElse("b"));
        System.out.println("also: " + alsoEmpty.orElseGet(() -> "c"));
    }

    @Setter
    @Getter
    private static class Employee {
        private String name;
        private int age;
        private List<Address> addresses;
    }

    @Getter
    @Setter
    private static class Address {
        private int zip;
        private String addr1;
        private String addr2;
    }
}
