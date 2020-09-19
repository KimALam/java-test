package rxjava.condition;

import io.reactivex.Observable;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class TakeUntil {
    public static void main(String[] args) throws InterruptedException {
        String[] data = {"1", "2", "3", "4", "5", "6"};

        Observable<String> source = Observable.fromArray(data)
                .zipWith(
                        Observable.interval(100L, TimeUnit.MILLISECONDS),
                        (val, notUsed) -> val)
                .takeUntil(Observable.timer(500L, TimeUnit.MILLISECONDS));

        source.subscribe(i -> log.info("onNext : {}", i));

        Thread.sleep(1000);
    }
}
