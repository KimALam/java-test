package rxjava.event;

import io.reactivex.Observable;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DoOnNextCompleteErrorTest {
    public static void main(String[] args) {
        String[] orgs = {"1", "3", "5"};
        Observable<String> source = Observable.fromArray(orgs);

        source.doOnNext(data -> log.info("doOnNext() : {}", data))
                .doOnComplete(() -> log.info("doOnComplete()"))
                .doOnError(e -> log.info("doOnError() : {}", e.getMessage()))
                .subscribe(val -> log.info("onNext : {}", val));

        // onError
        Integer[] divider = {10, 5, 0};
        Observable.fromArray(divider)
                .map(div -> 1000 / div)
                .doOnNext(data -> log.info("doOnNext() : {}", data))
                .doOnComplete(() -> log.info("doOnComplete()"))
                .doOnError(e -> log.info("doOnError() : {}", e.getMessage()))
                .subscribe(val -> log.info("onNext : {}", val));
    }
}
