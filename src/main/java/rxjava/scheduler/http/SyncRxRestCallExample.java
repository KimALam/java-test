package rxjava.scheduler.http;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;

import static rxjava.scheduler.http.CommonUtils.FIRST_URL;
import static rxjava.scheduler.http.CommonUtils.SECOND_URL;

@Slf4j
public class SyncRxRestCallExample {
    private static final OkHttpClient client = new OkHttpClient();

    public static void main(String[] args) throws InterruptedException {
        Observable<String> source = Observable.just(FIRST_URL)
                .map(SyncRxRestCallExample::get)
                .concatWith(Observable.just(SECOND_URL)
                        .map(SyncRxRestCallExample::get))
                .subscribeOn(Schedulers.io());

        source.subscribe(val -> log.info("onNext : {}", val));

        Thread.sleep(5000);
    }

    public static String get(String url) throws IOException {
        log.info("Requesting get : {}", url);

        Request request = new Request.Builder().url(url).build();
        return client.newCall(request)
                .execute()
                .body()
                .string();
    }
}
