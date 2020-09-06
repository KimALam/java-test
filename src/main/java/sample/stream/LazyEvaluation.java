package sample.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LazyEvaluation {
    public static void main(String[] args) {
        final List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        System.out.println(
                list.stream()
                        .filter(i -> {
                            boolean b = i < 6;
                            System.out.println("i < 6 : " + b);
                            return b;
                        })
                        .filter(i -> {
                            boolean b = (i % 2 == 0);
                            System.out.println("i % 2 : " + b);
                            return b;
                        })
                        .map(i -> {
                            int r = i * 10;
                            System.out.println("i * 10 : " + r);
                            return r;
                        })
                        .collect(Collectors.toList())
        );
    }
}
