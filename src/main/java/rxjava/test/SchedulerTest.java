package rxjava.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class SchedulerTest {
    public static void main(String[] args) throws InterruptedException {
/*
        Single<String> source = Single.zip(
                Single.fromCallable(() -> {
                    log.info("callable #1");
                    Thread.sleep(5000);
                    log.info("callable wakeup #1");
                    return "aaaa";
                }).subscribeOn(Schedulers.io()),
                Single.fromCallable(() -> {
                    log.info("callable #2");
                    Thread.sleep(1000);
                    log.info("callable wakeup #2");
                    return "bbbb";
                }).subscribeOn(Schedulers.io()),
                Single.fromCallable(() -> {
                    log.info("callable #3");
                    Thread.sleep(100);
                    log.info("callable wakeup #3");
                    return "cccc";
                }).subscribeOn(Schedulers.io()),
                (a, b, c) -> {
                    log.info("zipper : a={}, b={}, c={}", a, b, c);
                    return a;
                }
        ).map(a -> {
            log.info("map : {}", a);
            return a;
        });

//        source.subscribe(data -> log.info("onNext : {}", data));
//        log.info("main sleep 1");
//        Thread.sleep(2000);
//        log.info("main sleep 2");
//        Thread.sleep(10_000);

        log.info("before blockingGet");
        Thread.sleep(2000);
        String result = source.blockingGet();
        log.info("after blockingGet : {}", result);
*/

        ExecutorService service = Executors.newFixedThreadPool(2);

        service.execute(() -> {
            log.info("runnable #1");
        });

        service.execute(() -> {
            log.info("runnable #2");
            try {
                Thread.sleep(1000);
                log.info("runnable wakeup #2");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
