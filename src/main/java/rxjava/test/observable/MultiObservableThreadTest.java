package rxjava.test.observable;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

@Slf4j
public class MultiObservableThreadTest {
    private static final OkHttpClient client = new OkHttpClient();

    public static void main(String[] args) {
        Observable ob = Observable.fromCallable(() -> {
            log.info("fromCallable...");
            String res = requestGet("http://localhost:9091/index");
            return res;
        }).map(s -> {
            log.info("map : {}", s);
            return s + "_map";
        }).subscribeOn(Schedulers.io());

        log.info("end...");

        ob.subscribe(s -> log.info("success 1: {}", s));
        ob.subscribe(s -> log.info("success 2: {}", s));

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
