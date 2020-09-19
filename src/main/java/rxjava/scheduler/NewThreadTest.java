package rxjava.scheduler;

import rxjava.common.Shape;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NewThreadTest {
    public static void main(String[] args) throws InterruptedException {
        String[] objs = {"1-S", "2-T", "3-P"};

        Observable<String> source = Observable.fromArray(objs)
                .doOnNext(data -> log.info("Original data : {}", data))
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread())
                .map(Shape::getShape);

        source.subscribe(i -> log.info("onNext: {}", i));

        Thread.sleep(1000);
    }
}
