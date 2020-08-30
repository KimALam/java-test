package test.inheritance;

import java.util.Objects;

class EqualsInheritTestMain {
    public static void main(String[] args) {
        SuperClass spc1 = new SuperClass(1, 2);
        SuperClass sbc1 = new SubClass(1, 2, 3);

        System.out.println(spc1.equals(sbc1));
        System.out.println(sbc1.equals(spc1));
    }

    private static class SuperClass {
        private int a;
        private int b;

        public SuperClass(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public boolean equals(Object o) {
            System.out.println("SuperClass.equals()..." + o);

            if (this == o) return true;
            if (!(o instanceof SuperClass)) return false;
            SuperClass that = (SuperClass) o;
            return a == that.a &&
                    b == that.b;
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b);
        }
    }

    private static class SubClass extends SuperClass {
        private int c;

        public SubClass(int a, int b, int c) {
            super(a, b);
            this.c = c;
        }
    }
}
