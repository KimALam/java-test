package rxjava.flowcontrol;

import io.reactivex.Observable;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class SampleTest {
    public static void main(String[] args) throws InterruptedException {
        String[] data = {"1", "7", "2", "3", "6"};
        log.info("start...");

        Observable<String> earlySource = Observable.fromArray(data)
                .take(4)
                .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), (a, b) -> a);

        Observable<String> lateSource = Observable.just(data[4])
                .zipWith(Observable.timer(300L, TimeUnit.MILLISECONDS), (a, b) -> a);

        Observable<String> source = Observable.concat(earlySource, lateSource)
                .sample(300L, TimeUnit.MILLISECONDS);

        source.subscribe(val -> log.info("onNext : {}", val));
        Thread.sleep(1000);
        // main thread
//        source.blockingForEach(val -> log.info("onNext : {}", val));
    }
}
