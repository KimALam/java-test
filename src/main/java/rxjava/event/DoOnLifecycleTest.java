package rxjava.event;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class DoOnLifecycleTest {
    public static void main(String[] args) throws InterruptedException {
        String[] orgs = {"1", "3", "5", "2", "6"};

        Observable<String> source = Observable.fromArray(orgs)
                .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), (a, b) -> {
                    log.info("zipWith : a={}, b={}", a, b);
                    return a;
                })
                .doOnLifecycle(d -> log.info("doOnSubscribe : {}", d), () -> log.info("doOnDispose"));
        Disposable d = source.subscribe(data -> log.info("onNext :{}", data));

        Thread.sleep(200);
        d.dispose();
        Thread.sleep(300);
    }
}
