package rxjava.operator;

import io.reactivex.Observable;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ScanTest {
    public static void main(String[] args) {
        String[] balls = {"1", "3", "5"};

        Observable<String> source = Observable.fromArray(balls)
                .scan((ball1, ball2) -> ball2 + "(" + ball1 + ")");
//        Observable<String> source = Observable.fromArray(balls)
//                .reduce((ball1, ball2) -> ball2 + "(" + ball1 + ")");

        source.subscribe(data -> log.info("Subscriber #1: {}", data));
    }
}
