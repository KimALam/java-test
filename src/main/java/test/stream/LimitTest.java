package test.stream;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

class LimitTest {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5)
                .stream()
                .map(i -> {
                    System.out.println("i = " + i);
                    return i + 10;
                })
                .limit(5)
                .collect(toList());
    }
}
