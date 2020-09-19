package rxjava.event;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class RetryWhenTest {
    public static void main(String[] args) {
        Observable.create((ObservableEmitter<String> emitter) -> {
            emitter.onError(new RuntimeException("always fails"));
        }).retryWhen(attempts -> {
            log.info("in retryWhen...");

            return attempts.zipWith(Observable.range(1, 3), (n, i) -> i)
                    .flatMap(i -> {
                        log.info("delay retry by {} seconds", i);
                        return Observable.timer(i, TimeUnit.SECONDS);
                    });
        }).blockingForEach(d -> log.info("bloking for each : {}", d));
    }
}
