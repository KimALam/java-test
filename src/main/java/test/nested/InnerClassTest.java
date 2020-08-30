package test.nested;

public class InnerClassTest {
    private long a = 20;
    private String type = "long";

    private void printOuter() {
        MyNumber num = new MyNumber();
//        System.out.println("a = " + num.a);
//        System.out.println("type = " + num.type);
        num.printInner();
    }

    MyNumber getMyNumber() {
        return new MyNumber();
    }

    class MyNumber {
        int a = 10;
        String type = "Integer";

        public MyNumber() {}

        void printInner() {
            System.out.println("out.a = " + InnerClassTest.this.a);
            System.out.println("out.type = " + InnerClassTest.this.type);
            System.out.println("inner.a = " + this.a);
            System.out.println("inner.type = " + this.type);
        }
    }

    public static void main(String[] args) {
        InnerClassTest in = new InnerClassTest();
        InnerClassTest2 in2 = new InnerClassTest2();
        in2.printInnerInTest2(in);
    }

    private static class MyNumberStatic {
        private int a = 10;
        private String type = "Integer";

        public MyNumberStatic() {}

        private void printInner() {
//            System.out.println("out.a = " + InnerClassTest.this.a);
//            System.out.println("out.type = " + InnerClassTest.this.type);
//            System.out.println("inner.a = " + this.a);
//            System.out.println("inner.type = " + this.type);
        }
    }
}
