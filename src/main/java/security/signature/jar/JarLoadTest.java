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
            // AppClassLoader가 아닌 JarLoader에 의해 TestAAA, TestBBB를 로딩하기 위해서는
            // build 된 target/classes 폴더에서 TestAAA.class와 TestBBB.class를 삭제 후 아래 실행.
            // java -cp ./classes security.signature.jar.JarLoadTest
            Class cls = jl.loadClass("security.signature.jar.TestAAA");
            System.out.println("[JarLoadTest] cl : " + getClass().getClassLoader());
            System.out.println("cls : " + cls.getName());
            Object aaa = cls.newInstance();

            // 주의) 캐스팅 안됨: ta의 class loader와 aaa의 class loader가 다르기 때문?
//            TestAAA ta = (TestAAA) aaa;
//            ((TestAAA)aaa).printClassLoader();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
