package test.nested;

public class InnerClassTest2 {
    public void printInnerInTest2(InnerClassTest test) {
        System.out.println("Inner test 2");
        test.getMyNumber().printInner();
    }
}
