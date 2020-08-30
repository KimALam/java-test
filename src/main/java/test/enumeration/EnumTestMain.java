package test.enumeration;

public class EnumTestMain {
    public static void main(String[] args) {
        for (TestEnum e : TestEnum.values()) {
            System.out.println("enum: " + e);
            System.out.println("name(): " + e.name());
            System.out.println("toString(): " + e.toString());
        }
        System.out.println(TestEnum.valueOf("FIRST"));
    }

    public enum TestEnum {
        FIRST("first"),
        SECOND("second"),
        THIRD("third");

        private final String label;

        TestEnum(String label) {
            this.label = label;
        }

        @Override
        public String toString() {
            return this.label;
        }
    }
}
