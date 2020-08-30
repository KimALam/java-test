package test.completable_future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class EmptyTest {
    private static final Logger log = LoggerFactory.getLogger(EmptyTest.class);

    public static void main(String[] args) {
        List<String> coffeeList = Arrays.asList("americano", "moca", "latte");

        log.info("before supplyAsync");
        List<CompletableFuture<Integer>> completableFutureList = coffeeList.stream()
                .map(name -> CompletableFuture.supplyAsync(() -> {
                    log.info("supplyAsync : " + name);
                    return getResult();
                }))
                .collect(toList());
        log.info("after supplyAsync");

        Map<Integer, Integer> result = completableFutureList.stream()
                .map(CompletableFuture::join)
                .filter(Objects::nonNull)
                .peek(System.out::println)
                .collect(toMap(i -> i, Function.identity()));

        System.out.println(completableFutureList.size());
        System.out.println(result);
    }

    private static Integer getResult() {
        return null;
    }
}
