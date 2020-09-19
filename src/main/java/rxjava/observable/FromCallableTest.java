package rxjava.observable;

import io.reactivex.Observable;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;

@Slf4j
public class FromCallableTest {
    public static void main(String[] args) {
        Callable<String> callable = () -> {
            log.info("In callable...");
            Thread.sleep(3000);
            return "Hello Callable";
        };

        Observable<String> source = Observable.fromCallable(callable);
        source.subscribe(next -> log.info("onNext: {}", next));
    }
}
