package rxjava.flowcontrol;

import io.reactivex.Observable;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
public class BufferTest {
    public static void main(String[] args) throws InterruptedException {
        String[] data = {"1", "2", "3", "4", "5", "6"};
        log.info("start...");

        Observable<String> earlySource = Observable.fromArray(data)
                .take(3)
                .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), (a, b) -> a);
        Observable<String> middleSource = Observable.just(data[3])
                .zipWith(Observable.interval(300L, TimeUnit.MILLISECONDS), (a, b) -> a);
        Observable<String> lateSource = Observable.just(data[4], data[5])
                .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), (a, b) -> a);

        // 1. 3개씩 모아서 주기
        Observable<List<String>> source = Observable.concat(earlySource, middleSource, lateSource)
                .buffer(3);
        source.subscribe(val -> log.info("onNext : {}", val));

        // 2. 2개 모으고 1개 건너뛰기
        Observable<List<String>> source1 = Observable.concat(earlySource, middleSource, lateSource)
                .buffer(2, 3);
        source1.subscribe(val -> log.info("onNext2 : {}", val));

        Thread.sleep(1000);
    }
}
