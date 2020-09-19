package rxjava.math_etc;

import io.reactivex.Observable;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class DelayTest {
    public static void main(String[] args) throws InterruptedException {
        String[] data = {"1", "7", "2", "3", "4"};

        Observable<String> source = Observable.fromArray(data)
                .delay(100L, TimeUnit.MILLISECONDS);
        source.subscribe(i -> log.info("onNext : {}", i));

        Thread.sleep(1000);
    }
}
