package test.design.pattern;

public class TestMain {
    public static void main(String[] args) {
        String a = "aaa";
        String b = "aaa";
        System.out.println("a == b: " + (a == b));

        String as = new String("aaa");
        String bs = new String("aaa");
        System.out.println("as == bs: " + (as == bs));

        System.out.println("a.intern == b.inter: " + (a.intern() == b.intern()));
        System.out.println("as.intern == bs.inter: " + (as.intern() == bs.intern()));
        System.out.println("a.intern == as.inter: " + (a.intern() == as.intern()));
    }
}
