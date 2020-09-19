package rxjava.operator;

import io.reactivex.Observable;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class ConcatMapTest {
    public static void main(String[] args) throws InterruptedException {
        String[] balls = {"1", "3", "5"};

        Observable<String> source = Observable.interval(100L, TimeUnit.MILLISECONDS)
                .map(Long::intValue)
                .map(idx -> balls[idx])
                .take(balls.length)
                .doOnNext(l -> log.info("doOnNext : {}", l))
//                .flatMap(ball -> Observable.interval(200L, TimeUnit.MILLISECONDS).map(notUsed -> ball + "♢").take(2));
                .concatMap(ball -> Observable.interval(200L, TimeUnit.MILLISECONDS).map(notUsed -> ball + "♢").take(2));
//                .switchMap(ball -> Observable.interval(200L, TimeUnit.MILLISECONDS).map(notUsed -> ball + "♢").take(2));

        source.subscribe(data -> log.info("Subscriber #1 : {}", data));

        Thread.sleep(3000);
    }
}
