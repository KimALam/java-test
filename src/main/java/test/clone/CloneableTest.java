package test.clone;

import lombok.ToString;

public class CloneableTest {
    public static void main(String[] args) {
        BaseClass base = new BaseClass("aaaa", 1111);
        System.out.println("base : " + base);
        BaseClass cloned = base.clone();
        System.out.println("cloned : " + cloned);
    }

    @ToString
    private static class BaseClass implements Cloneable {
        String a;
        Integer b;

        public BaseClass() {}
        public BaseClass(String a, Integer b) {
            this.a = a;
            this.b = b;
        }

        @Override
        protected BaseClass clone() {
            try {
                return (BaseClass) super.clone();
            } catch (CloneNotSupportedException e) {
            }
            return null;
        }

        public String canOverride(int i) {
            return null;
        }
    }
}
