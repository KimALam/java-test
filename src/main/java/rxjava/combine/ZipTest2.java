package rxjava.combine;

import io.reactivex.Observable;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class ZipTest2 {
    public static void main(String[] args) throws InterruptedException {
        Observable<Integer> source = Observable.zip(
                Observable.just(100, 200, 300),
                Observable.just(10, 20, 30),
                Observable.just(1, 2, 3),
                (a, b, c) -> a + b + c);
        source.subscribe(data -> log.info("value: {}", data));

        Observable<String> source1 = Observable.zip(
                Observable.just("RED", "GREEN", "BLUE"),
                Observable.interval(200L, TimeUnit.MILLISECONDS),
                (value, i) -> value);
        source1.subscribe(data -> log.info("zipInterval: {}", data));

        Thread.sleep(1000);
    }
}
