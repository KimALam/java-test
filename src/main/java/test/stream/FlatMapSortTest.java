package test.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

class FlatMapSortTest {
    public static void main(String[] args) {
        List<List<Integer>> ill = new ArrayList<>();
        ill.add(Arrays.asList(1, 6, 5));
        ill.add(Arrays.asList(4, 2, 3));

        List<Integer> il = ill.stream()
                .flatMap(l -> {
                    System.out.println("in stream : list = " + l);
                    return l.stream();
                })
                .peek(l -> System.out.println("in peek1 : " + l))
                .sorted()
                .peek(l -> System.out.println("in peek2 : " + l))
                .collect(toList());

        System.out.println(il);
    }
}
