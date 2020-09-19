package rxjava.observable;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JustTest {
    public static void main(String[] args) {
        Observable<String> source = Observable.just("RED", "GREEN", "YELLOW");

        Disposable d = source.subscribe(
                v -> log.info("onNext() : value :" + v),
                err -> log.info("onError() : err :" + err.getMessage()),
                () -> log.info("onComplete()")
        );

        log.info("isDisposed() :" + d.isDisposed());
    }
}
