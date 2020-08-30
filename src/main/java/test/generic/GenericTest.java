package test.generic;

import java.util.ArrayList;
import java.util.List;

public class GenericTest {
    public static void main(String[] args) {
        Geni g = new Geni("aaa");
        System.out.println(g);

        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        Geni g2 = new Geni(list);
        System.out.println(g2);

        int[] i = new int[2];
        i[0] = 10;
        i[1] = 11;
        Geni g3 = new Geni(i);
        System.out.println(g3);
    }

    private static class Geni<T> {
        T data;

        public Geni(T body) {
            if (body instanceof List) {
                System.out.println("list...");
            } else if (body instanceof int[]){
                System.out.println("array...");
            } else {
                System.out.println("obj...");
            }
            data = body;
        }

        public Geni(List<T> list) {
            System.out.println("list22222...");
        }

        public Geni(T[] arr) {
            System.out.println("array22222...");
        }

        @Override
        public String toString() {
            return "data=" + data;
        }
    }
}
