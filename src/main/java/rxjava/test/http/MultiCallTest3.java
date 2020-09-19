package rxjava.test.http;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MultiCallTest3 {
    public static void main(String[] args) throws InterruptedException {
        Single<String> source = Single.just("aaaa")
                .flatMap(str -> {
                    log.info("flatMap #1 : {}", str);
                    return Single.fromCallable(() -> {
                        log.info("flatMap #1 fromCallable");
                        Thread.sleep(2000);
                        return str + "_flatMap1";
                    }).subscribeOn(Schedulers.io());
                })
                .flatMap(str2 -> {
                    log.info("flatMap #2 : {}", str2);
                    return Single.fromCallable(() -> {
                        log.info("flatMap #2 fromCallable");
                        Thread.sleep(2000);
                        return str2 + "_flatMap1";
                    }).subscribeOn(Schedulers.io());
                })
                .map(str3 -> {
                    log.info("map : {}", str3);
                    return str3 + "_map";
                });

        // 순차적으로 2초씩 멈추네.
        // async 로 하려면 zip과 subscribeOn으로?
//        source.blockingGet();
        source.subscribe(data -> log.info("onNext : {}", data));
        Thread.sleep(5000);
    }
}
