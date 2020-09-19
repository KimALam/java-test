package rxjava.combine;

import io.reactivex.Observable;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class MergeTest {
    public static void main(String[] args) throws InterruptedException {
        String[] data1 = {"1", "3"};
        String[] data2 = {"2", "4", "6"};

        Observable<String> source1 = Observable.interval(0L, 2, TimeUnit.SECONDS)
                .map(Long::intValue)
                .map(idx -> data1[idx])
                .take(data1.length);

        Observable<String> source2 = Observable.interval(1, TimeUnit.SECONDS)
                .map(Long::intValue)
                .map(idx -> data2[idx])
                .take(data2.length);

        Observable<String> source = Observable.merge(source1, source2);

        source.subscribe(data -> log.info("onNext: {}", data));

        Thread.sleep(5000);
    }
}
