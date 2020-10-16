package security.signature.jar;

class JarLoadTest {
    private static final String JAR_PATH = "file:///Users/alkim78/test/java-test/src/main/resources/security/";
    private static final String JAR_NAME = "unsigned.jar";

    public static void main(String[] args) {
        JarLoadTest jlt = new JarLoadTest();
        jlt.testClassLoading();
    }

    private void testClassLoading() {

        JarLoader jl = new JarLoader(JAR_PATH, this.getClass().getClassLoader());
        jl.readJarFile(JAR_NAME);

        try {
            Class cls = jl.loadClass("security.signature.jar.TestAAA");
            System.out.println("[JarLoadTest] cl : " + getClass().getClassLoader());
            System.out.println("cls : " + cls.getName());
            TestAAA aaa = (TestAAA) cls.newInstance();
            aaa.printClassLoader();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
