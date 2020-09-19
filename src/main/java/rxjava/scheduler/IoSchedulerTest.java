package rxjava.scheduler;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

@Slf4j
public class IoSchedulerTest {
    public static void main(String[] args) throws InterruptedException {
        String thisDir = "/Users/alkim78/test/rxjava-test";
        File[] files = new File(thisDir).listFiles();

        Observable<String> source = Observable.fromArray(files)
                .filter(f -> !f.isDirectory())
                .map(f -> f.getAbsolutePath())
                .subscribeOn(Schedulers.io());

        source.subscribe(i -> log.info("onNext : {}", i));

        Thread.sleep(500);
    }
}
