package test.collections;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MinMaxTest {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(4, 3, 8, 1, 9);
        Integer min = Collections.min(list);
        System.out.println("min : " + min);
    }
}
