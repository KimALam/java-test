package test.type;

import java.util.ArrayList;
import java.util.List;

public class LonglongTest {
    public static void main(String[] args) {
//        Long l = Long.valueOf("1234");
//        System.out.println(l);

        Impl impl1 = new Impl("aaaa");
        List list = new ArrayList<Impl>();

    }

    private static void getList(List<Inf> infs) {
        System.out.println("print infs : " + infs);
    }

    private interface Inf {
        void printName();
    }

    private static class Impl implements Inf {
        private String name;

        public Impl(String name) {
            this.name = name;
        }

        @Override
        public void printName() {
            System.out.println("My name is : " + name);
        }
    }
}
