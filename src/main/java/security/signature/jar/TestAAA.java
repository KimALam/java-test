package security.signature.jar;

class TestAAA {
    TestBBB bbb = new TestBBB();

    void printClassLoader() {
        System.out.println("[TestAAA] cl : " + getClass().getClassLoader());
        bbb.printClassLoader();
    }
}
