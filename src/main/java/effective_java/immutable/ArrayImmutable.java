package effective_java.immutable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArrayImmutable {
    // Potential security hole!
//    public static final Thing[] VALUES = {...}

    private static final Thing[] PRIVATE_VALUES = new Thing[5];
    // 1. array immutable
    public static final List<Thing> VALUES = Collections.unmodifiableList(Arrays.asList(PRIVATE_VALUES));
    // 2. array immutable
    public static final Thing[] values() {
        return PRIVATE_VALUES.clone();
    }

    private static class Thing {}
}
