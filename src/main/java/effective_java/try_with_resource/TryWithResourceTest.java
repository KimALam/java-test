package effective_java.try_with_resource;

public class TryWithResourceTest {
    public static void main(String[] args) {
        try (A a = new A()) {
            System.out.println("In try()...");
        } catch (Exception e) {
            System.out.println("In catch()...");
        }
    }

    static class A implements AutoCloseable {
        public A() {
            System.out.println("new A()...");
        }

        @Override
        public void close() throws Exception {
            System.out.println("A.close()...");
        }
    }
}
