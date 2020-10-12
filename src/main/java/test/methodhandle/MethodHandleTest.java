package test.methodhandle;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.math.BigDecimal;

class MethodHandleTest {
    public static void main(String[] args) throws Throwable {
        // Obtain a lookup context
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        // method arguments type
        MethodType mt = MethodType.methodType(BigDecimal.class, int.class);
        // method reference to BigDecimal.pow(BigDecimal, int);
        MethodHandle power = lookup.findVirtual(BigDecimal.class, "pow", mt);

        // invoke method
        BigDecimal p = (BigDecimal) power.invoke(new BigDecimal("5"), 2);
        System.out.println(p);
        p = (BigDecimal) power.invoke(new BigDecimal("5"), (byte) 2);
        System.out.println(p);
        p = (BigDecimal) power.invoke(new BigDecimal("5"), new Integer(2));
        System.out.println(p);

        // exact
        p = (BigDecimal) power.invokeExact(new BigDecimal("5"), 2);
        System.out.println(p);
        // can't
//        p = (BigDecimal) power.invokeExact(new BigDecimal("5"), (byte) 2);
//        p = (BigDecimal) power.invokeExact(new BigDecimal("5"), new Integer(2));
    }
}
