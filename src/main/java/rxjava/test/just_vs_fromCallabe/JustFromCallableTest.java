package rxjava.test.just_vs_fromCallabe;

import io.reactivex.Observable;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;

@Slf4j
public class JustFromCallableTest {
    public static void main(String[] args) throws InterruptedException {
        log.info("start : {}", getTimeObject());

        // 1. just()
        // subscribe() 하더라도 처음 계산된 값을 그대로 돌려준다.
        Observable<Long> justSource = Observable.just(getTimeObject());

        justSource.subscribe(val -> log.info("just.onNext #1 : {}", val));
        Thread.sleep(3000);
        justSource.subscribe(val -> log.info("just.onNext #2 : {}", val));

        // 2. fromCallable()
        // subscribe() 할 때마다 callable 이 재계산 된다.
        Observable<Long> callableSource = Observable.fromCallable(() -> getTimeObject());

        callableSource.subscribe(val -> log.info("fromCallable.onNext #2 : {}", val));
        Thread.sleep(3000);
        callableSource.subscribe(val -> log.info("fromCallable.onNext #2 : {}", val));

        log.info("end: {}", getTimeObject());
    }

    public static Long getTimeObject() {
        return Instant.now().getEpochSecond();
    }
}
