package test.classloader;

class CustomClassLoaderTestMain {
    public static void main(String[] args) throws Exception {
        FileClassLoader loader = new FileClassLoader("/Users/a1101179/test");

        Class klass = loader.loadClass("test.classloader.myclass.Hello");
        Object obj = klass.newInstance();

        System.out.println(obj.getClass().getName());
    }
}
