package rxjava.math_etc;

import io.reactivex.Observable;
import io.reactivex.schedulers.Timed;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class TimeIntervalTest {
    public static void main(String[] args) throws InterruptedException {
        String[] data = {"1", "3", "7"};

        log.info("main start...");
        Observable<Timed<String>> source = Observable.fromArray(data)
                .delay(item -> {
                    try {
                        Thread.sleep(new Random().nextInt(100));
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }

                    log.info("wake up...");
                    return Observable.just(item);
                })
                .timeInterval();

        source.subscribe(i -> log.info("onNext : {}", i));

        Thread.sleep(1000);
    }
}
