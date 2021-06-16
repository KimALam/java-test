package test.completable_future;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ThenCombineTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<List<Long>> cf1 = CompletableFuture.supplyAsync(
                () -> new ArrayList() {{
                    add(1);
                    add(2);
                }});
        CompletableFuture<List<Long>> cf2 = CompletableFuture.supplyAsync(
                () -> new ArrayList() {{
                    add(10);
                    add(20);
                }});

        List<Long> list = getList(
                new ArrayList() {{ add(10L); add(20L); }},
                new ArrayList() {{ add(1L); add(2L); add(3L); }}
        );

        System.out.println(list);

//        List<Long> list = cf1.thenCombine(cf2, (l1, l2) -> {
//            l1.addAll(l2);
//            return l1;
//        }).get();
    }

    private static List<Long> getList(List<Long> a, List<Long> b) {
        return Stream.of(a, b)
                     .peek(s -> System.out.println("in stream #1 : " + s))
                     .flatMap(Collection::stream)
                     .peek(s -> System.out.println("in stream #2 : " + s))
                     .collect(toList());
    }
}
