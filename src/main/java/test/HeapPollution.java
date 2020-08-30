package test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class HeapPollution {
    public static void main(String[] args) {
        Set s = new TreeSet<Integer>();
        Set<String> ss = s;

        s.add(new Integer(42));

        Iterator<String> iter = ss.iterator();
        while (iter.hasNext()) {
            String str = iter.next();
            System.out.println(str);
        }

/**
        array -> reifiable -> runtime can know type
*/
//        List<Integer> integers = new ArrayList<Integer>() {{
//            add(1);
//            add(2);
//        }};
//        List<Number> numbers = integers;

//        Integer[] integers = {1, 2, 3, 4};
//        Number[] numbers = integers;


/**
        generic -> type erasure -> non-reifiable -> runtime can't know type
*/
//        covariance -> read only
//        List<? extends Number> numbers = new ArrayList<Integer>() {{
//            add(1);
//            add(2);
//        }};
//        Number n = numbers.get(0);
//        numbers.add(3); // error. read only

//        contravariance -> write only
//        List<? super Integer> integers = new ArrayList<Number>();
//        integers.add(1);
//        System.out.println(integers.get(0)); // ClassCastException.
    }

/**
    source(covariant) -> read only
    destination(contravariant) -> write only
    if want read & write, use invariant.
*/
    private static void copy(List<? extends Number> source, List<? super Number> destination) {
        for (Number number : source) {
            destination.add(number);
        }
    }
}
