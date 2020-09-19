package rxjava.event;

import rxjava.common.OkHttpHelper;
import io.reactivex.Observable;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RetryTest {
    private static final int RETRY_MAX = 5;
    private static final int RETRY_DELAY = 1000;

    public static void main(String[] args) {
        log.info("Start...");
        String url = "https://api.github.com/zen";

        // 1. retry()
/*
        Observable<String> source = Observable.just(url)
                .map(OkHttpHelper::get)
                .retry(5)
                .onErrorReturn(e -> "error");

        log.info("subscribing...");
        source.subscribe(data -> log.info("onNext :{}", data));
*/

        // 2. retry with delay
        Observable<String> source1 = Observable.just(url)
                .map(OkHttpHelper::get)
                .retry((retryCnt, e) -> {
                    log.info("retry count : {}", retryCnt);
                    Thread.sleep(RETRY_DELAY);

                    return retryCnt < RETRY_MAX ? true : false;
                })
                .onErrorReturn(e -> "error");

        log.info("subscribing...");
        source1.subscribe(data -> log.info("onNext : {}", data));
    }
}
