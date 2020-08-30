package test.initialization;

import java.util.Collections;
import java.util.Map;

public class InitialBlockMain {
    public static void main(String[] args) {
        TestClass tc = new TestClass();
        System.out.println(tc);
        tc.printField();

    }

    private static class TestClass {
        private Map<Integer, Integer> map;
        private int a;
        private final int b;
        private static int c;

        {
            map = getMap();
            a = retNumber();
            b = 3;
            c = retNumber();
        }

        public void printField() {
            System.out.println("a: " + a);
            System.out.println("b: " + b);
            System.out.println("c: " + c);
            System.out.println("map: " + map);
        }

        public int retNumber() {
            return 1;
        }

        public Map<Integer, Integer> getMap() {
            return Collections.emptyMap();
        }

        @Override
        public String toString() {
            return "TestClass{" +
                    "map=" + map +
                    ", a=" + a +
                    ", b=" + b +
                    '}';
        }
    }
}
