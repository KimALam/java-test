package security.signature.jar;

public class TestBBB {
    public TestBBB() {
        System.out.println("[TestBBB] : " + getClass().getClassLoader());
    }

    public void printClassLoader() {
        System.out.println("[TestBBB]printClassLoader() : " + getClass().getClassLoader());
    }
}
