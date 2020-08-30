package test.generic;

public class ConstrainMe {
    public static void main(String[] args) {
        System.out.println(SubClassA.INSTANCE.getMe());
        System.out.println(SubClassB.INSTANCE.getMe());

        System.out.println(SubClassA.INSTANCE.getClass());
        System.out.println(SubClassB.INSTANCE.getClass());
    }

    // Restrict generic type T to class of "extends me".
    private static abstract class SuperClass<T extends SuperClass<T>> {
        abstract public T getMe();
    }

    private static class SubClassA extends SuperClass<SubClassA> {
        public static final SubClassA INSTANCE = new SubClassA();

        private SubClassA() {}

        @Override
        public SubClassA getMe() {
            return INSTANCE;
        }
    }

    private static class SubClassB extends SuperClass {
        public static final SubClassB INSTANCE = new SubClassB();

        private SubClassB() {}

        @Override
        public SuperClass getMe() {
            return INSTANCE;
        }
    }
}
