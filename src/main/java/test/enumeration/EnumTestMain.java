package test.enumeration;

public class EnumTestMain {
    public static void main(String[] args) {
        for (TestEnum e : TestEnum.values()) {
            System.out.println("enum: " + e);
            System.out.println("name(): " + e.name());
            System.out.println("toString(): " + e.toString());
        }
        System.out.println("valueOf : " + TestEnum.valueOf("FIRST"));
    }

    public enum TestEnum {
        FIRST("1111", "1010"),
        SECOND("2222", "2020"),
        THIRD("3333", "3030");

        private final String label;
        private final String label2;

        TestEnum(String label, String label2) {
            this.label = label;
            this.label2 = label2;
        }

        @Override
        public String toString() {
            return this.label;
        }
    }
}
