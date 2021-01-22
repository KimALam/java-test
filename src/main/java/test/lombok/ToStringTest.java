package test.lombok;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class ToStringTest {
    public static void main(String[] args) {
        Sub a = new Sub();
        System.out.println("print : " + a);
    }

    @Getter
    @NoArgsConstructor
    @ToString
    private static class Super {
        private String a = "super";
    }

    @Getter
    @NoArgsConstructor
    @ToString(callSuper = true)
    private static class Sub extends Super {
        private String b = "sub";
    }
}
