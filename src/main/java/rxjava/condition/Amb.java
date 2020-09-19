package rxjava.condition;

import io.reactivex.Observable;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Amb {
    public static void main(String[] args) throws InterruptedException {
        String[] data1 = {"1", "3", "5"};
        String[] data2 = {"2-R", "4-R"};

        List<Observable<String>> sources = Arrays.asList(
                Observable.fromArray(data1)
                        .zipWith(
                                Observable.interval(0L, 200L, TimeUnit.MILLISECONDS),
                                (data, notUsed) -> data)
                        .doOnComplete(() -> log.info("Observable #1 : onComplete()")),
                Observable.fromArray(data2)
                        .delay(100L, TimeUnit.MILLISECONDS)
                        .doOnComplete(() -> log.info("Observable #2 : onComplete()"))
        );

        Observable.amb(sources)
                .doOnComplete(() -> log.info("Result : onComplete()"))
                .subscribe(data -> log.info("onNext : {}", data));

        Thread.sleep(1000);
    }
}
