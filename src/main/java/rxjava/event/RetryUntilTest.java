package rxjava.event;

import rxjava.common.OkHttpHelper;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetAddress;

@Slf4j
public class RetryUntilTest {
    public static void main(String[] args) throws InterruptedException {
        log.info("Start...");

        String url = "https://api.github.com/zen";

        Observable<String> source = Observable.just(url)
                .map(OkHttpHelper::get)
                .subscribeOn(Schedulers.io())
                .retryUntil(() -> {
                    log.info("retrying until...");

                    if (isNetworkAvaliable())
                        return true;

                    Thread.sleep(1000);
                    return false;
                });

        log.info("subscribing...");
        source.subscribe(data -> log.info("onNext : {}", data));

        Thread.sleep(5000);
    }

    public static boolean isNetworkAvaliable() {
        try {
            return InetAddress.getByName("www.google.com").isReachable(1000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
