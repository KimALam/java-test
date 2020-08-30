package test.variant;

import java.util.ArrayList;
import java.util.List;

class ContravariantTest {
    public static void main(String[] args) {
        List<A> aList = new ArrayList<>();
        aList.add(new A());

        // contravariant: A feature which allows to substitute supertype with subtype.
        // wildcard super generic is contravariant.
        List<? super B> bList = aList;
        bList.add(new B());
//        bList.add(new A()); // compile error.

        for (int i = 0; i < bList.size(); i++) {
            System.out.println(bList.get(i));
        }
    }

    private static class A {
        @Override
        public String toString() {
            return "A";
        }
    }

    private static class B extends A {
        @Override
        public String toString() {
            return "B";
        }
    }
}
