package security.signature.jar;

public class TestAAA {
    private TestBBB bbb;

    public TestAAA() {
        System.out.println("[TestAAA] : " + getClass().getClassLoader());
        bbb = new TestBBB();
    }

    public void printClassLoader() {
        System.out.println("[TestAAA] printClassLoader() : " + getClass().getClassLoader());
    }
}
