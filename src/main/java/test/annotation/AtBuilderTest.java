package test.annotation;

class AtBuilderTest {
    public static void main(String[] args) {
        BuilderClass bc = BuilderClass.builder().build();
        System.out.println("bc : " + bc);
    }
}
