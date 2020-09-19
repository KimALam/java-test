package rxjava.combine;

import rxjava.common.Shape;
import io.reactivex.Observable;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class CombineLatestTest {
    public static void main(String[] args) throws InterruptedException {
        String[] data1 = {"6", "7", "4", "2"};
        String[] data2 = {"DIAMOND", "STAR", "PENTAGON"};

        Observable<String> source = Observable.combineLatest(
                Observable.fromArray(data1)
                        .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS),
                                (shape, notUsed) -> {
                                    log.info("zipWith #1: shape={}, notUsed={}", shape, notUsed);
                                    return Shape.getColor(shape);
                                }),
                Observable.fromArray(data2)
                        .zipWith(Observable.interval(150L, 200L, TimeUnit.MILLISECONDS),
                                (shape, notUsed) -> {
                                    log.info("zipWith #2: shape={}, notUsed={}", shape, notUsed);
                                    return Shape.getSuffix(shape);
                                }),
                (v1, v2) -> {
                    log.info("combineLatest: v1={}, v2={}", v1, v2);
                    return v1 + v2;
                });

        source.subscribe(data -> log.info("onNext: data={}", data));

        Thread.sleep(1000);
    }
}
