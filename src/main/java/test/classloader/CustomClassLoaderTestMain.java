package test.classloader;

class CustomClassLoaderTestMain {
    public static void main(String[] args) throws Exception {
        FileClassLoader loader = new FileClassLoader("/Users/alkim78/test");

        Class klass = loader.loadClass("test.classloader.Hello");
        Object obj = klass.newInstance();
        Hello.class.cast(obj).printMe();

        System.out.println(obj.getClass().getName());
    }
}
