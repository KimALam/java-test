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

public class CompletableFutureTestMain {
    private static final Logger log = LoggerFactory.getLogger(CompletableFutureTestMain.class);

    public static void main(String[] args) {
        List<String> coffeeList = Arrays.asList("americano", "moca", "latte");

        log.info("before supplyAsync");
        List<CompletableFuture<Integer>> completableFutureList = coffeeList.stream()
                .map(name -> CompletableFuture.supplyAsync(() -> {
                    log.info("supplyAsync : " + name);
                    Integer i = getPrice(name);
                    return i;
                }))
                .collect(toList());
        log.info("after supplyAsync");
        System.out.println(completableFutureList);
        System.out.println("empty list : " + completableFutureList.size());

        Map<Integer, Integer> emap = CompletableFuture.allOf(completableFutureList.toArray(new CompletableFuture[completableFutureList.size()]))
                .thenApply(Void -> {
                    log.info("thenApply. size : " + completableFutureList.size());
                    return completableFutureList.stream()
                            .map(CompletableFuture::join)
                            .filter(Objects::nonNull)
                            .collect(toMap(
                                    i -> i,
                                    Function.identity(),
                                    (old, neo) -> old));
                })
                .join();
//                .stream()
//                .forEach(price -> log.info("price is " + price));

        System.out.println("map: " + emap);
        System.out.println("map size: " + emap.size());
        log.info("main end...");
    }

    public static Integer getPrice(String name) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        return null;

        if ("latte".equals(name)) {
            return 2000;
        } else if ("americano".equals(name)) {
            return 1000;
        } else if ("moca".equals(name)) {
            return 1500;
        }
        return 500;
    }
}
