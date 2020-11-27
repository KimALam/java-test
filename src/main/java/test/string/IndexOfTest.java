package test.string;

class IndexOfTest {
    public static void main(String[] args) {
        String TEXT = "abccba";
        System.out.println("length : " + TEXT.length());
        System.out.println("indexOf : " + TEXT.indexOf("a"));
        System.out.println("lastIndexOf : " + TEXT.lastIndexOf("a"));
        System.out.println("lastIndexOf + 1 : " + TEXT.substring(TEXT.lastIndexOf("a") + 1));
        System.out.println("lastIndexOf + 1 : " + TEXT.substring(TEXT.lastIndexOf("a") + 2));
    }
}
