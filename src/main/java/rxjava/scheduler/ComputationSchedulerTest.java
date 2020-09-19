package rxjava.scheduler;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class ComputationSchedulerTest {
    public static void main(String[] args) throws InterruptedException {
        String[] orgs = {"1", "3", "5"};

        Observable<String> source = Observable.fromArray(orgs)
                .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), (a, b) -> a);

        // Subscription #1
        source.map(item -> "<<" + item + ">>")
                // zip() already subscribe on computation thread.
                // so, no needed this subscribeOn();
                .subscribeOn(Schedulers.computation())
                .subscribe(i -> log.info("Subscriber #1 : {}", i));

        // Subscription #2
        source.map(item -> "##" + item + "##")
                // zip() already subscribe on computation thread.
                // so, no needed this subscribeOn();
                .subscribeOn(Schedulers.computation())
                .subscribe(i -> log.info("Subscriber #2 : {}", i));

        Thread.sleep(1000);
    }
}
