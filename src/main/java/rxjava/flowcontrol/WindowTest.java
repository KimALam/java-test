package rxjava.flowcontrol;

import io.reactivex.Observable;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class WindowTest {
    public static void main(String[] args) throws InterruptedException {
        String[] data = {"1", "2", "3", "4", "5", "6"};
        log.info("start...");

        Observable<String> earlySource = Observable.fromArray(data)
                .take(3)
                .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), (a, b) -> a);
        Observable<String> middleSource = Observable.just(data[3])
                .zipWith(Observable.interval(300L, TimeUnit.MILLISECONDS), (a, b) -> a);
        Observable<String> lateSource = Observable.just(data[4], data[5])
                .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), (a, b) -> a);

        Observable<Observable<String>> source = Observable.concat(earlySource, middleSource, lateSource)
                .window(3);

        source.subscribe(observable -> {
            log.info("New Observable Started!!!");
            observable.subscribe(val -> log.info("Window.onNext : {}", val));
        });

        Thread.sleep(1000);
    }
}
