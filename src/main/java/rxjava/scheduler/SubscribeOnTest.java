package rxjava.scheduler;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SubscribeOnTest {
    public static void main(String[] args) throws InterruptedException {
        String[] orgs = {"1", "3", "5"};

        Observable.fromArray(orgs)
                .doOnNext(data -> log.info("Original data : {}", data))
                .map(data -> "<<" + data + ">>")
                .subscribeOn(Schedulers.newThread())
                .subscribe(i -> log.info("onNext : {}", i));

//        Thread.sleep(1000);

        Observable.fromArray(orgs)
                .doOnNext(data -> log.info("Original data2 : {}", data))
                .map(data -> "##" + data + "##")
                .subscribeOn(Schedulers.newThread())
                .subscribe(i -> log.info("onNext2 : {}", i));

        Thread.sleep(1000);
    }
}
