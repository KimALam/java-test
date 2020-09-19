package rxjava.observable;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class ConnectableObservableTest {
    public static void main(String[] args) throws InterruptedException {
        String[] dt = {"1", "3", "5"};
        Observable<String> balls = Observable.interval(100L, TimeUnit.MILLISECONDS)
                .map(Long::intValue)
                .map(i -> dt[i])
                .take(dt.length);

        ConnectableObservable<String> source = balls.publish();

        source.subscribe(data -> log.info("Subscriber #1 => {}", data));
        source.subscribe(data -> log.info("Subscriber #2 => {}", data));

        source.connect();

        Thread.sleep(250);
        source.subscribe(data -> log.info("Subscriber #3 => {}", data));
        Thread.sleep(100);
    }
}
