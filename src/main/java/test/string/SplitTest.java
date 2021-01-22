package test.string;

public class SplitTest {
    public static void main(String[] args) {
        String a = "abc";
        String[] sp = a.split(":");

        System.out.println("size : " + sp.length);
        for (String s : sp) {
            System.out.println(s);
        }
    }
}
