package rxjava.flowcontrol;

import io.reactivex.Observable;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class ThrottleFirstTest {
    public static void main(String[] args) throws InterruptedException {
        String[] data = {"1", "2", "3", "4", "5", "6"};
        log.info("start...");

        Observable<String> earlySource = Observable.just(data[0])
                .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), (a, b) -> a);
        Observable<String> middleSource = Observable.just(data[1])
                .zipWith(Observable.interval(300L, TimeUnit.MILLISECONDS), (a, b) -> a);
        Observable<String> lateSource = Observable.just(data[2], data[3], data[4], data[5])
                .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), (a, b) -> a)
                .doOnNext(d -> log.info("doOnNext : {}", d));

        Observable<String> source = Observable.concat(earlySource, middleSource, lateSource)
                .throttleFirst(200L, TimeUnit.MILLISECONDS);

        source.subscribe(val -> log.info("onNext : {}", val));
        Thread.sleep(1000);
    }
}
