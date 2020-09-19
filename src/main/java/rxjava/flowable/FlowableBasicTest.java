package rxjava.flowable;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FlowableBasicTest {
    public static void main(String[] args) throws InterruptedException {
        Flowable.just("Hello World")
                .subscribe(val -> log.info("onNext : {}", val));

        Flowable.fromCallable(() -> {
            log.info("fromCallable...");
            Thread.sleep(1000);
            return "Done";
        })
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.single())
        .subscribe(val -> log.info("onNext : {}", val));

        Thread.sleep(2000);
    }
}
