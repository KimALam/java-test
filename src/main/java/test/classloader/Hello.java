package test.classloader;

class Hello {
    void printMe() {
        System.out.println("Hello : " + getClass().getClassLoader());
    }
}
