package security.signature.jar;

public class TestAAA {
    public TestAAA() {
        System.out.println("TestAAA : " + getClass().getClassLoader());
    }

    void printClassLoader() {
        System.out.println("[TestAAA] cl : " + getClass().getClassLoader());
    }
}
