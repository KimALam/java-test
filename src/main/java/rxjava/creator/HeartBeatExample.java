package rxjava.creator;

import io.reactivex.Observable;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Slf4j
public class HeartBeatExample {
    public static void main(String[] args) throws InterruptedException {
        // other threads
/*
        Observable.timer(2, TimeUnit.SECONDS)
                .map(val -> "https://api.github.com/zen")
                .map(OkHttpHelper::get)
                .repeat()
                .subscribe(data -> log.info("Ping result : {}", data));
*/

        // same thread
        Observable.interval(2, TimeUnit.SECONDS)
                .map(val -> "https://api.github.com/zen")
                .map(OkHttpHelper::get)
                .subscribe(data -> log.info("Ping result #2 : {}", data));

        Thread.sleep(20000);
    }

    private static class OkHttpHelper {
        private static OkHttpClient client = new OkHttpClient();

        public static String get(String url) throws IOException {
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            try {
                Response res = client.newCall(request).execute();
                return res.body().string();
            } catch (IOException e) {
                e.printStackTrace();
                throw e;
            }
        }
    }
}
