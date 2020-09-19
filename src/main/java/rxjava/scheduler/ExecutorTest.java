package rxjava.scheduler;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Slf4j
public class ExecutorTest {
    public static void main(String[] args) throws InterruptedException {
        final int THREAD_NUM = 10;
        Executor executor = Executors.newFixedThreadPool(THREAD_NUM);

        String[] data = {"1", "3", "5"};
        Observable<String> source = Observable.fromArray(data);

        source.subscribeOn(Schedulers.from(executor))
                .subscribe(val -> log.info("Subscriber #1 : {}", val));
        source.subscribeOn(Schedulers.from(executor))
                .subscribe(val -> log.info("Subscriber #2 : {}", val));

        Thread.sleep(500);
    }
}
