package rxjava.test.http;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

@Slf4j
public class MultiCallTest {
    private static final String req1 = "https://api.github.com/zen";
    private static final String req2 = "https://raw.githubusercontent.com/yudong80/reactivejava/master/README.md";
    private static final String req3 = "https://raw.githubusercontent.com/yudong80/reactivejava/master/samples/callback_hell";

    private static final OkHttpClient client = new OkHttpClient();

    public static void main(String[] args) throws InterruptedException {
        Observable.zip(
                Observable.fromCallable(() -> requestGet(req1))
                        .doOnSubscribe(d -> log.info("Request #1 : {}", d))
                        .subscribeOn(Schedulers.io()),
                Observable.fromCallable(() -> requestGet(req2))
                        .doOnSubscribe(d -> log.info("Request #2 : {}", d))
                        .subscribeOn(Schedulers.io()),
                Observable.fromCallable(() -> requestGet(req3))
                        .doOnSubscribe(d -> log.info("Request #3 : {}", d))
                        .subscribeOn(Schedulers.io()),
                (a, b, c) -> {
                    log.info("a = {}", a);
                    log.info("b = {}", b);
                    log.info("c = {}", c);
                    return a;
                }
        ).subscribe(val -> log.info("val={}", val));

        Thread.sleep(3000);
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
