package rxjava.flowcontrol;

import io.reactivex.Observable;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class DebounceTest {
    public static void main(String[] args) throws InterruptedException {
        String[] data = {"1", "2", "3", "5"};

        Observable<String> source = Observable.concat(
                Observable.timer(100L, TimeUnit.MILLISECONDS).map(i -> {
                    log.info("Timer #0 : {}", i);
                    return data[0];
                }),
                Observable.timer(300L, TimeUnit.MILLISECONDS).map(i -> {
                    log.info("Timer #1 : {}", i);
                    return data[1];
                }),
                Observable.timer(100L, TimeUnit.MILLISECONDS).map(i -> {
                    log.info("Timer #2 : {}", i);
                    return data[2];
                }),
                Observable.timer(300L, TimeUnit.MILLISECONDS).map(i -> {
                    log.info("Timer #3 : {}", i);
                    return data[3];
                })
        ).debounce(200L, TimeUnit.MILLISECONDS);

        source.subscribe(val -> log.info("onNext : {}", val));
        Thread.sleep(1000);
    }
}
