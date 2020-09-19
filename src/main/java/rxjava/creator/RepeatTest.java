package rxjava.creator;

import io.reactivex.Observable;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RepeatTest {
    public static void main(String[] args) {
        String[] balls = {"1", "3", "5"};
        Observable<String> source = Observable.fromArray(balls)
                .repeat(3);

        source.doOnComplete(() -> log.info("onComplete"))
                .subscribe(data -> log.info("onNext: {}", data));
    }
}
