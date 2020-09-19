package rxjava.test.http;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;

@Slf4j
public class MultiCallTest2 {
    private static final String req1 = "://api.github.com/zen";
    private static final String req2 = "://raw.githubusercontent.com/yudong80/reactivejava/master/README.md";
    private static final String req3 = "://raw.githubusercontent.com/yudong80/reactivejava/master/samples/callback_hell";

    private static final OkHttpClient client = new OkHttpClient();

    public static void main(String[] args) throws InterruptedException {
        Single<List<String>> source = Observable.just("https")
                .flatMap(protocol -> {
                    log.info("flatMap : {}", protocol);
                    return Observable.zip(
                            Observable.fromCallable(() -> {
                                log.info("flatMap.fromCallable #1 : {}", protocol);
                                return requestGet(protocol + req1);
                            })
                            .doOnSubscribe(d -> log.info("flatMap.fromCallabe #1 doOnSubscribe() : {}", d))
                            .subscribeOn(Schedulers.io()),

                            Observable.fromCallable(() -> {
                                log.info("flatMap.fromCallable #2 : {}", protocol);
                                return requestGet(protocol + req2);
                            })
                            .doOnSubscribe(d -> log.info("flatMap.fromCallabe #2 doOnSubscribe() : {}", d))
                            .subscribeOn(Schedulers.io()),
                            (a, b) -> {
                                log.info("combine : a={}, b={}", a, b);
                                return a + b;
                            });
                }).map(c -> {
                    log.info("map : {}", c);
                    return c + "_map";
                }).toList();

        List<String> data = source.blockingGet();
        log.info("data : {}", data.get(0));

//        source.subscribe(data -> log.info("onNext : {}", data.get(0)));
//        Thread.sleep(2000);
    }

    public static String requestGet(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            Response res = client.newCall(request).execute();
            return res.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
