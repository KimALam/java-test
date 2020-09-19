package rxjava.event;

import io.reactivex.Observable;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DoOnTerminateTest {
    public static void main(String[] args) {
        String[] orgs = {"1", "3", "5"};
        Observable<String> source = Observable.fromArray(orgs);

        source.doOnTerminate(() -> log.info("doOnTerminate"))
                .doOnComplete(() -> log.info("doOnComplete"))
                .doOnError(e -> log.info("doOnError :{}", e.getMessage()))
                .subscribe(val -> log.info("onNext : {}", val));
    }
}
