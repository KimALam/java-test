package test.nested;

import java.lang.reflect.Field;

class NestedTestMain {
    public static void main(String[] args) throws Exception {
        new Nest1().f();
    }

    private static class Nest1 {
        private int nest1Val = 1;

        public void f() throws Exception {
            Nest2 n2 = new Nest2();
            // ok
            System.out.println("nest2 value: " + n2.nest2Val);

            // not ok: nested base access-control(java11 supported)
            Field f2 = Nest2.class.getDeclaredField("nest2Val");
            f2.setInt(n2, 4);
        }
    }

    private static class Nest2 {
        private int nest2Val = 2;
    }
}
