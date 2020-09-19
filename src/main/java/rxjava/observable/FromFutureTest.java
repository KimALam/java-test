package rxjava.observable;

import io.reactivex.Observable;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
public class FromFutureTest {
    public static void main(String[] args) {
        Future<String> future = Executors.newSingleThreadExecutor().submit(() -> {
            log.info("In future...");
            Thread.sleep(1000);
            return "Hello Future";
        });

        log.info("Create source...");
        Observable<String> source = Observable.fromFuture(future);
        source.subscribe(next -> log.info("onNext: {}", next));
    }
}
