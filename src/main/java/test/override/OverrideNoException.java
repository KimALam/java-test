package test.override;

import java.io.IOException;

class OverrideNoException {
    public static void main(String[] args) {
        Child c = new Child();
        c.printName();
    }

    private static class Parent {
        void printName() throws IOException {
            System.out.println("Parent...");
        }
    }

    private static class Child extends Parent {
        @Override
        void printName() {
            System.out.println("Child...");
        }
    }
}
