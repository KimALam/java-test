package effective_java.enums;

import java.util.EnumSet;
import java.util.Optional;

class OperationTestMain {
    public static void main(String[] args) {
        double x = 3L;
        double y = 2L;
        for (Operation op : Operation.values()) {
            System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
        }

        Optional<Operation> op = Operation.fromString("+");
        System.out.println("op: " + op.get());
    }
}
