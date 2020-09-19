package rxjava.scheduler;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TrampolineSchedulerTest {
    public static void main(String[] args) throws InterruptedException {
        String[] orgs = {"1", "3", "5"};
        Observable<String> source = Observable.fromArray(orgs);

        // Subscriber #1
        source.subscribeOn(Schedulers.trampoline())
                .map(data -> "<<" + data + ">>")
                .subscribe(i -> log.info("Subscriber #1 : {}", i));

        // Sbuscriber #2
        source.subscribeOn(Schedulers.trampoline())
                .map(data -> "##" + data + "##")
                .subscribe(i -> log.info("Subscriber #2 : {}", i));

        Thread.sleep(500);
    }
}
