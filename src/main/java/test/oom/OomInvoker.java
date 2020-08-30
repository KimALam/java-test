package test.oom;

import java.util.ArrayList;
import java.util.List;

public class OomInvoker {
    public static void main(String[] args) {
        List<Inner> list = new ArrayList<>();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            list.add(new Inner(i));
        }
        System.out.println("size: " + list.size());
    }

    private static class Inner {
        private int i;

        public Inner(int i) {
            this.i = i;
        }
    }
}
